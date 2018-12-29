/**
 * StopThreadUnsafe.java	  V1.0   2018年12月14日 下午9:39:03
 *
 * Copyright 2018 FUJIAN FUJITSU COMMUNICATION SOFTWARE CO., LTD. All rights reserved.
 *
 * Modification history(By    Time    Reason):
 * 
 * Description:
 */

package ThreadTest.day01;

public class StopThreadUnsafe {
	
	public static User user = new User();
	
	public static class User{
		private int id;
		private String name;
		public User() {
			this.id = 0;
			this.name = "0";
		}
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + "]";
		};
	}
	
	public static class ChangeObjectThread extends Thread{
		volatile boolean stopme = false;
		
		@Override
		public void run() {
			while(true){
				if(stopme){
					System.out.println("exit by stop me!!");
				}
				synchronized(user){
					int v = (int) (System.currentTimeMillis()/1000);
					user.setId(v);
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					user.setName(String.valueOf(v));
				}
				Thread.yield();
			}
		}
	}
	
	public static class ReadObjectThread extends Thread{
		@Override
		public void run() {
			while(true){
				synchronized(user){
					if(user.getId() !=Integer.parseInt(user.getName())){
						System.out.println(user.toString());
					}
				}
				Thread.yield();//让步
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new ReadObjectThread().start();
		while(true){
			ChangeObjectThread t1 = new ChangeObjectThread();
			t1.start();
			Thread.sleep(150);
			//t1.stop();
			t1.stopme = true;
		}
	}
}
