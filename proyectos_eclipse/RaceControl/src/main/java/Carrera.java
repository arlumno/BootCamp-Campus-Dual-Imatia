import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Excepciones.IncompleteException;

public class Carrera {
	protected final String NOMBRE;
	protected final int DURACION_MINUTOS;
	public final static int DURACION_MINUTOS_DEFAULT = 180;
	private List<Garaje> garajes = new ArrayList<>();
	protected List<Coche> coches = new ArrayList<>();
	private List<List<Coche>> podio = new ArrayList<>();
	boolean carreraFinalizada = false;
	protected int puestosPodio = 3;
	protected String tipo = "Estándar";
	
	/**
	 * 
	 * @param nOMBRE           Nombre de la carrera
	 * @param garajes          Listado de garajes participantes
	 * @param dURACION_MINUTOS Duración de la carrera, por defecto 180
	 */
	public Carrera(String nOMBRE, List<Garaje> garajes, int dURACION_MINUTOS) {
		NOMBRE = nOMBRE;
		DURACION_MINUTOS = dURACION_MINUTOS;
		for (Garaje garaje : garajes) {
			addGaraje(garaje);
		}
	}

	public Carrera(String nOMBRE, List<Garaje> garajes) {
		this(nOMBRE, garajes, DURACION_MINUTOS_DEFAULT);
	}

	public Carrera(String nOMBRE, int dURACION_MINUTOS) {
		this(nOMBRE, new ArrayList<Garaje>(), dURACION_MINUTOS);
	}

	public Carrera(String nOMBRE) {
		this(nOMBRE, DURACION_MINUTOS_DEFAULT);
	}

	public List<Garaje> getGarajes() {
		return garajes;
	}

	public void setGarajes(List<Garaje> garajes) {
		this.garajes = garajes;
	}

	public void addGaraje(Garaje garaje) {
		if (!garajes.contains(garaje)) {
			garajes.add(garaje);
		}
	}
	
	public String getTipo() {
		return tipo;
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
		carreraFinalizada = true;
		calcularPodio();

	}

	protected void preparar() throws IncompleteException {
		coches.clear();

		if (garajes.size() > 1) {
			for (Garaje garaje : garajes) {
				coches.add(garaje.getCoche());
			}
		} else if (garajes.size() == 1) {
			coches.addAll(garajes.get(0).getCoches());
		}

		if (coches.size() < 3) {
			throw new IncompleteException("La carrena \"" + NOMBRE + "\" requiere mínimo 3 vehículos");
		}

		for (Coche coche : coches) {
			coche.preparar();
		}

	}

	protected void calcularPodio() {
		if (carreraFinalizada) {
			// creo el podio
			for (int i = 0; i < puestosPodio; i++) {
				podio.add(new ArrayList<>());// puesto i
			}

			// ordeno el arraylist de mayor a menor distancia recorrida
			Collections.sort(coches);
			int puestoPodio = 0;
			podio.get(puestoPodio).add(coches.get(0));
			for (int i = 1; i < coches.size() && puestoPodio < podio.size(); i++) {
				if (coches.get(i - 1).getCounterKm() > coches.get(i).getCounterKm()) {
					puestoPodio++;
				}
				if(puestoPodio < podio.size()) {
					podio.get(puestoPodio).add(coches.get(i));				
				}
			}
		}
	}

	public boolean isCarreraFinalizada() {
		return carreraFinalizada;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}

	public List<List<Coche>> getPodio() {
		return podio;
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void syncReferences(List<Garaje> garajesMain, List<Coche> cochesMain) {
		Control.syncReferences(garajesMain, garajes);
		Control.syncReferences(cochesMain, coches);
		if(!podio.isEmpty()) {
			for(List<Coche> puesto: podio) {
				Control.syncReferences(cochesMain, puesto);
			}		
		}
	}
	
	@Override
	public int hashCode() {
		return NOMBRE.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Carrera) {
			return ((Carrera) obj).getNOMBRE().equals(NOMBRE);
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "Carrera [NOMBRE=" + NOMBRE + ", DURACION_MINUTOS=" + DURACION_MINUTOS + ", coches=\n" + coches
				+ ", podio=" + podio + ", carreraFinalizada=" + carreraFinalizada + ", garajes=" + garajes + "]";
	}

}
