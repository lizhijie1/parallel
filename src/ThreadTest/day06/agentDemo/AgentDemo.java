/**
 * AgentDemo.java	  V1.0   2018年12月24日 下午4:24:47
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.agentDemo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.dsl.Inbox.Inbox;
import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class AgentDemo {
	public static Agent<Integer> counterAgent = Agent.create(0, ExecutionContexts.global());
	static ConcurrentLinkedQueue<Future<Integer>> futures = new ConcurrentLinkedQueue<Future<Integer>>();
	
	public static void main(String[] args) {
		final ActorSystem system = ActorSystem.create("agentdemo",ConfigFactory.load("src/samplehello.conf"));
		ActorRef[] counter = new ActorRef[10];
		for (int i = 0; i < counter.length; i++) {
			counter[i] = system.actorOf(Props.create(CounterActor.class),"counter_"+i);
		}
		final Inbox inbox = (Inbox) Inbox.create(system);
		for (int i = 0; i < counter.length; i++) {
			inbox.send(counter[i],1);
			inbox.watch(counter[i]);
		}
		int closeCount = 0;
		//等待所有Actor全部结束
		while(true){
			Object msg = inbox.receive(Duration.create(1,TimeUnit.SECONDS));
			if(msg instanceof Terminated){
				closeCount++;
				if(closeCount == counter.length){
					break;
				}
			}else{
				System.out.println(msg);
			}
		}
		
		//等待所有的累加线程完成，因为他们都是异步的
		Futures.sequence(futures, system.dispatcher()).onComplete(
				new  OnComplete<Iterable<Integer>>() {
					@Override
					public void onComplete(Throwable arg0, Iterable<Integer> arg1) throws Throwable {
						System.out.println("counterAgent="+counterAgent.get());
					}
				}, system.dispatcher());
	}
}
