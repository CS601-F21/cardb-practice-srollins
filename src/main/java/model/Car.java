package model;

/**
 * Car class
 *
 */
public abstract class Car implements Comparable<Car> {

	protected String model;
	protected String vehicleClass;
	protected int pollutionScore;
	
	/**
	 * Constructor
	 * @param model, vehicleClass, pollutionScore
	 * 
	 */
	public Car(String model, String vehicleClass, int pollutionScore) {
		this.model = model;
		this.vehicleClass = vehicleClass;
		this.pollutionScore = pollutionScore;
	}
	
	/**
	 * Return the model value.
	 * @return
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * Return the vehicle class value.
	 * @return
	 */
	public String getVehicleClass() {
		return vehicleClass;
	}
	
	/**
	 * Return the pollution score value.
	 * @return
	 */
	public int getPollutionScore() {
		return pollutionScore;
	}
	
	/**
	 * Compare Car objects.
	 * @return
	 */
	public int compareTo(Car car) {
		if(pollutionScore == car.getPollutionScore()) {
			return model.compareTo(car.getModel());
		} else {
			return pollutionScore - car.getPollutionScore();
		}
	}

}
