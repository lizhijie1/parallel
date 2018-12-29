/**
 * MasterBird.java	  V1.0   2018年12月24日 下午10:00:39
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class MasterBird extends UntypedActor{

	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	private PsoValue gBest = null;
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof PBestMsg){
			PsoValue pBest =((PBestMsg) msg).getValue();
			if(gBest == null || gBest.value<pBest.value){
				//更新最优解，通知所有粒子
				System.out.println(msg+"\n");
				gBest = pBest;
				ActorSelection selection = getContext().actorSelection("/user/bird_*");
				selection.tell(new GBestMsg(gBest), getSelf());
			}
		}else{
			unhandled(msg);
		}
	}

}
