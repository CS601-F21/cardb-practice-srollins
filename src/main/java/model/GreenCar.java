package model;

import model.Car;

/**
 * GreenCar class
 *
 */
public class GreenCar extends Car{

	private String fuelType;
	
	/**
	 * Constructor
	 * @param model, vehicleClass, pollutionScore, fuelType
	 * 
	 */
	public GreenCar(String model, String vehicleClass, int pollutionScore, String fuelType) {
		super(model, vehicleClass, pollutionScore);
		this.fuelType = fuelType;
	}
	
	/**
	 * Return fuel type of a green car.
	 * @return
	 */
	public String getFuelType() {
		return fuelType;
	}
	
}
