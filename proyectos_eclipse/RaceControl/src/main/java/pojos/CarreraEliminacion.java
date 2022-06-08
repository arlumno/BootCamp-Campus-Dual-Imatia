package pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Excepciones.IncompleteException;

public class CarreraEliminacion extends Carrera {

	public static final int DURACION_MINUTOS_DEFAULT = 10;

	/**
	 * 
	 * @param nOMBRE           Nombre de la carrera
	 * @param garajes          Listado de garajes participantes
	 * @param dURACION_MINUTOS Duración del calentamiento antes de la eliminación,
	 *                         por defecto 10
	 */
	public CarreraEliminacion(String nOMBRE, ArrayList<Garaje> garajes, int dURACION_MINUTOS) {
		super(nOMBRE, garajes, dURACION_MINUTOS);
		setTipo("Eliminación");
	}

	public CarreraEliminacion(String nOMBRE, ArrayList<Garaje> garajes) {
		this(nOMBRE, garajes, DURACION_MINUTOS_DEFAULT);
	}

	public CarreraEliminacion(String nOMBRE, int dURACION_MINUTOS) {
		this(nOMBRE, new ArrayList<>(), dURACION_MINUTOS);
	}

	public CarreraEliminacion(String nOMBRE) {
		this(nOMBRE, DURACION_MINUTOS_DEFAULT);
	}

//	@Override
//	public void iniciar() throws IncompleteException {
//		// preparamos la carrera:
//		super.preparar();
//		// empieza la carrera.
//		for (int i = 0; i < DURACION_MINUTOS; i++) {
//			for (Coche coche : coches) {
//				coche.conducir();
//			}
//		}
//		// clono la lista de participantes a una nueva lista de eliminatoria para no
//		// modificar la original
//		LinkedList<Coche> enCarrera = (LinkedList<Coche>) coches.clone();		
//		float minDistancia = 0;
//		
//
//		// rondas de eliminación
//		while (!enCarrera.isEmpty()) {
//			for (Coche coche : enCarrera) {
//				coche.conducir();
//			}
//			Collections.sort(enCarrera);				
//			minDistancia = enCarrera.getLast().getCounterKm();
//			enCarrera.removeLast();
//			enCarrera.removeIf((it) -> it.getCounterKm() == minDistancia);			
//			
//		}
//		carreraFinalizada = true;
//		calcularPodio();
//	}

	/**
	 * Inicia la carrera, y calcula la velocidad recorrida por minuto, al finalizar
	 * la DURACION_MINUTOS de la carrera, se deja de contar la distancia de los
	 * coches en ultima posición en cada minuto. Hasta que se anulen a todos los
	 * coches	 * 
	 * 
	 * @throws IncompleteException Si la carrera no está lista para ser iniciada
	 */
	@Override
	public void iniciar() throws IncompleteException {
		// preparamos la carrera:
		super.preparar();
		// empieza la carrera.
		for (int i = 0; i < DURACION_MINUTOS; i++) {
			for (Coche coche : cochesParticipantes) {
				coche.conducir();
			}
		}
		// clono la lista de participantes a una nueva lista de eliminatoria para no
		// modificar la original
		List<Coche> enCarrera = new ArrayList<>();
		enCarrera.addAll(cochesParticipantes);
		boolean seguirEliminado;
		float minDistancia = 0;
		ArrayList<Coche> eliminadosRonda;

		// rondas de eliminación
		while (!enCarrera.isEmpty()) {
			for (Coche coche : enCarrera) {
				coche.conducir();
			}

			// ordeno esta ronda
			Collections.sort(enCarrera);

			eliminadosRonda = new ArrayList<Coche>();
			minDistancia = -1;
			seguirEliminado = true;
			// compruebo distancias para eliminar al que menos tenga, con empates
			for (int i = enCarrera.size() - 1; i >= 0 && seguirEliminado; i--) {
				if (minDistancia == -1) {
					// el ultimo de la lista marca la distancia de eliminacion
					minDistancia = enCarrera.get(i).getCuentaKilometros();
					eliminadosRonda.add(enCarrera.get(i));
				} else if (minDistancia == enCarrera.get(i).getCuentaKilometros()) {
					eliminadosRonda.add(enCarrera.get(i));
				} else {
					seguirEliminado = false;
				}
			} //
			enCarrera.removeAll(eliminadosRonda);
		}
		setCarreraFinalizada(true);
		calcularPodio();
	}
}
