package ProxyToAop;

public class ProxyHello implements IHello {
	private IHello ihello;
	public ProxyHello(IHello ihello){
		this.ihello = ihello;
	}
	@Override
	
	public void sayHello(String str) {
		// TODO Auto-generate	d method stub
		Logger.start();
		ihello.sayHello(str);
		Logger.end();
	}

}
