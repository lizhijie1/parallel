package cglibProxyModel;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
/**
 * cglib子类代理工厂
 * 对UserDao在内存中动态构建一个子类对象
 * @author Administrator
 *
 */
public class ProxyFactory implements MethodInterceptor{
	//维护目标对象
	private Object target;
	
	public ProxyFactory(Object target){
		this.target = target;
	}
	
	//给目标对象创建动态代理对象
	public Object getProxyInstance(){
		//1.工具类
		Enhancer en = new Enhancer();
		//2.设置父类
		en.setSuperclass(target.getClass());
		//3.设置回调函数
		en.setCallback(this);
		//4.创建子类
		return en.create();
		
	}
	
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("开始事务。。。。。");
		//执行目标对象的方法
		Object returnValue = arg1.invoke(target, arg2);
		//
		System.out.println("结束事务。。。。。");
		return returnValue;
	}
}
