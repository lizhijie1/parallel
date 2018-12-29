/**
 * MyWorker.java	  V1.0   2018��12��21�� ����9:37:03
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.inbox;

import java.util.concurrent.TimeUnit;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.concurrent.duration.Duration;
/**
 * 
 * ������������Ϣ�ռ���
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class MyWorker extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
	
	public static enum Msg{
		WORKING,DONE,CLOSE
	}
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == Msg.WORKING){
			log.info("I am working");
		}
		if(msg == Msg.DONE){
			log.info("Stop working");
		}
		if(msg == Msg.CLOSE){
			log.info("I will shutdown");
			getSender().tell(Msg.CLOSE, getSelf());
			getContext().stop(getSender());
		}else{
			unhandled(msg);
		}
		
	}

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("inboxdemo",ConfigFactory.load("samplehello.conf"));
		ActorRef worker = system.actorOf(Props.create(MyWorker.class),"worker");
		final Inbox inbox =  Inbox.create(system);
		inbox.watch(worker);
		inbox.send(worker, MyWorker.Msg.WORKING);
		inbox.send(worker, MyWorker.Msg.DONE);
		inbox.send(worker, MyWorker.Msg.CLOSE);
		while(true){
			Object msg = inbox.receive(Duration.create(1,TimeUnit.SECONDS));
			if(msg == MyWorker.Msg.CLOSE){
				System.out.println("My worker is closing");
			}else if(msg instanceof Terminated){
				System.out.println("My worker is dead");
				system.shutdown();
				break;
			}else{
				System.out.println(msg);
			}
		}
	}
}
