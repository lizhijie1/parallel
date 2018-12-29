/**
 * STM.java	  V1.0   2018��12��24�� ����6:20:36
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
 * ������������������ڴ�
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class STMDemo{
	public static ActorRef company = null;
	public static ActorRef employee = null;
	
	public static void main(String[] args) throws Exception {
		final ActorSystem  system= ActorSystem.create("transactionDemo",ConfigFactory.load("samplehello.conf"));
		//����˾�˻�
		company = system.actorOf(Props.create(CompanyActor.class), "company");
		//�����Ա�˻�
		employee = system.actorOf(Props.create(EmployeeActor.class), "employee");
		
		Timeout timeout = new Timeout(1,TimeUnit.SECONDS);
		for (int i = 0; i < 20; i++) {
			//�½�һ��CoordinatedЭ���ߣ����ҽ����Э���ߵ�����Ϣ���͸�company
			//��company���յ����Э������Ϣ���Զ���Ϊ�������ĵ�һ����Ա
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
