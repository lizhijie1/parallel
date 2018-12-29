package decoratorModelTest;

public class Mocha extends Decorator {
	private String description = "¼ÓÁËÄ¦¿¨£¡";
	private Beverage beverage;
	public Mocha(Beverage beverage){
		this.beverage = beverage;
	}
	public String getDescription(){
		return beverage.getDescription()+"\n"+description;
	}
	public double getPrice(){
		return beverage.getPrice()+49;
	}
}
