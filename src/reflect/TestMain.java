package reflect;


public class TestMain {
	public static void main(String[] args) {
		System.out.println(XYZ.name);
	}
}

class XYZ{
	public static String name = "luoxn28";
	
	static{
		System.out.println("xyz��̬��");
	}
	
	public XYZ(){
		System.out.println("xyz��������");
	}
}
