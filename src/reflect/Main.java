package reflect;

public class Main {
	public static void main(String[] args) {
		//不会初始化静态块
		Class clazz1 = Base.class;
		System.out.println("----------");
		//会初始化
		try {
			Class clazz2 = Class.forName("zzz.Base");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
