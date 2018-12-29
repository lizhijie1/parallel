/**
 * RestartActor.java	  V1.0   2018��12��21�� ����8:36:32
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
	//ö�ٵ�ʹ��
	public static enum Msg{
		DONE,RESTART
	}
	//�ɹ�����RestartActor�Ļص�
	@Override
	public void preStart() throws Exception {
		super.preStart();
		System.out.println("preStart hashCode:"+this.hashCode());
	}

	//����֮�󣬴���Actyorʵ��֮�󣬻�����������
	@Override
	public void postRestart(Throwable reason) throws Exception {
		super.postRestart(reason);
		System.out.println("postRestart hashcode:"+this.hashCode());
	}

	//����֮��ͣ��Actor������������
	@Override
	public void postStop() throws Exception {
		System.out.println("postStop hashCode:"+this.hashCode());
	}

	//����֮ǰ����
	@Override
	public void preRestart(Throwable reason, Option<Object> message) throws Exception {
		super.preRestart(reason, message);
		System.out.println("preRestart hashCode:"+this.hashCode());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == Msg.DONE){
			//ֹͣActor
			getContext().stop(getSelf());
		}else if(msg == Msg.RESTART){
			System.out.println(((Object)null).toString());
			//�׳��쳣Ĭ�ϻᱻrestart,���������resume
			double a= 0/0;
		}
		unhandled(msg);
	}
}
