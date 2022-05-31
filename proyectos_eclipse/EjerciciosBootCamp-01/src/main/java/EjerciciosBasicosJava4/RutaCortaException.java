package EjerciciosBasicosJava4;

public class RutaCortaException extends Exception {

	public RutaCortaException(String message) {
		super("Ruta muy corta: "+ message);		
	}
	
}
