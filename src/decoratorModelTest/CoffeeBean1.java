package decoratorModelTest;
/**
 * ���幹������
 * @author Administrator
 *
 */
public class CoffeeBean1 implements Beverage{
	private String description = "ѡ�˵�һ�ֿ��ȶ�";
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
