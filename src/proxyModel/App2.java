package proxyModel;

public class App2 {
	public static void main(String[] args) {
		//Ŀ�����
		IUserDao target = new UserDao();
		System.out.println(target.getClass());
		//��Ŀ����󴴽��������
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		System.out.println(proxy.getClass());
		//ִ�з���
		proxy.save();
	}
}
