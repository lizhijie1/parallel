/**
 * BadyActor.java	  V1.0   2018��12��22�� ����11:15:14
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.InlayStatus;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Procedure;
/**
 * 
 * ��������������״̬ת��
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class BadyActor extends UntypedActor{
	
	private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
	
	public static enum Msg{
		SLEEP,PLAY,CLOSE
	}
	
	Procedure<Object> angry = new Procedure<Object>() {
		@Override
		public void apply(Object message) throws Exception {
			System.out.println("angryApply:"+message);
			if(message == Msg.SLEEP){
				getSender().tell("I am already angry",getSelf());
				System.out.println("I am already angry");
			}else if(message == Msg.PLAY){
				System.out.println("I like playing");
				getContext().become(happy);
			}
		}
	};
	
	Procedure<Object> happy = new Procedure<Object>() {
		@Override
		public void apply(Object message) throws Exception {
			System.out.println("happyApply"+message);
			if(message == Msg.PLAY){
				getSender().tell("I am already happy :-)",getSelf());
				System.out.println("I am alreay happy :-)");
			}else if(message == Msg.SLEEP){
				System.out.println("I don`t want to sleep");
				getContext().become(angry);
			}
		}
	};
	
	//һ�����״̬�л��󣬵��������µ���Ϣ�ʹ�ʱ���Ͳ�������onReceive()����������
	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("onReceive:"+msg);
		if(msg == Msg.SLEEP){
			//become�����л��߳�״̬������become��������һ��Procedure������Procedure��������Դ���
			//һ��Actor��״̬��ͬʱ������Ҫ��������װ��������״̬�µ���Ϣ�����߼�
			getContext().become(angry);
		}else if(msg == Msg.PLAY){
			getContext().become(happy);
		}else{
			unhandled(msg);
		}
	}
	
}
