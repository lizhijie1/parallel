package proxyModel;

public class App2 {
	public static void main(String[] args) {
		//目标对象
		IUserDao target = new UserDao();
		System.out.println(target.getClass());
		//给目标对象创建代理对象
		IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
		System.out.println(proxy.getClass());
		//执行方法
		proxy.save();
	}
}
