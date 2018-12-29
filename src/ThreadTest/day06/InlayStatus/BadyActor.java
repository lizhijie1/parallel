/**
 * BadyActor.java	  V1.0   2018年12月22日 上午11:15:14
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
 * 功能描述：内置状态转换
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
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
	
	//一旦完成状态切换后，当后续有新的消息送达时，就不会再由onReceive()函数处理了
	@Override
	public void onReceive(Object msg) throws Exception {
		System.out.println("onReceive:"+msg);
		if(msg == Msg.SLEEP){
			//become用于切换线程状态。方法become（）接收一个Procedure参数。Procedure在这里可以代表
			//一种Actor的状态，同时，更重要的是他封装了在这种状态下的消息处理逻辑
			getContext().become(angry);
		}else if(msg == Msg.PLAY){
			getContext().become(happy);
		}else{
			unhandled(msg);
		}
	}
	
}
