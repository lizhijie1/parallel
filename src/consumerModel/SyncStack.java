package consumerModel;
/**
 * 此类是共享数据区域
 * @author Administrator
 *
 */
public class SyncStack {
	private String[] str = new String[10];
	
	private int index;
	
	//共生产者调用
	public synchronized void push(String sst){
		if(index == str.length){
			try {
				wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		this.notify();
		str[index] =sst;
		index++;
	}
	//供消费者调用
	public synchronized String pop(){
		if(index == 0){
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.notify();
		index--;
		String product = str[index];
		return product;
	}
	
	public String[] pro(){
		return str;
	}
	
}
