import java.util.ArrayList;
import java.util.List;

import Excepciones.IncompleteException;

public class CarreraEstandar extends Carrera {
	public final static int DURACION_MINUTOS_DEFAULT = 180;
	//protected String tipo = "Eliminación";
	/**
	 * 
	 * @param nOMBRE           Nombre de la carrera
	 * @param garajes          Listado de garajes participantes
	 * @param dURACION_MINUTOS Duración de la carrera, por defecto 180
	 */
	public CarreraEstandar(String nOMBRE, List<Garaje> garajes, int dURACION_MINUTOS) {
		super(nOMBRE, garajes, dURACION_MINUTOS);	
		setTipo("Estandar");
	}

	public CarreraEstandar(String nOMBRE, List<Garaje> garajes) {
		this(nOMBRE, garajes, DURACION_MINUTOS_DEFAULT);
	}

	public CarreraEstandar(String nOMBRE, int dURACION_MINUTOS) {
		this(nOMBRE, new ArrayList<>(), DURACION_MINUTOS_DEFAULT);
	}

	public CarreraEstandar(String nOMBRE) {
		this(nOMBRE, DURACION_MINUTOS_DEFAULT);
	}
	
	public void iniciar() throws IncompleteException {
		// preparamos la carrera:
		preparar();
		// empieza la carrera.
		for (int i = 0; i < DURACION_MINUTOS; i++) {
			for (Coche coche : coches) {
				coche.conducir();
			}
		}	
		setCarreraFinalizada(true);
		calcularPodio();	
	}
}
