package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
	public static void main(String[] args) throws Exception, Exception {
		Class clazz = Student.class;
		//获取构造器
		Constructor[] constructors=clazz.getConstructors();
		for (Constructor constructor : constructors) {
			String name = constructor.getName();
			System.out.println(name);
		}
		System.out.println("------------------");
		//获取公有的属性
		//Field[] fields = clazz.getFields();
		//获取所有属性
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		System.out.println("-------------------");
		
		//获取公有方法
		//Method[] methods = clazz.getMethods();
		//获取所有方法
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		System.out.println("--------------");
		//反射创建实例
		Student student = (Student) clazz.newInstance();
		/**
		 * 有参数的构造器创建实例
		 * 1.得到构造器
		 * 2.通过构造器创建实例
		 */
		Constructor<Student> con = clazz.getConstructor(String.class,int.class,int.class);
		Student student1= con.newInstance("黎明",23,12);
		student1.goClass();
		System.out.println("-----------------");
		Method method = clazz.getDeclaredMethod("goBack",null);
		method.setAccessible(true);
		method.invoke(clazz.newInstance(), null);
	}
}
