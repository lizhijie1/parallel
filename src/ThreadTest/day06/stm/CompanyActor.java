/**
 * CompanyActor.java	  V1.0   2018年12月24日 下午6:27:29
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.stm;

import javax.management.RuntimeErrorException;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;
import scala.util.Try;

public class CompanyActor extends UntypedActor{
	Ref.View<Integer> count = STM.newRef(100);

	@Override
	public void onReceive(Object msg) throws Exception {
		//判断消息是不是协调者，如果是，表明这是一个新事务的开始
		if(msg instanceof Coordinated){
			final Coordinated c = (Coordinated) msg;
			//获得事务参数也就是需要转账的金额
			final int downCount = (int) c.getMessage();
			//将employee也加入当前的事务中
			STMDemo.employee.tell(c.coordinate(downCount),getSelf());
			try {
				//定义原子执行块作为这个事务的一部分
				c.atomic(new Runnable(){
					@Override
					public void run() {
						if(count.get()<downCount){
							throw new RuntimeException("less than "+downCount);
						}
						//对公司账户做出调整
						STM.increment(count, -downCount);
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if("GetCount".equals(msg)){
			getSender().tell(count.get(), getSelf());
		}else{
			unhandled(msg);
		}
	}
}
