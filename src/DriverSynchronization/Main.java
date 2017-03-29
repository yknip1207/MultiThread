package DriverSynchronization;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Company yahoo = new Company();
		Driver alex = new Driver("Alex", yahoo);
		alex.start();
		Driver bryce = new Driver("Bryce", yahoo);

		bryce.start();

		
	}

}
