/**
 * Bird.java	  V1.0   2018年12月24日 下午9:19:06
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day06.pso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Bird extends UntypedActor{
	private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	//个体最优
	private PsoValue pBest = null;
	//全局最优
	private PsoValue gBest = null;
	//velocity标识粒子在各个维度上的速度(在当前的案例中，每一年的投资额就可以认为是一个维度，因此系统有四个维度)
	private List<Double> velocity = new ArrayList<Double>(5);
	//x标识投资方案，即每一年的投资额
	private List<Double> x = new ArrayList<Double>(5);
	//随机数
	private Random r = new Random();

	//初始化粒子当前的位置
	@Override
	public void preStart() throws Exception {
		for (int i = 0; i < 5; i++) {
			velocity.add(Double.NEGATIVE_INFINITY);
			x.add(Double.NEGATIVE_INFINITY);
		}
		//x<=400
		x.set(1,(double)r.nextInt(401));
		
		//x2<=440-1.1*x1
		double max = 400-1.1*x.get(1);
		if(max<0){
			max = 0;
		}
		x.set(2,r.nextDouble()*max);
		
		//x3<=484-1.21*x1-1.1*x2
		
		max = 484-1.21*x.get(1)-1.1*x.get(2);
		if(max<0){
			max = 0;
		}
		x.set(3, r.nextDouble()*max);
		
		//x4<=532.4-1.331*x1-1.21*x2-1.1*x3
		max =532.4-1.331*x.get(1)-1.21*x.get(2)-1.1-x.get(3);
		if(max<=0){
			max = 0;
		}
		x.set(4, r.nextDouble()*max);
		
		double newFit = Fitness.fitness(x);
		pBest = new PsoValue(newFit,x);
		PBestMsg pBestMsg = new PBestMsg(pBest);
		ActorSelection selection = getContext().actorSelection("/user/masterbird");
		selection.tell(pBestMsg, getSelf());
	}

	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof GBestMsg){
			gBest = ((GBestMsg) msg).getValue();
			//更新速度
			for (int i = 0; i < velocity.size(); i++) {
				updateVelocity(i);
			}
			//更新位置
			for (int i = 0; i < x.size(); i++) {
				updateX(i);
			}
			validatex();
			double newFit = Fitness.fitness(x);
			if(newFit>pBest.value){
				pBest = new PsoValue(newFit,x);
				PBestMsg pBestMsg = new PBestMsg(pBest);
				getSender().tell(pBestMsg,getSelf());
			}
		}else{
			unhandled(msg);
		}
	}
	//更新速度
	public double updateVelocity(int i){
		double v = Math.random()*velocity.get(i)+
				2*Math.random()*(pBest.getX().get(i)-x.get(i))
				+2*Math.random()*(gBest.getX().get(i)-x.get(i));
		v = v>0?Math.min(v, 5):Math.max(v, -5);
		velocity.set(i, v);
		return v;
	}
	//更新位置
	public double updateX(int i){
		double newX = x.get(i)+velocity.get(i);
		x.set(i, newX);
		return newX;
	}
	
	public void validatex(){
		if(x.get(1)>400){
			x.set(1, (double)r.nextInt(401));
		}
		
		//x2
		double max = 400-1.1*x.get(1);
		if(x.get(2)>max || x.get(2)<0){
			x.set(2, r.nextDouble()*max);
		}
		
		//x3
		max = 484-1.21*x.get(1)-1.1*x.get(2);
		if(x.get(3)>max || x.get(3)<0){
			x.set(3, r.nextDouble()*max);
		}
		
		//x4
		max = 532.4-1.331*x.get(1)-1.21*x.get(2)-1.1*x.get(3);
		if(x.get(4)>max || x.get(4)<0){
			x.set(4, r.nextDouble()*max);
		}
	}
}
