/**
 * BadyMain.java	  V1.0   2018年12月22日 上午11:29:32
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.InlayStatus;

import com.typesafe.config.ConfigFactory;

import ThreadTest.day06.akkaDemo.WatchActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;

public class BadyMain {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("badyMain",ConfigFactory.load("src/samplehello.conf"));
		ActorRef child = system.actorOf(Props.create(BadyActor.class),"body");
		system.actorOf(Props.create(WatchActor.class,child), "watcher");
		child.tell(BadyActor.Msg.PLAY,ActorRef.noSender());
		child.tell(BadyActor.Msg.SLEEP, ActorRef.noSender());
		child.tell(BadyActor.Msg.PLAY,ActorRef.noSender());
		child.tell(BadyActor.Msg.PLAY,ActorRef.noSender());
		child.tell(PoisonPill.getInstance(),ActorRef.noSender());
	}
}
