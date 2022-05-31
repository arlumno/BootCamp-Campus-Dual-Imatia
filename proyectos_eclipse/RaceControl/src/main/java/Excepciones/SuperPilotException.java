package Excepciones;

public class SuperPilotException extends Exception{
	public SuperPilotException(String message) {
		super("Habilidad del piloto fuera de rango: "+ message);		
	}
	
}
