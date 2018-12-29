/**
 * DeadMain.java	  V1.0   2018��12��21�� ����4:14:57
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.akkaDemo;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
/**
 * 
 * ����������Actor����������
 * 			:һ��Actor��actorOf()���������ú�ʼ������Actorʵ�������󣬻����preStart()����
 * 			�������������ǿ��Խ���һЩ��Դ�ĳ�ʼ��������
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class DeadMain {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("deadWatch",ConfigFactory.load("src/samplehello.conf"));
		ActorRef worker = system.actorOf(Props.create(MyWork.class),"worker");
		//Props.creat()���������ĵ�һ������ΪҪ������Actor����
		//�ڶ�������Ϊ���Actor�Ĺ��캯���Ĳ���
		system.actorOf(Props.create(WatchActor.class,worker),"watcher");
		worker.tell(MyWork.Msg.WORKING, ActorRef.noSender());
		worker.tell(MyWork.Msg.DONE, ActorRef.noSender());
		worker.tell(PoisonPill.getInstance(),ActorRef.noSender());
	}
}
