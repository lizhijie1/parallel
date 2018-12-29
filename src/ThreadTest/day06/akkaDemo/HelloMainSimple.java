/**
 * HelloMainSimple.java	  V1.0   2018��12��21�� ����3:05:54
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
import akka.actor.Props;

public class HelloMainSimple {
	public static void main(String[] args) {
		//����ActorSystem,��ʶ�����ά��Actorϵͳ
		ActorSystem create = ActorSystem.create("hello",ConfigFactory.load("src/samplehello.conf"));
		ActorRef a = create.actorOf(Props.create(HelloWord.class),"helloWord");
		System.out.println("HelloWord Actor Path:"+a.path());
	}
}
