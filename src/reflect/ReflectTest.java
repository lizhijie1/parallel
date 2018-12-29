package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
	public static void main(String[] args) throws Exception, Exception {
		Class clazz = Student.class;
		//��ȡ������
		Constructor[] constructors=clazz.getConstructors();
		for (Constructor constructor : constructors) {
			String name = constructor.getName();
			System.out.println(name);
		}
		System.out.println("------------------");
		//��ȡ���е�����
		//Field[] fields = clazz.getFields();
		//��ȡ��������
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}
		System.out.println("-------------------");
		
		//��ȡ���з���
		//Method[] methods = clazz.getMethods();
		//��ȡ���з���
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		System.out.println("--------------");
		//���䴴��ʵ��
		Student student = (Student) clazz.newInstance();
		/**
		 * �в����Ĺ���������ʵ��
		 * 1.�õ�������
		 * 2.ͨ������������ʵ��
		 */
		Constructor<Student> con = clazz.getConstructor(String.class,int.class,int.class);
		Student student1= con.newInstance("����",23,12);
		student1.goClass();
		System.out.println("-----------------");
		Method method = clazz.getDeclaredMethod("goBack",null);
		method.setAccessible(true);
		method.invoke(clazz.newInstance(), null);
	}
}
