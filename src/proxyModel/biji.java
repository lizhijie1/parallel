package proxyModel;

public class biji {
	/**
	 * 代理模式：
	 * 	是一种设计模式，提供了对目标对象另外的访问方式，即通过代理对象访问目标对象，这样做的好处是
	 * 	可以在目标对象实现的基础上，增强额外的功能操作，即拓展目标对象的功能
	 * 编程思想：
	 *	不要随意去修改别人已经写好的代码或方法，如果需要修改，可以通过代理的方式来拓展该方法
	 * 技术关键点
	 * 	代理对象是对目标对象的扩展，并会调用目标对象
	 * 
	 * 1.1静态代理
	 * 	静态代理使用时，需要定义接口或者父类，被代理对象和代理对象一起实现相同的接口或者继承相同的父类
	 * 代码示例
	 * 	//接口类
	 * 	public interface IUserDao {
 			void save();
 		}
	 * 	//目标对象
	 * 	public class UserDao implements IUserDao {
		    public void save() {
		        System.out.println("----已经保存数据!----");
		    }
		}
		//静态代理对象
		public class UserDaoProxy implements IUserDao{
		    //接收保存目标对象
		    private IUserDao target;
		    public UserDaoProxy(IUserDao target){
		        this.target=target;
		    }
		
		    public void save() {
		        System.out.println("开始事务...");
		        target.save();//执行目标对象的方法
		        System.out.println("提交事务...");
		    }
		}
		//app测试类
		 public class App {
		    public static void main(String[] args) {
		        //目标对象
		        UserDao target = new UserDao();
		
		        //代理对象,把目标对象传给代理对象,建立代理关系
		        UserDaoProxy proxy = new UserDaoProxy(target);
		
		        proxy.save();//执行的是代理的方法
		    }
		}
		静态代理总结
		1.可以做到在不修改目标对象的功能前提下，对目标功能扩展
		2.缺点：
			因为代理对象需要与目标对象实现一样的接口，所以会有很多的代理类，类太多，同时一旦接口增加方法，目标
			对象与代理对象都需要维护；
			


		1.2动态代理
		动态代理有以下特点：
			1.代理对象不需要实现接口
			2.代理对象的生成，是利用JDK的API,动态的在内存中构建代理对象（需要我们指定创建代理对象/目标对象实现的
			接口的类型）
			3.动态代理也叫做：JDK代理，接口代理
		JDK中生成代理对象的API
			代理类所在包：java.lang.reflect.Proxy
			JDK实现代理只需使用newProxyInstance方法，但是该方法需要接受三个参数，完整的写法是：
			
				static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces,InvocationHandler h )
			
			注意该方法是在Proxy类中是静态方法，且接受的三个参数为
				
				ClassLoader loader,指定当前目标对象使用类加载器，获取加载器的方法是固定的
				
				Class<?>[] interfaces,:目标对象实现的接口类型，使用泛型方式确认类型
				
				InvocationHandler h，：事件处理，执行目标对象的方法时，会触发事件处理器的方法，会把当前执行目标对象的分作为参数传入
		public class ProxyFactory{
		    //维护一个目标对象
		    private Object target;
		    public ProxyFactory(Object target){
		        this.target=target;
		    }
		
		   //给目标对象生成代理对象
		    public Object getProxyInstance(){
		        return Proxy.newProxyInstance(
		                target.getClass().getClassLoader(),
		                target.getClass().getInterfaces(),
		                new InvocationHandler() {
		                    @Override
		                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		                        System.out.println("开始事务2");
		                        //执行目标对象方法
		                        Object returnValue = method.invoke(target, args);
		                        System.out.println("提交事务2");
		                        return returnValue;
		                    }
		                }
		        );
		    }
		
		}
	
	 *	1.3Cglib代理模式
	 *		上面的静态代理和动态代理模式都是要求目标对象是实现一个接口的目标对象，但是有时候目标对象只是一个单独的对象，并没有实现任何的
	 *		任何的接口，这个时候就可以使用以目标对象子类的方式类实现代理，这种方法叫做：Cglib代理
	 *
	 *		Cglib代理，也叫做子类代理，它是在内存中构建一个子类对象从而实现对目标对象功能的扩展
	 *
	 *			1.JDK的动态代理有一个限制，就是使用动态代理的对象必须实现一个或多个接口，如果想代理没有实现接口的类，就可以使用Cglib实现
	 *
	 *			2.Cglib是一个强大的高性能代码生成包，它可以在运行期扩展java类与实现java借口，它广泛的被许多AOP的框架使用，例如Spring AOP和
	 *				synaop,为他们提供丰富的interception(拦截)
	 *
	 *			3.Cglib包的底层是通过使用一个小而块的字节码处理框架ASM来转换字节码并生成新的类,不鼓励直接使用ASM,因为它要求你必须对JVM内部结构
	 *				包括class文件的格式和指令集都很熟悉。
	 *
	 *		Cglib子类代理实现方法
	 *			
	 *			1.需要引入cglib的jar文件，但是Spring的核心包中已经包括了Cglib功能，所以直接引入spring-core-3.2.2.jar
	 *
	 *			2.引入功能包后，就可以在内存中构建子类
	 *
	 *			3.代理的类不能为final，否则报错
	 *
	 *			4.目标对象的方法如果为final/static,那么就不会被拦截，即不会执行目标对象额外的业务方法
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
