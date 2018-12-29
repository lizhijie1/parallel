/**
 * DeadMain.java	  V1.0   2018年12月21日 下午4:14:57
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
 * 功能描述：Actor的生命周期
 * 			:一个Actor在actorOf()函数被调用后开始建立，Actor实例创建后，会调用preStart()方法
 * 			在这个方法里，我们可以进行一些资源的初始化工作。
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class DeadMain {
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("deadWatch",ConfigFactory.load("src/samplehello.conf"));
		ActorRef worker = system.actorOf(Props.create(MyWork.class),"worker");
		//Props.creat()方法，他的第一个参数为要创建的Actor类型
		//第二个参数为这个Actor的构造函数的参数
		system.actorOf(Props.create(WatchActor.class,worker),"watcher");
		worker.tell(MyWork.Msg.WORKING, ActorRef.noSender());
		worker.tell(MyWork.Msg.DONE, ActorRef.noSender());
		worker.tell(PoisonPill.getInstance(),ActorRef.noSender());
	}
}
