package DriverSynchronization;

public class Driver extends Thread {
	private Company com;
	private String name;
	public Driver(String name, Company c){
		this.com = c;
		this.name = name;
	}
	
	public void run(){
		for(int i = 0; i < 3; i++)
			this.com.add(50, this.name);
		
	}
}
