/**
 * HelloWord.java	  V1.0   2018年12月21日 上午11:03:58
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.akkaDemo;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

public class HelloWord extends UntypedActor{
	
	ActorRef greeter;
	
	//在Actor启动之前，会被Akka框架调用，完成初始化工作
	@Override
	public void preStart() throws Exception {
		//创建了greeter实例
		//创建Greeter时使用的是HelloWorld的上下文，因此它属于HelloWorld的子Actor
		greeter = getContext().actorOf(Props.create(Greeter.class),"greeter");
		System.out.println("Greeter Actor Path"+greeter.path());
		//并向它发送GREET消息
		greeter.tell(Greeter.Msg.GREET,getSelf());
	}

	//helloWold的消息处理函数
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == Greeter.Msg.DONE){
			//向greeter发送一条消息
			greeter.tell(Greeter.Msg.GREET, getSelf());
			//停止自己
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}

}
