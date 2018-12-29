package reflect;


public class TestMain {
	public static void main(String[] args) {
		System.out.println(XYZ.name);
	}
}

class XYZ{
	public static String name = "luoxn28";
	
	static{
		System.out.println("xyzæ≤Ã¨øÈ");
	}
	
	public XYZ(){
		System.out.println("xyzπªππ‘Ï¡À");
	}
}
