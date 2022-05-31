package Excepciones;

public class IncompleteException extends Exception{
	public IncompleteException(String message) {
		super("Datos Incompletos: "+ message);		
	}
	
}
