/**
 * Supervisor.java	  V1.0   2018��12��21�� ����4:40:30
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.strategy;

import java.util.concurrent.TimeUnit;

import akka.actor.OneForOneStrategy;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;
import akka.actor.SupervisorStrategy.Directive;
import akka.actor.UntypedActor;
import akka.japi.Function;
import scala.concurrent.duration.Duration;
/**
 * 
 * ����������Akka�ල����
 * 			:���һ��Actor��ִ�й����з������⣬����û�д����쳣�����³���
 * 			��ʱӦ����δ�����Akka�ڸ�Actor���Զ���Actor���мල���ල
 * 			Actor����Ϊ�Ƿ����쳣��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class Supervisor extends UntypedActor{
	//����һ���ල����
	//һ��������3�Σ��������Ƶ�ʣ���ֱ��ɱ��Actor
	private static SupervisorStrategy strategy = new OneForOneStrategy(3,Duration.create(1,TimeUnit.MINUTES),
	new Function<Throwable,Directive>(){
		@Override
		public Directive apply(Throwable t) throws Exception {
			if(t instanceof ArithmeticException){
				System.out.println("meet ArithmeticException,just resume");
				return SupervisorStrategy.resume();
			}else if(t instanceof NullPointerException){
				System.out.println("meet NullPointerException,restart");
				return SupervisorStrategy.restart();
			}else if(t instanceof IllegalArgumentException){
				return SupervisorStrategy.stop();
			}else{
				//�����׳�
				return SupervisorStrategy.escalate();
			}
		}
	});
	
	//���Ǹ���ĸ����SupervisorStrategy,�����Զ���ļල����
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}

	@Override
	public void onReceive(Object o) throws Exception {
		if(o instanceof Props){
			//�½�һ����ΪrestartActor����Actor,�����Actor���ɵ�ǰ��Supervisor���мල��
			//��SuperVisor����һ��һ��Props����ʱ���ͻ���������������һ��restartActor
			getContext().actorOf((Props)o,"restartActor");
		}else{
			unhandled(o);
		}
	}
}
