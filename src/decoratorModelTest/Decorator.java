package decoratorModelTest;

public class Decorator implements Beverage {
	private String description="��ֻ��װ��������֪�����������";
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
