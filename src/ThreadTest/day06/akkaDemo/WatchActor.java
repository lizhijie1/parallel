/**
 * WatchActor.java	  V1.0   2018年12月21日 下午4:08:49
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.akkaDemo;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class WatchActor extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
	
	public WatchActor(ActorRef ref) {
		getContext().watch(ref);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Terminated){
			System.out.println(String.format("%s has terminated,shutting down system", ((Terminated) msg).getActor().path()));
			getContext().system().shutdown();
		}else{
			unhandled(msg);
		}
	}

}
