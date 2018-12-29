package FactoryModel;

public class BusFactory implements ICarFactory {

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return new Bus();
	}

}
