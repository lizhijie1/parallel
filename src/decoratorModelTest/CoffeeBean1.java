package decoratorModelTest;
/**
 * 具体构件对象
 * @author Administrator
 *
 */
public class CoffeeBean1 implements Beverage{
	private String description = "选了第一种咖啡豆";
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 50;
	}
	
}
