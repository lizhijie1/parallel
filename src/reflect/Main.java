package reflect;

public class Main {
	public static void main(String[] args) {
		//�����ʼ����̬��
		Class clazz1 = Base.class;
		System.out.println("----------");
		//���ʼ��
		try {
			Class clazz2 = Class.forName("zzz.Base");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
