import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import Excepciones.IncompleteException;

public class Torneo {
	private final String NOMBRE;
	private ArrayList<Carrera> carreras = new ArrayList<Carrera>();
	private ArrayList<Garaje> garajes = new ArrayList<Garaje>();
	private HashMap<Coche, Integer> puntuacionCoches = new HashMap<Coche, Integer>();
	boolean torneoFinalizado = false;
	private int[] puntosPodio = { 100, 50, 25 };
	private int premioEuros = 100_000;

	public Torneo(String nOMBRE, ArrayList<Carrera> carreras) {
		NOMBRE = nOMBRE;
		this.carreras = carreras;

		for (Carrera carrera : carreras) {
			registrarGarajes(carrera);
		}
	}

	public Torneo(String nOMBRE) {
		this(nOMBRE, new ArrayList<Carrera>());
	}

	public void addCarrera(Carrera carrera) {
		if (!torneoFinalizado) {
			if (!carreras.contains(carrera)) {
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
		torneoFinalizado = true;
	}

	private ArrayList<Coche> obtenerGanadores() {
		if (torneoFinalizado) {
			TreeSet<Integer> rankingFinal = new TreeSet<Integer>(puntuacionCoches.values());
			Integer maxPuntuacion = rankingFinal.last();
			ArrayList<Coche> ganador = new ArrayList<Coche>();
			for (Map.Entry<Coche, Integer> entry : puntuacionCoches.entrySet()) {
				if (entry.getValue() == maxPuntuacion) {
					ganador.add(entry.getKey());
				}
			}
			return ganador;
		} else {
			return null;
		}
	}

	private void guardarPuntuacion(Carrera carrera) {
		ArrayList<ArrayList<Coche>> podio = carrera.getPodio();
		for (Coche coche : carrera.getCoches()) { // añado nuevos coches participantes a las puntuaciones
			if (!puntuacionCoches.containsKey(coche)) {
				puntuacionCoches.put(coche, 0);
			}
		}

		if (podio != null) {
			for (int i = 0; i < podio.size() && i < puntosPodio.length; i++) {
				for (Coche coche : podio.get(i)) {
					puntuacionCoches.put(coche, puntuacionCoches.get(coche) + (puntosPodio[i]) / podio.get(i).size());
				}
			}
		}
	}

	public boolean isTorneoFinalizado() {
		return torneoFinalizado;
	}

	public HashMap<Coche, Integer> getPuntuacionCoches() {
		return puntuacionCoches;
	}

	public String mostrarPuntuacionesCoches() {
		StringBuilder texto = new StringBuilder();
		if (torneoFinalizado) {
			texto.append("Puntuación Final:");
			for (Map.Entry<Coche, Integer> puntuacion : puntuacionCoches.entrySet()) {
				texto.append("\n " + puntuacion.getKey().getBRAND() + " - " + puntuacion.getKey().getMODEL() + " ("
						+ puntuacion.getKey().getPegatinaGaraje() + ")");
				texto.append(" - Puntos: " + puntuacion.getValue());
			}
			texto.append(mostrarGanadores());
		} else {
			texto.append("El torneo " + NOMBRE + " aún no se ha realizado" );
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
			texto.append("\n\t" + coche.getBRAND() + " - " + coche.getMODEL()+ " - " + coche.getPegatinaGaraje());			
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
