package pojos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.Collections;

import Excepciones.IncompleteException;
import controlador.Control;

public class Torneo {
	private final String NOMBRE;
	private List<Carrera> carreras = new ArrayList<>();
	private List<Garaje> garajesParticipantes = new ArrayList<>();
	private List<Puntuacion> puntuacionCoches = new ArrayList<>();
	private boolean torneoFinalizado = false;
	private int[] puntosPodio = { 100, 50, 25 };
	private int premioEuros = 100_000;

	/**
	 * 
	 * @param nOMBRE   Nombre del torneo
	 * @param carreras Lista de carreras a añadir al torneo
	 */
	public Torneo(String nOMBRE, List<Carrera> carreras) {
		NOMBRE = nOMBRE;
		this.carreras = carreras;

		for (Carrera carrera : carreras) {
			registrarGarajes(carrera);
		}
	}

	/**
	 * 
	 * @param nOMBRE Nombre del torneo
	 */
	public Torneo(String nOMBRE) {
		this(nOMBRE, new ArrayList<>());
	}

	/**
	 * Añade una carrera al torneo no iniciado, evitando duplicados. Y registra sus
	 * garajes. Admite carreras finalizadas.
	 * 
	 * @param carrera Carrera a añadir al torneo
	 */
	public void addCarrera(Carrera carrera) {
		if (!torneoFinalizado && !carreras.contains(carrera)) {
			carreras.add(carrera);
			registrarGarajes(carrera);
		}
	}

	/**
	 * Añade todos los garajes de las Carrera a los garajes que participan en el
	 * torneo, evitando duplicados
	 * 
	 * @param carrera Carrera a registrar
	 */
	private void registrarGarajes(Carrera carrera) {
		if (!torneoFinalizado) {
			for (Garaje garaje : carrera.getGarajesParticipantes()) {
				if (!garajesParticipantes.contains(garaje)) {
					// registramos en el torneo todos los garajes que participian en las carreras
					garajesParticipantes.add(garaje);
				}
			}
		}
	}

	/**
	 * Inicia todas las carreras no iniciadas del torneo, y guarda la puntuación de
	 * todas las carreras.
	 * 
	 * @throws IncompleteException
	 */
	public void iniciar() throws IncompleteException {
		if (!torneoFinalizado) {
			boolean carrerasFinalizadas = true;
			puntuacionCoches.clear();
			for (Carrera carrera : carreras) {
				if (!carrera.isCarreraFinalizada()) {
					try {
						carrera.iniciar();
						System.out.println(carrera.getNOMBRE() + " finalizada");
					} catch (IncompleteException e) {
						System.err.println(e.getMessage());
						carrerasFinalizadas = false;
					}
				}
				guardarPuntuacion(carrera);
			}
			if (carrerasFinalizadas) {
				Collections.sort(puntuacionCoches);
				torneoFinalizado = true;
			}
		}
	}

	/**
	 * Guarda la puntuación de los coches del torneo que participan en la carrera en
	 * función del puesto en el podio y el array de puntosPodio. Tiene en cuenta los
	 * empates
	 * 
	 * @param carrera
	 */
	private void guardarPuntuacion(Carrera carrera) {
		if (carrera.isCarreraFinalizada()) {
			// añado nuevos coches participantes a las puntuaciones
			for (Coche coche : carrera.getCochesParticipantes()) {
				if (!puntuacionCoches.contains(new Puntuacion(coche))) {
					puntuacionCoches.add(new Puntuacion(coche));
				}
			}

			List<List<Coche>> podio = carrera.getPodio();
			if (!podio.isEmpty()) {
				for (int i = 0; i < podio.size() && i < puntosPodio.length; i++) {
					for (Coche coche : podio.get(i)) {
						puntuacionCoches.get(puntuacionCoches.indexOf(new Puntuacion(coche)))
								.addPuntuacion(puntosPodio[i] / podio.get(i).size());
					}
				}
			}
		}
	}

	/**
	 * Obtiene el coche o coches con la mayor puntuación. Teniendo en cuenta
	 * empates.
	 * 
	 * @return Lista de coches en primer puesto
	 */
	private List<Coche> obtenerCochesGanadores() {
		List<Coche> ganadores = new ArrayList<>();
		ganadores.add(puntuacionCoches.get(0).getCoche());

		for (int i = 1; i < puntuacionCoches.size(); i++) {
			if (puntuacionCoches.get(i).getPuntos() == puntuacionCoches.get(0).getPuntos()) {
				ganadores.add(puntuacionCoches.get(i).getCoche());
			} else {
				break;
			}
		}
		return ganadores;

	}

	/**
	 * Obtiene un texto formateado con el ganador del torneo, o un texto vacio si el
	 * torneo no se ha iniciado
	 * 
	 * @return String con el resultado
	 */
	public String mostrarGanadores() {
		StringBuilder texto = new StringBuilder();
		if (torneoFinalizado) {
			texto.append("\n");
			String separador = "\n************************************************";
			texto.append(separador);
			texto.append("\n\t\tGANADOR/ES:");
			texto.append(separador);
			for (Coche coche : obtenerCochesGanadores()) {
				texto.append("\n\t" + coche.getMARCA() + " - " + coche.getMODELO() + " - " + coche.getPegatinaGaraje());
			}
			texto.append(separador);
			if (obtenerCochesGanadores().size() > 1) {
				texto.append("\n\tPremios: " + premioEuros / obtenerCochesGanadores().size() + "€ cada ganador");
			} else {
				texto.append("\n\t Premio: " + premioEuros + "€ ");
			}
			texto.append(separador);
		}
		return texto.toString();
	}

	/**
	 * Obtiene un texto formateado con la puntuación de los coches en la carrera y
	 * el ganador, o un mensaje informativo si la carrera no se ha iniciado.
	 * 
	 * @return String con el resultado
	 */
	public String mostrarPuntuacionesCoches() {
		StringBuilder texto = new StringBuilder();
		if (torneoFinalizado) {
			texto.append("\n********  " + NOMBRE + "\t *******");
			texto.append("\nPuntuación Final:");
			for (Puntuacion puntuacion : puntuacionCoches) {
				texto.append("\n " + puntuacion.getCoche().getMARCA() + " - " + puntuacion.getCoche().getMODELO() + " ("
						+ puntuacion.getCoche().getPegatinaGaraje() + ")");
				texto.append(" - Puntos: " + puntuacion.getPuntos());
			}
			texto.append(mostrarGanadores());
		} else {
			texto.append("El torneo " + NOMBRE + " aún no se ha realizado");
		}
		return texto.toString();
	}

	/**
	 * Actualiza y vincula las referencias de los coches, garajes y carreras que son
	 * iguales, en la listas de participantes. Si el coche, garaje o carrera no
	 * existe en las listasMain, los añade.
	 * 
	 * @param carrerasMain Lista de carreras maestra de donde se obtiene las
	 *                     referencias. Si no existe la carrera de la lista carresas
	 *                     se añade a carrerasMain
	 * @param garajesMain  Lista de garajes maestra de donde se obtiene las
	 *                     referencias. Si no existe el garaje de la lista
	 *                     garajesParticipantes se añade a garajesMain
	 * @param cochesMain   Lista de coches maestra de donde se obtiene las
	 *                     referencias. Si no existe el coche de la lista del
	 *                     puntuaciones se añade a cochesMain
	 */
	public void syncReferences(List<Carrera> carrerasMain, List<Garaje> garajesMain, List<Coche> cochesMain) {
		Control.syncReferences(garajesMain, garajesParticipantes);
		Control.syncReferences(carrerasMain, carreras);

		for (Puntuacion puntuacion : puntuacionCoches) {
			if (cochesMain.contains(puntuacion.getCoche())) {
				puntuacion.setCoche(cochesMain.get(cochesMain.indexOf(puntuacion.getCoche())));
			} else {
				cochesMain.add(puntuacion.getCoche());
			}
		}

	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public boolean isTorneoFinalizado() {
		return torneoFinalizado;
	}

	public List<Puntuacion> getPuntuacionCoches() {
		return puntuacionCoches;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	@Override
	public int hashCode() {
		return NOMBRE.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Torneo) {
			return ((Torneo) obj).getNOMBRE().equals(NOMBRE);
		}
		return super.equals(obj);
	}

	/**
	 * Objeto interno para la gestión de la puntuación de un coches en torneos, se
	 * ordena de mayor a menor puntuacion
	 * 
	 * @author Ar
	 *
	 */
	class Puntuacion implements Comparable {
		private Coche coche;
		private int puntos = 0;

		public Puntuacion(Coche coche) {
			super();
			this.coche = coche;
		}

		public void addPuntuacion(int puntos) {
			this.puntos += puntos;
		}

		public int getPuntos() {
			return puntos;
		}

		public Coche getCoche() {
			return coche;
		}

		public void setCoche(Coche coche) {
			this.coche = coche;
		}

		@Override
		public int compareTo(Object o) {
			if (((Puntuacion) o).puntos > puntos) {
				return 1;
			} else if (((Puntuacion) o).puntos < puntos) {
				return -1;
			} else {
				return 0;
			}
		}

		@Override
		public int hashCode() {
			return coche.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return coche.equals(((Puntuacion) obj).coche);
		}

		@Override
		public String toString() {
			return "Puntuacion [coche=" + coche + ", puntos=" + puntos + "]";
		}

	}
}
