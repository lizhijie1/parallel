package ProxyToAop;

public class Test {
	public static void main(String[] args) {
		IHello ihello  = new ProxyHello(new Hello());
		ihello.sayHello("hello");
	}
}
