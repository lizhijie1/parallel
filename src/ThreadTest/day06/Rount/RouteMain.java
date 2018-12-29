/**
 * RouteMain.java	  V1.0   2018年12月21日 下午10:48:04
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.Rount;

import com.typesafe.config.ConfigFactory;
import ThreadTest.day06.inbox.MyWorker;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
/**
 * 
 * 功能描述：Akka使用一个路由器组件（Router）来封装消息的调度
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class RouteMain {
	public static Agent<Boolean> flag = Agent.create(true, ExecutionContexts.global());
	public static void main(String[] args) throws InterruptedException {
		ActorSystem system = ActorSystem.create("route", ConfigFactory.load("samplehello.conf"));
		ActorRef w = system.actorOf(Props.create(WatchActor.class),"watcher");
		int i=1;
		while(flag.get()){
			w.tell(MyWorker.Msg.WORKING,ActorRef.noSender());
			if(i%10 == 0){
				w.tell(MyWorker.Msg.CLOSE,ActorRef.noSender());
				system.shutdown();
			}
			i++;
			Thread.sleep(100);
		}
	}
}
