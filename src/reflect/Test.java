package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
	@org.junit.Test
	public void test1(){
		//forName()��ȡ����
		StringBuilder str = new StringBuilder("123");
		System.out.println(str.getClass());
		System.out.println(Integer.class);
		try {
			Class<?> clazz = Class.forName("java.lang.StringBuilder");
			System.out.println(clazz.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@org.junit.Test
	public void test2() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		/**
		 * 
		 */
		Class<?> c = String.class;
		try {
			Object str = c.newInstance();
			System.out.println(str);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * ͨ�����췽������ʵ��
		 */
		Class<?> cla = String.class;
		
		try {
			Constructor con = cla.getConstructor(String.class);
			//���ݹ���������ʵ��
			Object obj = con.newInstance("23333");
			System.out.println(obj);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
