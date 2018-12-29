package annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义注解
 * @author Administrator
 *
 */
public class Test {
	
	
	public static void main(String[] args){
		Class clazz = Test.class;
		Method[] methods = clazz.getMethods();
		for (Method method: methods) {
			boolean flag = method.isAnnotationPresent(MyTest.class);
			if(flag){
				try {
					method.invoke(clazz.newInstance(), null);
					System.out.println("----------------");
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@MyTest
	public void method2(){
		System.out.println("Hello Annotation");
	}
	
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyTest{
	
}
