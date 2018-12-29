/**
 * Supervisor.java	  V1.0   2018年12月21日 下午4:40:30
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
 * 功能描述：Akka监督策略
 * 			:如果一个Actor在执行过程中发生意外，比如没有处理异常，导致出错
 * 			这时应该如何处理。在Akka内父Actor可以对子Actor进行监督，监督
 * 			Actor的行为是否有异常。
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class Supervisor extends UntypedActor{
	//定义一个监督策略
	//一分钟重试3次，超过这个频率，会直接杀死Actor
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
				//向上抛出
				return SupervisorStrategy.escalate();
			}
		}
	});
	
	//覆盖父类的父类的SupervisorStrategy,设置自定义的监督策略
	@Override
	public SupervisorStrategy supervisorStrategy() {
		return strategy;
	}

	@Override
	public void onReceive(Object o) throws Exception {
		if(o instanceof Props){
			//新建一个名为restartActor的子Actor,这个子Actor就由当前的Supervisor进行监督了
			//当SuperVisor接收一个一个Props对象时，就会根据这个配置生成一个restartActor
			getContext().actorOf((Props)o,"restartActor");
		}else{
			unhandled(o);
		}
	}
}
