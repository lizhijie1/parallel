package consumerModel;
/**
 * �����ǹ�����������
 * @author Administrator
 *
 */
public class SyncStack {
	private String[] str = new String[10];
	
	private int index;
	
	//�������ߵ���
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
	//�������ߵ���
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
