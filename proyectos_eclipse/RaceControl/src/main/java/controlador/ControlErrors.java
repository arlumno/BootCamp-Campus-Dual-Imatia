package controlador;
import java.util.Collection;
import java.util.LinkedList;

public class ControlErrors {
	private static final LinkedList<String> ERRORS_HISTORY = new LinkedList<String>();
	public static final String ERROR_START_ENCENDIDO = "El vehiculo ya está encendido";  
	public static final String ERROR_STOP_NOT_PARADO = "No se puede apagar vehiculo, debe estar parado";  
	public static final String ERROR_ACCELERATE_WRONG_GEAR = "No se puede acelerar: Marcha no adecuada";  
	public static final String ERROR_ACCELERATE_STOPPED = "No se puede acelerar: Vehículo apagado";  
	public static final String ERROR_BRAKE_STOPPED = "No sirve de nada frenar: Vehículo apagado";
	public static final String ERROR_BRAKE_PARADO= "No sirve de nada frenar: Vehículo parado";
	public static final String ERROR_GEAR_WRONG= "GRRRGggIIiGGgggGgIii";
	public static final String ERROR_GEAR_NOT_EXIST= "No se como, pero has arrancado el cambio de marchas";
	public static final String ERROR_CARRERA_SIN_COCHES= "La carrena no tiene vehículos";
	public static final String ERROR_TORNEO_NO_EXISTE= "El torneo no existe";
	public static final String ERROR_CARRERA_NO_EXISTE= "La carrera no existe";
	public static final String ERROR_GARAJE_NO_EXISTE= "El garaje no existe";
	public static String getLast() {
		return ERRORS_HISTORY.getLast();
	}
	
	public static Collection<String> getAll() {
		return ERRORS_HISTORY;
	}

	public static void add(String error) {
		ERRORS_HISTORY.add(error);
	}
	
	public static void reset() {
		 ERRORS_HISTORY.clear();
	}
}
