/**
 * StrategyMain.java	  V1.0   2018��12��21�� ����8:45:53
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.strategy;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class StrategyMain {
	public static void customStaregy(ActorSystem system){
		//����Supervisor Actor,����Supervisor����һ��RestarActor��Props
		ActorRef a = system.actorOf(Props.create(Supervisor.class),"Supervisor");
		a.tell(Props.create(RestartActor.class),ActorRef.noSender());
		//ѡ��RestartActorʵ���������RestartActor����100��Restart��Ϣ
		ActorSelection sel = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");
		for (int i = 0; i < 100; i++) {
			sel.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
		}
	}
	
	public static void main(String[] args) {
		//����ȫ��ActorSystem
		ActorSystem system = ActorSystem.create("lifecycle",ConfigFactory.load("src/lifecycle.conf"));
		customStaregy(system);
	}
}
