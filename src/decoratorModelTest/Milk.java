package decoratorModelTest;

public class Milk extends Decorator {
	private String description ="¼ÓÁËÅ£ÄÌ£¡";
	private Beverage beverage;
	
	public Milk(Beverage beverage){
		this.beverage = beverage;
	}
	
	public String getDescription(){
		return beverage.getDescription()+"\n"+description;
	}
	
	public double getPrice(){
		return beverage.getPrice()+20;
	}
}
