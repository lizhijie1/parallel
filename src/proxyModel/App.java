package proxyModel;

public class App {
	public static void main(String[] args) {
		//Ŀ�����
		UserDao userDao = new UserDao();
		
		//�������
		UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
		
		//ִ�д�����
		userDaoProxy.save();
	}
}
