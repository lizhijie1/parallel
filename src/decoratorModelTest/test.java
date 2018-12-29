package decoratorModelTest;

import org.junit.Test;

public class test {
	@Test
	public void test1(){
		Beverage beverage = new CoffeeBean1();
		beverage = new Milk(beverage);
		beverage = new Mocha(beverage);
		System.out.println(beverage.getDescription()+"\n¼Û¸ñÊÇ"+beverage.getPrice());
	}
}
