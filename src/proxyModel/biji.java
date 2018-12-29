package proxyModel;

public class biji {
	/**
	 * ����ģʽ��
	 * 	��һ�����ģʽ���ṩ�˶�Ŀ���������ķ��ʷ�ʽ����ͨ������������Ŀ������������ĺô���
	 * 	������Ŀ�����ʵ�ֵĻ����ϣ���ǿ����Ĺ��ܲ���������չĿ�����Ĺ���
	 * ���˼�룺
	 *	��Ҫ����ȥ�޸ı����Ѿ�д�õĴ���򷽷��������Ҫ�޸ģ�����ͨ������ķ�ʽ����չ�÷���
	 * �����ؼ���
	 * 	��������Ƕ�Ŀ��������չ���������Ŀ�����
	 * 
	 * 1.1��̬����
	 * 	��̬����ʹ��ʱ����Ҫ����ӿڻ��߸��࣬���������ʹ������һ��ʵ����ͬ�Ľӿڻ��߼̳���ͬ�ĸ���
	 * ����ʾ��
	 * 	//�ӿ���
	 * 	public interface IUserDao {
 			void save();
 		}
	 * 	//Ŀ�����
	 * 	public class UserDao implements IUserDao {
		    public void save() {
		        System.out.println("----�Ѿ���������!----");
		    }
		}
		//��̬�������
		public class UserDaoProxy implements IUserDao{
		    //���ձ���Ŀ�����
		    private IUserDao target;
		    public UserDaoProxy(IUserDao target){
		        this.target=target;
		    }
		
		    public void save() {
		        System.out.println("��ʼ����...");
		        target.save();//ִ��Ŀ�����ķ���
		        System.out.println("�ύ����...");
		    }
		}
		//app������
		 public class App {
		    public static void main(String[] args) {
		        //Ŀ�����
		        UserDao target = new UserDao();
		
		        //�������,��Ŀ����󴫸��������,���������ϵ
		        UserDaoProxy proxy = new UserDaoProxy(target);
		
		        proxy.save();//ִ�е��Ǵ���ķ���
		    }
		}
		��̬�����ܽ�
		1.���������ڲ��޸�Ŀ�����Ĺ���ǰ���£���Ŀ�깦����չ
		2.ȱ�㣺
			��Ϊ���������Ҫ��Ŀ�����ʵ��һ���Ľӿڣ����Ի��кܶ�Ĵ����࣬��̫�࣬ͬʱһ���ӿ����ӷ�����Ŀ��
			��������������Ҫά����
			


		1.2��̬����
		��̬�����������ص㣺
			1.���������Ҫʵ�ֽӿ�
			2.�����������ɣ�������JDK��API,��̬�����ڴ��й������������Ҫ����ָ�������������/Ŀ�����ʵ�ֵ�
			�ӿڵ����ͣ�
			3.��̬����Ҳ������JDK�����ӿڴ���
		JDK�����ɴ�������API
			���������ڰ���java.lang.reflect.Proxy
			JDKʵ�ִ���ֻ��ʹ��newProxyInstance���������Ǹ÷�����Ҫ��������������������д���ǣ�
			
				static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
			
			ע��÷�������Proxy�����Ǿ�̬�������ҽ��ܵ���������Ϊ
				
				ClassLoader loader,ָ����ǰĿ�����ʹ�������������ȡ�������ķ����ǹ̶���
				
				Class<?>[] interfaces,:Ŀ�����ʵ�ֵĽӿ����ͣ�ʹ�÷��ͷ�ʽȷ������
				
				InvocationHandler h�����¼�����ִ��Ŀ�����ķ���ʱ���ᴥ���¼��������ķ�������ѵ�ǰִ��Ŀ�����ķ���Ϊ��������
		public class ProxyFactory{
		    //ά��һ��Ŀ�����
		    private Object target;
		    public ProxyFactory(Object target){
		        this.target=target;
		    }
		
		   //��Ŀ��������ɴ������
		    public Object getProxyInstance(){
		        return Proxy.newProxyInstance(
		                target.getClass().getClassLoader(),
		                target.getClass().getInterfaces(),
		                new InvocationHandler() {
		                    @Override
		                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		                        System.out.println("��ʼ����2");
		                        //ִ��Ŀ����󷽷�
		                        Object returnValue = method.invoke(target, args);
		                        System.out.println("�ύ����2");
		                        return returnValue;
		                    }
		                }
		        );
		    }
		
		}
	
	 *	1.3Cglib����ģʽ
	 *		����ľ�̬����Ͷ�̬����ģʽ����Ҫ��Ŀ�������ʵ��һ���ӿڵ�Ŀ����󣬵�����ʱ��Ŀ�����ֻ��һ�������Ķ��󣬲�û��ʵ���κε�
	 *		�κεĽӿڣ����ʱ��Ϳ���ʹ����Ŀ���������ķ�ʽ��ʵ�ִ������ַ���������Cglib����
	 *
	 *		Cglib����Ҳ������������������ڴ��й���һ���������Ӷ�ʵ�ֶ�Ŀ������ܵ���չ
	 *
	 *			1.JDK�Ķ�̬������һ�����ƣ�����ʹ�ö�̬����Ķ������ʵ��һ�������ӿڣ���������û��ʵ�ֽӿڵ��࣬�Ϳ���ʹ��Cglibʵ��
	 *
	 *			2.Cglib��һ��ǿ��ĸ����ܴ������ɰ�������������������չjava����ʵ��java��ڣ����㷺�ı����AOP�Ŀ��ʹ�ã�����Spring AOP��
	 *				synaop,Ϊ�����ṩ�ḻ��interception(����)
	 *
	 *			3.Cglib���ĵײ���ͨ��ʹ��һ��С������ֽ��봦����ASM��ת���ֽ��벢�����µ���,������ֱ��ʹ��ASM,��Ϊ��Ҫ��������JVM�ڲ��ṹ
	 *				����class�ļ��ĸ�ʽ��ָ�������Ϥ��
	 *
	 *		Cglib�������ʵ�ַ���
	 *			
	 *			1.��Ҫ����cglib��jar�ļ�������Spring�ĺ��İ����Ѿ�������Cglib���ܣ�����ֱ������spring-core-3.2.2.jar
	 *
	 *			2.���빦�ܰ��󣬾Ϳ������ڴ��й�������
	 *
	 *			3.������಻��Ϊfinal�����򱨴�
	 *
	 *			4.Ŀ�����ķ������Ϊfinal/static,��ô�Ͳ��ᱻ���أ�������ִ��Ŀ���������ҵ�񷽷�
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 */
	
}
