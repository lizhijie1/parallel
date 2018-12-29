/**
 * RestartActor.java	  V1.0   2018年12月21日 下午8:36:32
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.strategy;

import akka.actor.UntypedActor;
import scala.Option;

public class RestartActor extends UntypedActor{
	//枚举的使用
	public static enum Msg{
		DONE,RESTART
	}
	//成功创建RestartActor的回调
	@Override
	public void preStart() throws Exception {
		super.preStart();
		System.out.println("preStart hashCode:"+this.hashCode());
	}

	//重启之后，创建Actyor实例之后，会调用这个函数
	@Override
	public void postRestart(Throwable reason) throws Exception {
		super.postRestart(reason);
		System.out.println("postRestart hashcode:"+this.hashCode());
	}

	//重启之后，停掉Actor会调用这个函数
	@Override
	public void postStop() throws Exception {
		System.out.println("postStop hashCode:"+this.hashCode());
	}

	//重启之前调用
	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		super.preRestart(reason, message);
		System.out.println("preRestart hashCode:"+this.hashCode());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == Msg.DONE){
			//停止Actor
			getContext().stop(getSelf());
		}else if(msg == Msg.RESTART){
			System.out.println(((Object)null).toString());
			//抛出异常默认会被restart,但是这里会resume
			double a= 0/0;
		}
		unhandled(msg);
	}
}
