/**
 * CounterActor.java	  V1.0   2018年12月22日 下午4:10:22
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.agentDemo;

import akka.actor.UntypedActor;
import akka.dispatch.Mapper;
import scala.concurrent.Future;
/**
 * 
 * 功能描述：多个Actor同时修改数据
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class CounterActor extends UntypedActor{
	Mapper addMapper = new Mapper<Integer,Integer>(){

		@Override
		public Integer apply(Integer i) {
			return i + 1;
		}
	};
	
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof Integer){
			for (int i = 0; i < 10000; i++) {
				//我希望能够知道futur合适结束
				Future<Integer> f = AgentDemo.counterAgent.alter(addMapper);
				AgentDemo.futures.add(f);
			}
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}
}
