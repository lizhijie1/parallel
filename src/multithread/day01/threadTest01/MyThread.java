package multithread.day01.threadTest01;

public class MyThread extends Thread{
	/*@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println("MyThread");
	}*/
	/*@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			int time = (int) (Math.random()*1000);
			Thread.sleep(time);
			System.out.println("run:"+Thread.currentThread().getName());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}*/
	/*private int i;
	public MyThread(int i){
		super();
		this.i=i;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(i);
	}*/
	//synchronized 安全锁
	private int count = 5;
	@Override
	synchronized public void run() {
		// TODO Auto-generated method stub
		super.run();
		count--;
		System.out.println("由:"+this.currentThread().getName()+"计算，count："+count);
	}
}
