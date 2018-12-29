package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
	@org.junit.Test
	public void test1(){
		//forName()获取方法
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
		 * 通过构造方法创建实例
		 */
		Class<?> cla = String.class;
		
		try {
			Constructor con = cla.getConstructor(String.class);
			//根据构造器创建实例
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
