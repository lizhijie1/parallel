/**
 * StrategyMain.java	  V1.0   2018年12月21日 下午8:45:53
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
		//创建Supervisor Actor,并对Supervisor发送一个RestarActor的Props
		ActorRef a = system.actorOf(Props.create(Supervisor.class),"Supervisor");
		a.tell(Props.create(RestartActor.class),ActorRef.noSender());
		//选中RestartActor实例，想这个RestartActor发送100条Restart消息
		ActorSelection sel = system.actorSelection("akka://lifecycle/user/Supervisor/restartActor");
		for (int i = 0; i < 100; i++) {
			sel.tell(RestartActor.Msg.RESTART, ActorRef.noSender());
		}
	}
	
	public static void main(String[] args) {
		//创建全局ActorSystem
		ActorSystem system = ActorSystem.create("lifecycle",ConfigFactory.load("src/lifecycle.conf"));
		customStaregy(system);
	}
}
