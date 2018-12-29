/**
 * EmployeeActor.java	  V1.0   2018年12月24日 下午6:45:00
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.stm;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

public class EmployeeActor extends UntypedActor{
	private Ref.View<Integer> count = STM.newRef(50);

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Coordinated){
			final Coordinated c = (Coordinated) msg;
			final int downCount = (int) c.getMessage();
			try {
				c.atomic(new Runnable(){
					@Override
					public void run() {
						STM.increment(count,downCount);
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if("GetCount".equals(msg)){
			getSender().tell(count.get(), getSelf());
		}else{
			unhandled(msg);
		}
	}

}
