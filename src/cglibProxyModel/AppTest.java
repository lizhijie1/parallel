package cglibProxyModel;

import org.junit.Test;


public class AppTest {
	@Test
	public void test(){
		//����Ŀ�����
		UserDao target = new UserDao();
		
		//�������
		UserDao proxy = (UserDao) new ProxyFactory(target).getProxyInstance();
		
		
		proxy.save();
	}
	
}
