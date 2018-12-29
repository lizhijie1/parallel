/**
 * CountTask.java	  V1.0   2018��12��16�� ����8:07:42
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day02.ThreadPool;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
/**
 * 
 * �����������ֶ���֮Fork/join���
 *
 * @author ��lizhijie
 *
 * �޸���ʷ��(�޸��ˣ��޸�ʱ�䣬�޸�ԭ��/����)
 */
public class CountTask extends RecursiveTask<Long>{
	private static final int THRESHOLD = 10000;
	
	private long start;
	
	private long end;
	
	public CountTask(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long sum = 0;
		boolean canCompute = (end-start)<THRESHOLD;
		if(canCompute){
			for (long i = start; i < end; i++) {
				sum+=i;
			}
		}else{
			//�ֳ�100��С����
			long step = (start+end)/100;
			ArrayList<CountTask> subTasks = new ArrayList<CountTask>();
			long pos = start;
			for (int i = 0; i < 100; i++) {
				long lastOne = pos+step;
				if(lastOne>end){
					lastOne = end;
				}
				CountTask subTask = new CountTask(pos,lastOne);
				pos+=step+1;
				subTasks.add(subTask);
				subTask.fork();
			}
			for (CountTask countTask : subTasks) {
				sum+=countTask.join();
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask countTask = new CountTask(0, 200000L);
		ForkJoinTask<Long> result = forkJoinPool.submit(countTask);
		Long res;
		try {
			res = result.get();
			System.out.println("sum="+res);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
}
