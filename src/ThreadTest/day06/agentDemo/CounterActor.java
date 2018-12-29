/**
 * CounterActor.java	  V1.0   2018��12��22�� ����4:10:22
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
 * �������������Actorͬʱ�޸�����
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
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
				//��ϣ���ܹ�֪��futur���ʽ���
				Future<Integer> f = AgentDemo.counterAgent.alter(addMapper);
				AgentDemo.futures.add(f);
			}
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}
}
