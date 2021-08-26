package practice.cardb;

import java.io.*;
import java.nio.file.Paths;

import model.GasCar;
import model.GreenCar;
import model.Car;
import model.CarList;

/**
 * Driver.
 *
 */
public class CarDBDriver{

	public static final String IN_FLAG = "-in";
	public static final String OUT_FLAG = "-out";

	/**
	 * Helper method to check for valid arguments.
	 */
	private static boolean validArgs(String[] args) {
		if(args.length != 4 ||
				!args[0].equals(IN_FLAG) ||
				!args[2].equals(OUT_FLAG)) {
			return false;
		}
		return true;
	}

	/**
	 * Helper method to print usage.
	 */
	private static void usage() {
		System.out.println("Expected usage:\n" +
				"java practice.CarDBDriver -in <relative path to cars.csv> -out <output file name>;");
	}

	/**
	 * Main logic
	 *
	 * Expected usage:
	 * 	java practice.CarDBDriver -in <relative path to cars.csv> -out <output file name>
	 *
	 * Example:
	 *  java practice.CarDBDriver -in cars.csv -out actual.txt
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		if (!validArgs(args)) {
			usage();
			System.exit(1);
		}

		CarList carList = buildCarList(args[1]);
		saveCarList(args[3], carList);
	}

	/**
	 * Saves the car list data.
	 * @param outFile
	 * @param carList
	 */
	private static void saveCarList(String outFile, CarList carList) {
		try (PrintWriter out = new PrintWriter(outFile)) {

			StringBuffer output = new StringBuffer("");
			output.append("All Cars:\n");
			output.append(carList+"\n");

			output.append("Green Cars - Fuel Type:\n");
			output.append(carList.toStringGreenCars() + "\n");

			output.append(String.format("Average MPG All: %.2f\n\n", carList.avgMpg()));
			output.append(String.format("Average MPG Subaru: %.2f\n\n", carList.avgMpgByPartialModel("SUBARU")));
			output.append(String.format("Average MPG Toyota: %.2f\n\n", carList.avgMpgByPartialModel("TOYOTA")));
			output.append(String.format("Average MPG Ferrari: %.2f\n\n", carList.avgMpgByPartialModel("FERRARI")));

			output.append("Vehicle Classes with 4-Cylinder Cars:\n");
			String[] result = carList.findClassesByCylinders(4);
			for(String str : result) {
				output.append("\t"+str+"\n");
			}

			output.append("Vehicle Classes with 6-Cylinder Cars:\n");
			result = carList.findClassesByCylinders(6);
			for(String str : result) {
				output.append("\t"+str+"\n");
			}

			output.append("Small SUVs with MPG > 22:\n");
			result = carList.findModelsByClassAndMpg("small SUV", 22);
			for(String str : result) {
				output.append("\t"+str+"\n");
			}

			output.append("Small Cars with MPG > 35:\n");
			result = carList.findModelsByClassAndMpg("small car", 35);
			for(String str : result) {
				output.append("\t"+str+"\n");
			}

			out.write(output.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts input to CarList.
	 * @return
	 */
	private static CarList buildCarList(String inFile) {
		CarList cars = new CarList();
		try (BufferedReader in = new BufferedReader(new FileReader(inFile))) {
			Car car;
			in.readLine(); // ignore header line
			String line = in.readLine();
			while (line != null) {
				String[] carStrParts = line.split(",");
				if (carStrParts[5].equals("Hydrogen") || carStrParts[5].equals("Electricity")) {
					String model = carStrParts[0];
					String vehicleClass = carStrParts[10];
					int pollutionScore = Integer.parseInt(carStrParts[11]);
					String fuelType = carStrParts[5];
					car = new GreenCar(model, vehicleClass, pollutionScore, fuelType);
					cars.addCar(car);
				} else {
					String model = carStrParts[0];
					String vehicleClass = carStrParts[10];
					int pollutionScore = Integer.parseInt(carStrParts[11]);
					int numberCylinders = Integer.parseInt(carStrParts[2]);
					int mpg = Integer.parseInt(carStrParts[14].split("/")[0]);
					car = new GasCar(model, vehicleClass, pollutionScore, numberCylinders, mpg);
					cars.addCar(car);
				}
				line = in.readLine();
			}
		} catch(IOException ioe) {
			System.out.println(ioe.getMessage());
		}
		return cars;
	}
}
