/**
 * MyWorker.java	  V1.0   2018年12月22日 下午3:34:15
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.ActorFuture;

import ThreadTest.day06.ActorFuture.Printer.Msg;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyWorker extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	public static enum Msg{
		DONE,CLOSE
	}
	
	@Override
	public void onReceive(Object msg){
		if(msg instanceof Integer){
			int i = (int) msg;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				getSender().tell(i*i,getSelf());
			}
			getSender().tell(i*i,getSelf());
		}
		if(msg == Msg.DONE){
			log.info("Stop working");
		}else if(msg == Msg.CLOSE){
			log.info("I will shutdown");
		}else{
			unhandled(msg);
		}
	}

}
