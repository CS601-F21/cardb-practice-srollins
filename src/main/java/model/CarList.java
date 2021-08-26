package model;

import java.util.Arrays;

import model.Car;
import model.GasCar;
import model.GreenCar;

/**
 * A list of Car objects.
 *
 */
public class CarList {

	public static final int INITIAL_SIZE = 10;

	private int size;
	private Car[] cars;
	
	/**
	 * Constructor
	 * @param
	 * 
	 */
	public CarList() {
		this.cars = new Car[INITIAL_SIZE];
		this.size = 0;
	}
	
	/**
	 * Method to add car object to array/list of cars in a sorted manner.
	 * @return
	 */
	public void addCar(Car car) {
		if(cars.length == size) {
			resize();
		}
		cars[size] = car;
		size++;

		for(int i = size-1; i > 0; i--) {
			if(cars[i].compareTo(cars[i-1]) <= 0) {
				Car tmp = cars[i-1];
				cars[i-1] = cars[i];
				cars[i] = tmp;
			} else {
				break;
			}
		}
	}

	/**
	 * Helper method to resize array when full.
	 */
	private void resize() {
		Car[] tmp = new Car[cars.length*2];
		System.arraycopy(cars, 0, tmp, 0, cars.length);
		cars = tmp;
	}

	/**
	 * Helper method to determine if a String array contains a target String.
	 * @param values
	 * @param valuesSize
	 * @param target
	 * @return
	 */
	private boolean contains(String[] values, int valuesSize, String target) {

		for(int i = 0; i < valuesSize; i++) {
			if(values[i].equals(target)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Helper
	 */
	private String[] resizeStringArray(String[] original) {
		String[] larger = new String[original.length*2];
		System.arraycopy(original, 0, larger, 0, original.length);
		return larger;
	}

	/**
	 * Returns the string representation of Car Object. 
	 *
	 * @return
	 */
	public String toString() {
		StringBuffer toReturn = new StringBuffer("");
		for(int i = 0; i < size; i++) {
			if(cars[i] instanceof GreenCar) {
				GreenCar greenCar = (GreenCar) cars[i];
				toReturn.append("Model: " + greenCar.getModel() +
						" Class: " + greenCar.getVehicleClass() +
						" Pollution Score: " + greenCar.getPollutionScore() +
						" Fuel Type: " + greenCar.getFuelType() +
						"\n");
			}
			else if (cars[i] instanceof GasCar){
				GasCar gasCar = (GasCar) cars[i];
				toReturn.append("Model: " + gasCar.getModel() +
						" Class: " + gasCar.getVehicleClass() +
						" Pollution Score: " + gasCar.getPollutionScore() +
						" MPG: " + gasCar.getMpg() +
						" Cylinders: " + gasCar.getNumberCylinders() +
						"\n");
			}
		}
		return toReturn.toString();
	}
	
	/**
	 * Returns the string representation of GreenCar Object.
	 * @return
	 */
	public String toStringGreenCars() {
		StringBuffer toReturn = new StringBuffer("");
		for(int i = 0; i < size; i++) {
			if(cars[i] instanceof GreenCar) {
				GreenCar greenCar = (GreenCar) cars[i];
				toReturn.append("Model: " + greenCar.getModel() +
						" Fuel Type: " + greenCar.getFuelType() +
						"\n");
			}
		}
		return toReturn.toString();
	}
	
	/**
	 * Returns MPG of GasCar objects.
	 * @return
	 */
	public double avgMpg() {
		double sum = 0;
		int count = 0;
		for(int i = 0; i < size; i++) {
			if(cars[i] instanceof GasCar) {
				GasCar gasCar = (GasCar) cars[i];
				sum += gasCar.getMpg();
				count++;
			}
		}
		return (sum/count);
	}

	/**
	 * Returns  MPG of GasCar objects
	 * @return
	 */
	public double avgMpgByPartialModel(String model) {
		double sum = 0;
		int count = 0;
		for(int i = 0; i < size; i++) {
			if(cars[i] instanceof GasCar) {
				GasCar gasCar = (GasCar) cars[i];
				if(gasCar.getModel().toUpperCase().contains(model.toUpperCase())) {
					sum += gasCar.getMpg();
					count++;
				}
			}
		}
		return (sum/count);
	}
	
	/**
	 * Takes as input an int specifying number of cylinders and returns a String[]
	 * containing the vehicle classes with models that have the specified number
	 * of cylinders. For full credit, the String[] will have no repeated
	 * elements (each class will only appear once) and the length must be
	 * large enough to accommodate only the number of valid elements. The
	 * resulting array will be sorted and you may use Arrays.sort for this purpose.
	 */
	public String[] findClassesByCylinders(int cylinderCount) {
		String[] classes = new String[INITIAL_SIZE];
		int classesSize = 0;
		for(int i = 0; i < size; i++) {
			if(cars[i] instanceof GasCar) {
				GasCar gasCar = (GasCar) cars[i];
				if(gasCar.getNumberCylinders() == cylinderCount &&
						!contains(classes, classesSize, gasCar.getVehicleClass())) {
					classes[classesSize++] = gasCar.getVehicleClass();
					if(classes.length == classesSize) {
						classes = resizeStringArray(classes);
					}
				}
			}
		}
		String[] returnArray = new String[classesSize];
		System.arraycopy(classes, 0, returnArray, 0, classesSize);
		Arrays.sort(returnArray);
		return returnArray;
	}
	
	/**
	 *Takes as input a target vehicle class and a minimum MPG and returns a
	 * String[] containing the models of all vehicles of the specified class
	 * that have at least the specified combined MPG. The String[] may have
	 * repeated elements if the same model appears twice in the dataset, but
	 * the the length must be large enough to accommodate only the number of
	 * valid elements. The resulting array will be sorted and you may use
	 * Arrays.sort for this purpose.
	 */
	public String[] findModelsByClassAndMpg(String vehicleClass, int mpg) {
		String[] models = new String[INITIAL_SIZE];
		int modelsSize = 0;
		for(int i = 0; i < size; i++) {
			if(cars[i] instanceof GasCar) {
				GasCar gasCar = (GasCar) cars[i];
				if(gasCar.getVehicleClass().equals(vehicleClass) &&
						gasCar.getMpg() >= mpg) {
					models[modelsSize++] = gasCar.getModel();
					if(models.length == modelsSize) {
						models = resizeStringArray(models);
					}
				}
			}
		}
		String[] returnArray = new String[modelsSize];
		System.arraycopy(models, 0, returnArray, 0, modelsSize);
		Arrays.sort(returnArray);
		return returnArray;

	}
}
