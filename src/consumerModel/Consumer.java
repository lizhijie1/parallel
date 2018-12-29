package consumerModel;

public class Consumer implements Runnable{
	private SyncStack stack;
	public Consumer(SyncStack stack){
		this.stack = stack;
	}
	@Override
	public void run() {
		for (int i = 0; i < stack.pro().length; i++) {
			String consumer = stack.pop();
			System.out.println("�����ˣ�"+consumer);
			try {
				Thread.sleep(400);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
