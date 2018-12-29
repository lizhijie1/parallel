/**
 * CompanyActor.java	  V1.0   2018��12��24�� ����6:27:29
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.stm;

import javax.management.RuntimeErrorException;

import akka.actor.UntypedActor;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;
import scala.util.Try;

public class CompanyActor extends UntypedActor{
	Ref.View<Integer> count = STM.newRef(100);

	@Override
	public void onReceive(Object msg) throws Exception {
		//�ж���Ϣ�ǲ���Э���ߣ�����ǣ���������һ��������Ŀ�ʼ
		if(msg instanceof Coordinated){
			final Coordinated c = (Coordinated) msg;
			//����������Ҳ������Ҫת�˵Ľ��
			final int downCount = (int) c.getMessage();
			//��employeeҲ���뵱ǰ��������
			STMDemo.employee.tell(c.coordinate(downCount),getSelf());
			try {
				//����ԭ��ִ�п���Ϊ��������һ����
				c.atomic(new Runnable(){
					@Override
					public void run() {
						if(count.get()<downCount){
							throw new RuntimeException("less than "+downCount);
						}
						//�Թ�˾�˻���������
						STM.increment(count, -downCount);
					}
				});
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}else if("GetCount".equals(msg)){
			getSender().tell(count.get(), getSelf());
		}else{
			unhandled(msg);
		}
	}
}
