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
			String producter = "��Ʒ"+i;
			syncStack.push(producter);
			System.out.println("�����ˣ�"+producter);
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
