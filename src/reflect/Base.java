package reflect;
/**
 * 类加载器首先会检查这个类的Class对象是否已被加载过，如果尚未加载，默认的类加载器就会根据类名查找对应的.class文件。
 * 想在运行时使用类型信息，必须获取对象的Class对象的引用，使用功能Class.forName("Base")可以实现该目得，或者使用
 * basc.class.注意，有一点很有趣，使用功能“.class”来创建Class对象的引用时，不会自动初始化该Class对象，使用forName()
 * 会自动初始化该Class对象。为了使用类而做的准备工作一般有以下3个步骤：
 * 		1.加载：由类加载器完成，找到对应的字节码，创建一个Class对象
 * 
 * 		2.链接： 验证类中的字节码，为静态域分配空间
 * 
 * 		3.初始化：如果该类有超类，则对其初始化，执行静态初始化器和静态初始化块
 * 
 * @author Administrator
 *
 */
public class Base {
	static int num = 1;
	static{
		System.out.println("Base"+ num);
	}

}
