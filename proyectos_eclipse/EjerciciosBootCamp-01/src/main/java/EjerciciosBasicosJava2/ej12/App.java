package EjerciciosBasicosJava2.ej12;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class App {

	public static void main(String[] args) {
		Car coche1 = new Car();
		Car coche2 = new Car("Rennault", "Clio", 130, Car.FuelTypes.GASOLINA);
		Car coche3 = new Car("Citroen", "Berlingo", 145, Car.FuelTypes.DIESEL);
		Car coche4 = new Car("Toyota", "Prius", 155, Car.FuelTypes.ELECTRICO);

		System.out.println("01******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	
		if(!coche1.start()) {System.err.println(coche1.getLastError());};
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.setGear(Car.Gears.D)) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};

		System.out.println("02******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	
		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		coche1.turnSteeringWheel(23);
		if(!coche1.setGear(Car.Gears.R)) {System.err.println(coche1.getLastError());};
		coche1.turnSteeringWheel(600);
		System.out.println("03******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	
		
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};

		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		System.out.println("04******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};		
		if(!coche1.brake()) {System.err.println(coche1.getLastError());};
		
		if(!coche1.setGear(Car.Gears.R)) {System.err.println(coche1.getLastError());};
		System.out.println("05******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	

		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		System.out.println("06******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	
		
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};
		if(!coche1.accelerate()) {System.err.println(coche1.getLastError());};

		coche1.turnSteeringWheel(-600);
		
		System.out.println("");
		System.out.println("07******");	
		System.out.println(coche1.showDetails());
		System.out.println("******");	
		System.out.println("******");	
		System.out.println("******");	
		System.out.println("******");	
		System.out.println("******");	
		System.out.println("******");	
		System.out.println("******");	
		System.out.println(coche2.showDetails());
		System.out.println("******");	
		System.out.println(coche3.showDetails());
		System.out.println("******");	
		System.out.println(coche4.showDetails());
		
		
	}

}
