package cglibProxyModel;

import org.junit.Test;


public class AppTest {
	@Test
	public void test(){
		//创建目标对象
		UserDao target = new UserDao();
		
		//代理对象
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		
		
		proxy.save();
	}
	
}
