package pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import App.AppConfig;
import Excepciones.IncompleteException;
import controlador.Control;
import pojos.Torneo.Puntuacion;

public abstract class Carrera {

	protected final String NOMBRE;
	protected final int DURACION_MINUTOS;
	public static final int DURACION_MINUTOS_DEFAULT = 180;
	private List<Garaje> garajesParticipantes = new ArrayList<>();
	protected List<Coche> cochesParticipantes = new ArrayList<>();
	private List<List<Coche>> podio = new ArrayList<>();
	private boolean carreraFinalizada = false;
	protected int puestosPodio = 3;
	protected String tipo = "-";

//	protected transient AppConfig config = AppConfig.getAppConfig();
	public Carrera() {
		this.NOMBRE = "";
		this.DURACION_MINUTOS = 0;
	}

	/**
	 * 
	 * @param nOMBRE           Nombre de la carrera
	 * @param dURACION_MINUTOS Duración de la carrera, en minutos
	 */
	public Carrera(String NOMBRE, int DURACION_MINUTOS) {
		this.NOMBRE = NOMBRE;
		this.DURACION_MINUTOS = DURACION_MINUTOS;
	}

	/**
	 * 
	 * @param nOMBRE           Nombre de la carrera
	 * @param garajes          Lista de garajes que participan
	 * @param dURACION_MINUTOS Duración de la carrera, en minutos
	 */
	public Carrera(String nOMBRE, List<Garaje> garajes, int dURACION_MINUTOS) {
		this(nOMBRE, dURACION_MINUTOS);
		setGarajesParticipantes(garajes);
	}

	/**
	 * Constructor con duracion de la carrera por defecto DURACION_MINUTOS_DEFAULT
	 * 180
	 * 
	 * @param NOMBRE Nombre de la carrera
	 */
	public Carrera(String NOMBRE) {
		this.NOMBRE = NOMBRE;
		this.DURACION_MINUTOS = DURACION_MINUTOS_DEFAULT;
	}

	public List<Garaje> getGarajesParticipantes() {
		return garajesParticipantes;
	}

	/**
	 * Añade los garajes de una lista, a la lista de garajes de la carrera. Evita
	 * duplicados.
	 * 
	 * @param garajes
	 */
	public void setGarajesParticipantes(List<Garaje> garajes) {
		for (Garaje garaje : garajes) {
			addGaraje(garaje);
		}
	}

	/**
	 * Añade un garaje a la lista de garajes, siempre que no exista en la lista
	 * 
	 * @param garaje
	 */
	public void addGaraje(Garaje garaje) {
		if (!garajesParticipantes.contains(garaje)) {
			garajesParticipantes.add(garaje);
		}
	}

	public String getTipo() {
		return tipo;
	}

	/**
	 * Metodo abstracto para iniciar la carrera, y calcular la velocidad recorrida
	 * según las reglas de la carrera. Debe incluir el metodo preparar()
	 * 
	 * @throws IncompleteException Si la carrera no está lista para ser iniciada
	 */
	public abstract void iniciar() throws IncompleteException;

	/**
	 * Prepara la carrera añadiendo y preparando los coches que van a participar de
	 * cada garaje, 1 aleatorio de cada garaje si hay varios, o todos los de un
	 * garaje si es el unico participante. Mínimo debe haber 3 coches en la carrera
	 * 
	 * @throws IncompleteException Lanza esta excepción si finalmente hay menos de 3
	 *                             coches en la carrera.
	 */
	protected void preparar() throws IncompleteException {
		cochesParticipantes.clear();

		if (garajesParticipantes.size() > 1) {
			for (Garaje garaje : garajesParticipantes) {
				cochesParticipantes.add(garaje.getCoche());
			}
		} else if (garajesParticipantes.size() == 1) {
			cochesParticipantes.addAll(garajesParticipantes.get(0).getCoches());
		}

		if (cochesParticipantes.size() < 3) {
			throw new IncompleteException("La carrena \"" + NOMBRE + "\" requiere mínimo 3 vehículos");
		}

		for (Coche coche : cochesParticipantes) {
			coche.preparar();
		}

	}

	/**
	 * Calcula el podio según la velocidad recorrida de los coches, permite empates.
	 * Debe haberse iniciado la carrera previamente. El tamaño del podio depende de
	 * puestosPodio
	 */
	protected void calcularPodio() {
		if (carreraFinalizada) {
			// creo el podio
			for (int i = 0; i < puestosPodio; i++) {
				podio.add(new ArrayList<>());// puesto i
			}

			// ordeno el arraylist de mayor a menor distancia recorrida
			Collections.sort(cochesParticipantes);
			int puestoPodio = 0;
			podio.get(puestoPodio).add(cochesParticipantes.get(0));
			for (int i = 1; i < cochesParticipantes.size() && puestoPodio < podio.size(); i++) {
				if (cochesParticipantes.get(i - 1).getCuentaKilometros() > cochesParticipantes.get(i)
						.getCuentaKilometros()) {
					puestoPodio++;
				}
				if (puestoPodio < podio.size()) {
					podio.get(puestoPodio).add(cochesParticipantes.get(i));
				}
			}
		}
	}

	public String mostrarPodio() {
		StringBuilder texto = new StringBuilder();
		if (carreraFinalizada) {
			if (!podio.isEmpty()) {
				texto.append("\n------------- PODIO \"" + NOMBRE + "\" -------------");
				for (int i = 0; i < podio.size(); i++) {
					if (!podio.get(i).isEmpty()) {
						texto.append("\n\n+++ Puesto " + (i + 1) + " +++");
					}
					for (Coche coche : podio.get(i)) {
						texto.append("\n\t" + coche.getMARCA() + " - " + coche.getMODELO() + " - "
								+ coche.getPegatinaGaraje());
					}
				}
			}
		} else {
			texto.append("La carrera aún no se ha realizado");
		}
		return texto.toString();
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

	public List<Coche> getCochesParticipantes() {
		return cochesParticipantes;
	}

	public void setCarreraFinalizada(boolean carreraFinalizada) {
		this.carreraFinalizada = carreraFinalizada;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * Actualiza y vincula las referencias de los coches y garajes que son iguales,
	 * en la listas de participantes. Si el coche o garaje no existe en las
	 * listasMain, los añade.
	 * 
	 * @param garajesMain Lista de garajes maestra de donde se obtiene las
	 *                    referencias. Si no existe el garaje de la lista
	 *                    garajesParticipantes se añade a garajesMain
	 * @param cochesMain  Lista de coches maestra de donde se obtiene las
	 *                    referencias. Si no existe el coche de la lista
	 *                    cochesParticipantes se añade a cochesMain
	 */
	public void syncReferences(List<Garaje> garajesMain, List<Coche> cochesMain) {
		Control.syncReferences(garajesMain, garajesParticipantes);
		Control.syncReferences(cochesMain, cochesParticipantes);
		if (!podio.isEmpty()) {
			for (List<Coche> puesto : podio) {
				Control.syncReferences(cochesMain, puesto);
			}
		}
	}

	protected void flipadaStatus(List<Coche> cochesParticipantes, int vuelta) {
		LinkedList<Coche> coches = new LinkedList<>();
		coches.addAll(cochesParticipantes);
		Collections.sort(coches);

		float longitud = 8;
		float maxDistancia = coches.getFirst().getCuentaKilometros();
		float minDistancia = coches.getLast().getCuentaKilometros();
		float tramo = (maxDistancia - minDistancia) / (longitud - 1);
		StringBuilder pantalla = new StringBuilder();
		String cochecito = "";

		pantalla.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		pantalla.append("\n\t********************************************************");
		pantalla.append("\n\t**************** Carrera " + NOMBRE + " \t****************");
		pantalla.append("\n\t********************************************************");
		pantalla.append("\n\t***************\tTiempo: " + (DURACION_MINUTOS - vuelta) + " \t\t****************");
		pantalla.append("\n\t********************************************************\n");

		int posicion;
		for (Coche coche : cochesParticipantes) {
			posicion = (int) Math.floor((coche.getCuentaKilometros() - minDistancia) / tramo);
			pantalla.append("\n");
			for (int i = 0; i < longitud; i++) {
				if (i == posicion) {
					pantalla.append(" \\O=o> ");
				} else {
					pantalla.append("\t");
				}
			}
			pantalla.append("\t[" + coche.getMARCA() + " - " + coche.getMODELO() + "] --- Hab.: "
					+ coche.getHabilidadPiloto() + " ---- " + coche.getCuentaKilometros());
		}

		System.out.println(pantalla.toString());
	}

	protected void cuentaAtras() {
		for (int i = 5; i >= 0; i--) {
			StringBuilder pantalla = new StringBuilder();
			pantalla.append("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			pantalla.append("\n\t********************************************************");
			pantalla.append("\n\t**************** Carrera " + NOMBRE + " \t****************");
			pantalla.append("\n\t********************************************************");
			pantalla.append("\n\t********************************************************");
			pantalla.append("\n\t**************** Empieza en:  " + i + " \t****************");
			System.out.println(pantalla.toString());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		return "Carrera [NOMBRE=" + NOMBRE + ", DURACION_MINUTOS=" + DURACION_MINUTOS + ", coches=\n"
				+ cochesParticipantes + ", podio=" + podio + ", carreraFinalizada=" + carreraFinalizada + ", garajes="
				+ garajesParticipantes + "]";
	}

}