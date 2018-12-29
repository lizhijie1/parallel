/**
 * MyWork.java	  V1.0   2018年12月21日 下午3:52:11
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.akkaDemo;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MyWork extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
	
	public static enum Msg{
		WORKING,DONE,CLOSE;
	}
	
	@Override
	public void postStop() throws Exception {
		System.out.println("MyWorker is stoping");
	}

	@Override
	public void preStart() throws Exception {
		System.out.println("MyWorker is starting");
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == Msg.WORKING){
			System.out.println("I am working");
		}
		if(msg == Msg.DONE){
			System.out.println("Stop working");
		}
		if(msg == Msg.CLOSE){
			System.out.println("I will shutDown");
			getSender().tell(Msg.CLOSE, getSelf());
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}
}
