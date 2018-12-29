/**
 * WatchActor.java	  V1.0   2018年12月21日 下午10:28:11
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.Rount;

import java.util.ArrayList;

import ThreadTest.day06.inbox.MyWorker;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRouter;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import io.netty.resolver.RoundRobinInetAddressResolver;
/**
 * 
 * 功能描述：消息路由
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */

public class WatchActor extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(),this);
	//定义路由
	public Router router;
	{
		ArrayList<Routee> routees = new ArrayList<Routee>();
		for (int i = 0; i < 5; i++) {
			ActorRef worker = getContext().actorOf(Props.create(MyWorker.class),"worker_"+i);
			getContext().watch(worker);
			routees.add(new ActorRefRoutee(worker));
		}
		//指定路由策略和一组被路由的actor
		router = new Router(new RoundRobinRoutingLogic(),routees);
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof MyWorker.Msg){
			router.route(msg, getSender());
		}else if(msg instanceof Terminated){
			router = router.removeRoutee(((Terminated) msg).actor());
			System.out.println(((Terminated) msg).actor().path()+"is closed,routees="+router.routees().size());
			if(router.routees().size() == 0){
				System.out.println("Close system");
				getContext().system().shutdown();
			}
		}else{
			unhandled(msg);
		}
	}
	
}
