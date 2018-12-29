package decoratorModelTest;

public class Decorator implements Beverage {
	private String description="我只是装饰器，不知道具体的描述";
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

}
