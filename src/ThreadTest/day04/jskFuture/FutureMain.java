/**
 * FutureMain.java	  V1.0   2018年12月18日zhe 下午10:20:51
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day04.jskFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * 
 * 功能描述：jdk中FUTURE的应用举例
 *
 * @author ：lizhijie
 *
 * 修改历史：(修改人，修改时间，修改原因/内容)
 */
public class FutureMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//构造FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("a"));
		ExecutorService service = Executors.newFixedThreadPool(1);
		//在这里开启线程进行RealData的call执行
		service.submit(future);
		
		System.out.println(System.currentTimeMillis()+"请求完毕");
		try {
			//这里依然可以在做额外的数据操作，这里使用sleep代替其他业务逻辑的处理
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis()+"数据 ="+future.get());
	}
}
