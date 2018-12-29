/**
 * STM.java	  V1.0   2018年12月24日 下午6:20:36
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.stm;

import java.util.concurrent.TimeUnit;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.EmptyLocalActorRef;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.transactor.Coordinated;
import akka.util.Timeout;
import scala.concurrent.Await;

/**
 * 
 * 功能描述：软件事务内存
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class STMDemo{
	public static ActorRef company = null;
	public static ActorRef employee = null;
	
	public static void main(String[] args) throws Exception {
		final ActorSystem  system= ActorSystem.create("transactionDemo",ConfigFactory.load("samplehello.conf"));
		//管理公司账户
		company = system.actorOf(Props.create(CompanyActor.class), "company");
		//管理雇员账户
		employee = system.actorOf(Props.create(EmployeeActor.class), "employee");
		
		Timeout timeout = new Timeout(1,TimeUnit.SECONDS);
		for (int i = 0; i < 20; i++) {
			//新建一个Coordinated协调者，并且将这个协调者当做消息发送给company
			//当company收收到这个协调者消息后，自动成为这个事务的第一个成员
			company.tell(new Coordinated(i,timeout), ActorRef.noSender());
			Thread.sleep(200);
			Integer companyCount = (Integer) Await.result(Patterns.ask(company,"GetCount",timeout),timeout.duration());
			Integer employeeCount = (Integer) Await.result(Patterns.ask(employee,"GetCount",timeout),timeout.duration());
			System.out.println("company count="+companyCount);
			System.out.println("employee count="+employeeCount);
			System.out.println("===========================");
		}		
	}
}
