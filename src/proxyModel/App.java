package proxyModel;

public class App {
	public static void main(String[] args) {
		//目标对象
		UserDao userDao = new UserDao();
		
		//代理对象
		UserDaoProxy userDaoProxy = new UserDaoProxy(userDao);
		
		//执行代理方法
		userDaoProxy.save();
	}
}
