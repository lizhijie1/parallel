/**
 * Printer.java	  V1.0   2018年12月22日 下午2:54:20
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.ActorFuture;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Printer extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	public static enum Msg{
		DONE,CLOSE
	}
	
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Integer){
			System.out.println("Printer:"+msg);
		}
		if(msg == Msg.DONE){
			System.out.println("Stop Working");
		}else if(msg == Msg.CLOSE){
			log.info("I will shutdown");
			getSender().tell(Msg.CLOSE, getSelf());
			getContext().system().shutdown();
		}else{
			unhandled(msg);
		}
	}

}
