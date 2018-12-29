package multithread.day01.threadTest01;

public class Test {
	public static void main(String[] args) {
		/*try {
			MyThread thread = new MyThread();
			thread.setName("MyThread");
			thread.start();
			for (int i = 0; i < 10; i++) {
				int time = (int) (Math.random()*1000);
				thread.sleep(time);
				System.out.println("main:"+Thread.currentThread().getName());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		//执行start()方法的顺序不代表线程的启动的顺序
		/*MyThread thread1 = new MyThread(1);	
		MyThread thread2 = new MyThread(2);
		MyThread thread3 = new MyThread(3);
		MyThread thread4 = new MyThread(4);
		MyThread thread5 = new MyThread(5);
		MyThread thread6 = new MyThread(6);
		MyThread thread7 = new MyThread(7);
		MyThread thread8 = new MyThread(8);
		MyThread thread9 = new MyThread(9);
		MyThread thread10 = new MyThread(10);
		MyThread thread11 = new MyThread(11);
		MyThread thread12 = new MyThread(12);
		
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
		thread6.start();
		thread7.start();
		thread8.start();
		thread9.start();
		thread10.start();
		thread11.start();
		thread12.start();*/
		//存在线程安全问题
		MyThread thread = new MyThread();
		Thread a = new Thread(thread,"a");
		Thread b = new Thread(thread,"b");
		Thread c = new Thread(thread,"c");
		Thread d = new Thread(thread,"d");
		Thread e = new Thread(thread,"e");
		
		a.start();
		b.start();
		c.start();
		d.start();
		e.start();

	}
}
