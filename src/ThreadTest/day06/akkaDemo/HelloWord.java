/**
 * HelloWord.java	  V1.0   2018��12��21�� ����11:03:58
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
	
	//��Actor����֮ǰ���ᱻAkka��ܵ��ã���ɳ�ʼ������
	@Override
	public void preStart() throws Exception {
		//������greeterʵ��
		//����Greeterʱʹ�õ���HelloWorld�������ģ����������HelloWorld����Actor
		greeter = getContext().actorOf(Props.create(Greeter.class),"greeter");
		System.out.println("Greeter Actor Path"+greeter.path());
		//����������GREET��Ϣ
		greeter.tell(Greeter.Msg.GREET,getSelf());
	}

	//helloWold����Ϣ������
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg == Greeter.Msg.DONE){
			//��greeter����һ����Ϣ
			greeter.tell(Greeter.Msg.GREET, getSelf());
			//ֹͣ�Լ�
			getContext().stop(getSelf());
		}else{
			unhandled(msg);
		}
	}

}
