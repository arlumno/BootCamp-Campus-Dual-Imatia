import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.Collections;


import Excepciones.IncompleteException;

public class Torneo {
	private final String NOMBRE;
	private List<Carrera> carreras = new ArrayList<>();
	private List<Garaje> garajes = new ArrayList<>();
	private List<Puntuacion> puntuacionCoches = new ArrayList<>();
//	private HashMap<Coche, Integer> puntuacionCoches = new HashMap<>();
	boolean torneoFinalizado = false;
	private int[] puntosPodio = { 100, 50, 25 };
	private int premioEuros = 100_000;

	class Puntuacion implements Comparable {
		private Coche coche;
		private int puntos = 0;

		public Puntuacion(Coche coche) {
			super();
			this.coche = coche;
		}
		public void addPuntuacion (int puntos) {
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

	public Torneo(String nOMBRE, List<Carrera> carreras) {
		NOMBRE = nOMBRE;
		this.carreras = carreras;

		for (Carrera carrera : carreras) {
			registrarGarajes(carrera);
		}
	}

	public Torneo(String nOMBRE) {
		this(nOMBRE, new ArrayList<>());
	}

	public void addCarrera(Carrera carrera) {
		if (!torneoFinalizado) {
			if(!carreras.contains(carrera)) {
				carreras.add(carrera);
				registrarGarajes(carrera);
			}
		}
	}

	private void registrarGarajes(Carrera carrera) {
		if (!torneoFinalizado) {
			for (Garaje garaje : carrera.getGarajes()) {
				if (!garajes.contains(garaje)) {
					garajes.add(garaje); // registramos en el torneo todos los garajes que participian en las carreras
				}
			}
		}
	}

	public void iniciar() throws IncompleteException {
		if (!torneoFinalizado) {
			for (Carrera carrera : carreras) {
				if (!carrera.isCarreraFinalizada()) {
					carrera.iniciar();
					guardarPuntuacion(carrera);
					System.out.println(carrera.getNOMBRE() + " finalizada");
				}
			}
		}
		Collections.sort(puntuacionCoches);
		torneoFinalizado = true;
	}

	private List<Coche> obtenerGanadores() {
		//TreeSet<Integer> rankingFinal = new TreeSet<Integer>(puntuacionCoches.values());
	//	Collections.sort(puntuacionCoches);
		//Integer maxPuntuacion = rankingFinal.last();
		List<Coche> ganadores = new ArrayList<>();
		ganadores.add(puntuacionCoches.get(0).getCoche());
		
		for (int i = 1; i < puntuacionCoches.size(); i++) {
			if (puntuacionCoches.get(i).getPuntos() == puntuacionCoches.get(0).getPuntos()) {
				ganadores.add(puntuacionCoches.get(i).getCoche());			
			}else {
				break;
			}
		}
		return ganadores;

	}
//	private List<Coche> obtenerGanadores() {
//		TreeSet<Integer> rankingFinal = new TreeSet<Integer>(puntuacionCoches.values());
//		Integer maxPuntuacion = rankingFinal.last();
//		List<Coche> ganadores = new ArrayList<Coche>();
//		for (Map.Entry<Coche, Integer> entry : puntuacionCoches.entrySet()) {
//			if (entry.getValue() == maxPuntuacion) {
//				ganadores.add(entry.getKey());
//			}
//		}
//		return ganadores;
//		
//	}

	private void guardarPuntuacion(Carrera carrera) {
		List<List<Coche>> podio = carrera.getPodio();
		for (Coche coche : carrera.getCoches()) { // añado nuevos coches participantes a las puntuaciones
			if (!puntuacionCoches.contains(new Puntuacion(coche))) {
				puntuacionCoches.add(new Puntuacion(coche));
			}
		}
		System.out.println();
		if (!podio.isEmpty()) {
			for (int i = 0; i < podio.size() && i < puntosPodio.length; i++) {
				for (Coche coche : podio.get(i)) {
					puntuacionCoches.get(puntuacionCoches.indexOf(new Puntuacion(coche))).addPuntuacion(puntosPodio[i] / podio.get(i).size());
				}
			}
		}
	}
//	private void guardarPuntuacion(Carrera carrera) {
//		List<List<Coche>> podio = carrera.getPodio();
//		for (Coche coche : carrera.getCoches()) { // añado nuevos coches participantes a las puntuaciones
//			if (!puntuacionCoches.containsKey(coche)) {
//				puntuacionCoches.put(coche, 0);
//			}
//		}
//		
//		if (!podio.isEmpty()) {
//			for (int i = 0; i < podio.size() && i < puntosPodio.length; i++) {
//				for (Coche coche : podio.get(i)) {
//					puntuacionCoches.put(coche, puntuacionCoches.get(coche) + (puntosPodio[i]) / podio.get(i).size());
//				}
//			}
//		}
//	}

	public boolean isTorneoFinalizado() {
		return torneoFinalizado;
	}

	public List<Puntuacion> getPuntuacionCoches() {
		return puntuacionCoches;
	}

	public String mostrarPuntuacionesCoches() {
		StringBuilder texto = new StringBuilder();
		if (torneoFinalizado) {
			texto.append("Puntuación Final:");
			for (Puntuacion puntuacion: puntuacionCoches) {
				texto.append("\n " + puntuacion.getCoche().getBRAND() + " - " + puntuacion.getCoche().getMODEL() + " ("
						+ puntuacion.getCoche().getPegatinaGaraje() + ")");
				texto.append(" - Puntos: " + puntuacion.getPuntos());
			}
			texto.append(mostrarGanadores());
		} else {
			texto.append("El torneo " + NOMBRE + " aún no se ha realizado");
		}
		return texto.toString();
	}

	public String mostrarGanadores() {
		StringBuilder texto = new StringBuilder();
		texto.append("\n\n");
		String separador = "\n************************************************";
		texto.append(separador);
		texto.append("\n\t\tGANADOR/ES:");
		texto.append(separador);
		for (Coche coche : obtenerGanadores()) {
			texto.append("\n\t" + coche.getBRAND() + " - " + coche.getMODEL() + " - " + coche.getPegatinaGaraje());
		}
		texto.append(separador);
		if (obtenerGanadores().size() > 1) {
			texto.append("\n\tPremios: " + premioEuros / obtenerGanadores().size() + "€ cada ganador");
		} else {
			texto.append("\n\t Premio: " + premioEuros + "€ ");
		}
		texto.append(separador);
		return texto.toString();
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public void syncReferences(List<Carrera> carrerasMain, List<Garaje> garajesMain, List<Coche> cochesMain) {
		Control.syncReferences(garajesMain, garajes);
		Control.syncReferences(carrerasMain, carreras);

		for (Puntuacion puntuacion: puntuacionCoches) {
			if (cochesMain.contains(puntuacion.getCoche())) {
				puntuacion.setCoche(cochesMain.get(cochesMain.indexOf(puntuacion.getCoche())));
			} else {
				cochesMain.add(puntuacion.getCoche());
			}
		}

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
}
