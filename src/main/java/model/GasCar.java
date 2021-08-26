package model;

import model.Car;

/**
 * GasCar class
 *
 */
public class GasCar extends Car {
	private int numberCylinders;
	private int mpg;
	
	/**
	 * Constructor
	 * @param model, vehicleClass, pollutionScore, numberCylinders, mpg
	 * 
	 */
	public GasCar(String model, String vehicleClass, int pollutionScore, int numberCylinders, int mpg) {
		super(model, vehicleClass, pollutionScore);
		this.numberCylinders = numberCylinders;
		this.mpg = mpg;
	}
	
	/**
	 * Return number of cylinders of a gas car.
	 * @return
	 */
	public int getNumberCylinders() {
		return numberCylinders;
	}
	
	/**
	 * Return mpg value of gas car.
	 * @return
	 */
	public int getMpg() {
		return mpg;
	}

	public String toString() {
		return "Model: " + model + "MPG: " + mpg;
	}
}
