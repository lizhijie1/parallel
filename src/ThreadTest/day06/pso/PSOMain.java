/**
 * PSOMain.java	  V1.0   2018年12月24日 下午10:12:17
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import akka.actor.Props;

public class PSOMain {
	public static final int BIRD_COUNT = 100000;
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("psoSystem",ConfigFactory.load("src/samplehello.conf"));
		system.actorOf(Props.create(MasterBird.class),"masterbird");
		for (int i = 0; i < BIRD_COUNT; i++) {
			system.actorOf(Props.create(Bird.class),"bird_"+i);
		}
	}
}
