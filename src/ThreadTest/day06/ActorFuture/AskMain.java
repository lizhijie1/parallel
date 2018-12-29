/**
 * AskMain.java	  V1.0   2018年12月22日 下午2:49:54
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.ActorFuture;

import java.awt.print.PrinterAbortException;
import java.util.concurrent.TimeUnit;

import com.typesafe.config.ConfigFactory;

import ThreadTest.day06.akkaDemo.WatchActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
/**
 * 
 * 功能描述：询问模式Actor的Future
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class AskMain {
	public static void main(String[] args) throws Exception {
		ActorSystem system = ActorSystem.create("askdemo",ConfigFactory.load("src/samplehello.conf"));
		ActorRef worker = system.actorOf(Props.create(MyWorker.class), "worker");
		ActorRef printer = system.actorOf(Props.create(Printer.class),"printer");
		system.actorOf(Props.create(WatchActor.class,worker),"watcher");
		
		//等待future返回
		Future<Object> f = Patterns.ask(worker,5,1500);
		int re = (int)Await.result(f,Duration.create(6,TimeUnit.SECONDS));
		System.out.println("return:"+re);
		
		//直接导向其他Actor,pipe不会等待
		f = Patterns.ask(worker, 6, 1500);
		Patterns.pipe(f, system.dispatcher()).to(printer);
		worker.tell(PoisonPill.getInstance(), ActorRef.noSender());
	}
}
