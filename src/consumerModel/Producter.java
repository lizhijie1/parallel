package consumerModel;

import java.util.Stack;

public class Producter implements Runnable {
	private SyncStack syncStack;
	
	public Producter(SyncStack  syncStack){
		this.syncStack = syncStack;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (int i = 0; i < syncStack.pro().length; i++) {
			String producter = "产品"+i;
			syncStack.push(producter);
			System.out.println("生产了："+producter);
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
