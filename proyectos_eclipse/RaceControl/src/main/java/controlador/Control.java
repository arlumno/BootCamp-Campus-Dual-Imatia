package controlador;
import java.util.ArrayList;
import java.util.List;

import Excepciones.SuperPilotException;
import pojos.Carrera;
import pojos.CarreraEliminacion;
import pojos.CarreraEstandar;
import pojos.Coche;
import pojos.Garaje;
import pojos.Torneo;

public class Control {
	List<Coche> coches = new ArrayList<>();
	List<Garaje> garajes = new ArrayList<>();
	List<Carrera> carreras = new ArrayList<>();
	List<Torneo> torneos = new ArrayList<>();

	public Control() {

	}

	/**
	 * 
	 * @param garajes  Lista de todos los garajes en la app
	 * @param carreras Lista de todos las carreras en la app
	 * @param torneos  Lista de todos los torneos en la app
	 */
	public Control(List<Garaje> garajes, List<Carrera> carreras, List<Torneo> torneos) {
		this.garajes = garajes;
		this.carreras = carreras;
		this.torneos = torneos;
	}

	/**
	 * Añade un torneo a la lista de torneos
	 * 
	 * @param torneo Objeto Torneo a añadir
	 * @return true si lo añade, false si no lo añade porque ya existe en la lista
	 */
	public boolean addTorneo(Torneo torneo) {
		boolean resultado = true;
		if (torneos.contains(torneo)) {
			resultado = false;
		} else {
			torneos.add(torneo);
		}
		return resultado;
	}

	/**
	 * Añade una carrera a la lista de carreras
	 * 
	 * @param carrera Objeto Carrera a añadir
	 * @return true si lo añade, false si no lo añade porque ya existe en la lista
	 */
	public boolean addCarrera(Carrera carrera) {
		boolean resultado = true;
		if (carreras.contains(carrera)) {
			resultado = false;
		} else {
			carreras.add(carrera);
		}
		return resultado;
	}

	/**
	 * Añaade un garaje a la lista de garajes
	 * 
	 * @param garaje Objeto Garaje a añadir
	 * @return true si lo añade, false si no lo añade porque ya existe en la lista
	 */
	public boolean addGaraje(Garaje garaje) {
		boolean resultado = true;
		if (garajes.contains(garaje)) {
			resultado = false;
		} else {
			garajes.add(garaje);
		}
		return resultado;
	}

	/**
	 * Añade un coche a la lista de coches
	 * 
	 * @param coche Objeto Coche a añadir
	 * @return true si lo añade, false si no lo añade porque ya existe en la lista
	 */
	public boolean addCoche(Coche coche) {
		boolean resultado = true;
		if (coches.contains(coche)) {
			resultado = false;
		} else {
			coches.add(coche);
		}
		return resultado;
	}

	/**
	 * Asigna un Coche a un Garaje
	 * 
	 * @param garaje
	 * @param coche
	 */
	public void addCocheGaraje(Garaje garaje, Coche coche) {
		garaje.addCoche(coche);
	}

	/**
	 * Asigna un Coche a una Carrera
	 * 
	 * @param carrera
	 * @param garaje
	 */
	public void addGarajeCarrera(Carrera carrera, Garaje garaje) {
		carrera.addGaraje(garaje);
	}

	/**
	 * Asigna una Carrera a un Torneo
	 * 
	 * @param torneo
	 * @param carrera
	 */
	public void addCarreraTorneo(Torneo torneo, Carrera carrera) {
		torneo.addCarrera(carrera);
	}

	/**
	 * Objtiene una lista de Carreras que no se han iniciado.
	 * 
	 * @return Lista de Carreras
	 */
	public List<Carrera> getCarrerasPendientes() {
		List<Carrera> carrerasPendientes = new ArrayList<>();
		for (Carrera carrera : carreras) {
			if (!carrera.isCarreraFinalizada()) {
				carrerasPendientes.add(carrera);
			}
		}
		return carrerasPendientes;
	}

	/**
	 * Obtiene una lista de Carreras que no se han iniciado y no está asignadas a
	 * algún torneo
	 * 
	 * @return Lista de Carreras
	 */
	public List<Carrera> getCarrerasSinAsignar() {
		List<Carrera> carrerasAsignables = new ArrayList<>();
		boolean valido;
		for (Carrera carrera : carreras) {
			valido = true;
			if (!carrera.isCarreraFinalizada()) {
				for (Torneo torneo : torneos) {
					if (torneo.getCarreras().contains(carrera)) {
						valido = false;
					}
				}
				if (valido) {
					carrerasAsignables.add(carrera);
				}
			}
		}
		return carrerasAsignables;
	}

	/**
	 * Obtiene una lista de Coches que no tienen garaje asignado.
	 * 
	 * @return Lista de Coches
	 */
	public List<Coche> getCochesSinAsignar() {
		List<Coche> cochesSinAsignar = new ArrayList<>();
		for (Coche coche : coches) {
			if (coche.getPegatinaGaraje().equals("")) {
				cochesSinAsignar.add(coche);
			}
		}
		return cochesSinAsignar;
	}

	/**
	 * Obtiene una lista formateada con todos los coches de la aplicación, indicando
	 * su nombre, modelo y garaje (si lo tiene)
	 * 
	 * @return String con el resultado.
	 */
	public String listarCoches() {
		StringBuilder texto = new StringBuilder();
		texto.append("\nCoches: (" + coches.size() + "):\n");
		for (Coche coche : coches) {
			texto.append("\n" + coche.getMARCA() + " - " + coche.getMODELO() + " (" + coche.getPegatinaGaraje() + ")");
		}
		return texto.toString();
	}

	/**
	 * Obtiene una lista formateada con todos los Garajes de la aplicación, y los
	 * coches que tiene asignado. Indica nombre del garaje, modelo y marca de sus
	 * coches.
	 * 
	 * @return String con el resultado
	 */
	public String listarGarajes() {
		StringBuilder texto = new StringBuilder();
		texto.append("\nGarajes: (" + garajes.size() + "):\n");
		for (Garaje garaje : garajes) {
			texto.append("\n\n" + garaje.getNOMBRE());
			if (garaje.getCoches().isEmpty()) {
				texto.append("\n Sin Coches inscritos.");
			} else {
				for (Coche coche : garaje.getCoches()) {
					texto.append("\n\t" + coche.getMARCA() + " - " + coche.getMODELO());
				}
			}
		}
		return texto.toString();
	}

	/**
	 * Obtiene una lista formateada con las Carreras de la aplicación, indicando el
	 * nombre, tipo, si está finalizada y los garajes que participantes
	 * 
	 * @return String con el resultado
	 */
	public String listarCarreras() {
		StringBuilder texto = new StringBuilder();
		texto.append("\nCarreras: (" + carreras.size() + "):\n");
		for (Carrera carrera : carreras) {
			texto.append("\n\n" + carrera.getNOMBRE());
			texto.append("\n Tipo: " + carrera.getTipo());
			texto.append("\n Finalizada: " + carrera.isCarreraFinalizada());
			if (carrera.getGarajesParticipantes().isEmpty()) {
				texto.append("\n Sin garajes participantes.");
			} else {
				texto.append("\n Garajes participantes:");
				for (Garaje garaje : carrera.getGarajesParticipantes()) {
					texto.append("\n\t" + garaje.getNOMBRE());
				}
			}
		}
		return texto.toString();
	}

	/**
	 * Obtiene una lista formateada de Torneos de la aplicación, indicando el
	 * nombre, si está finalizado, las carreras que lo componen y las puntuaciones
	 * del torneo.
	 * 
	 * @return String resultado.
	 */
	public String listarTorneos() {
		StringBuilder texto = new StringBuilder();
		texto.append("\nTorneos: (" + torneos.size() + "):\n");
		for (Torneo torneo : torneos) {
			texto.append("\n\n" + torneo.getNOMBRE());
			texto.append("\n Finalizada: " + torneo.isTorneoFinalizado());
			texto.append("\n Carreras que lo componen:");
			if (torneo.getCarreras().isEmpty()) {
				texto.append("\n Sin carreras asignadas.");
			} else {
				for (Carrera carrera : torneo.getCarreras()) {
					texto.append("\n\t" + carrera.getNOMBRE());
				}
			}
			texto.append("\n" + torneo.mostrarPuntuacionesCoches());
		}
		return texto.toString();
	}

	public List<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	public List<Garaje> getGarajes() {
		return garajes;
	}

	public void setGarajes(List<Garaje> garajes) {
		this.garajes = garajes;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public List<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
	}


	/**
	 * Metodo estático para actualizar dos listas para evitar objetos clonados.
	 * 
	 * @param <T>				
	 * @param listMain               Lista que tendrá la referencias maestras, si el
	 *                               objeto de listToUpdateReferences no existe en
	 *                               esta lista, se añade.
	 * @param listToUpdateReferences Lista a actualizar con las referencias de
	 *                               listMain si los objetos se evaluan como
	 *                               iguales, si el objeto no existe en listMain, se
	 *                               añade a esa lista
	 */
	public static <T> void syncReferences(List<T> listMain, List<T> listToUpdateReferences) {
		for (int i = 0; i < listToUpdateReferences.size(); i++) {
			if (listMain.contains(listToUpdateReferences.get(i))) {
				listToUpdateReferences.set(i, listMain.get(listMain.indexOf(listToUpdateReferences.get(i))));
			} else {
				listMain.add(listToUpdateReferences.get(i));
			}
		}
	}

	/**
	 * Actualiza y vincula las referencias de los objetos que son iguales, en toda
	 * la aplicación
	 */
	public void syncAllReferences() {

		for (Garaje garaje : garajes) {
			garaje.syncReferences(coches);
		}

		for (Carrera carrera : carreras) {
			carrera.syncReferences(garajes, coches);
		}

		for (Torneo torneo : torneos) {
			torneo.syncReferences(carreras, garajes, coches);
		}
		System.err.println(">> Referencias sincronizadads");
	}
	
//	/**
//	 * Carga datos de ejemplo en la aplicacón.
//	 */
//	public void resetAndLoadDefault() {
//		System.err.println("********************************************");
//		System.err.println("**********CARGANDO DATOS POR DEFECTO********");
//		System.err.println("********************************************");
//		garajes = new ArrayList<>();
//		carreras = new ArrayList<>();
//		torneos = new ArrayList<>();
//
//		try {
//			// garajes
//			Garaje g01 = new Garaje("Los Manquiñas");
//			garajes.add(g01);
//			g01.addCoche(new Coche("Renaul", "Clio"));
//			g01.addCoche(new Coche("Ford", "Fiesta"));
//			g01.addCoche(new Coche("Renaul", "Traffic"));
//
//			Garaje g02 = new Garaje("Los Flipados");
//			garajes.add(g02);
//			g02.addCoche(new Coche("Seat", "F1"));
//			g02.addCoche(new Coche("Seat", "F2"));
//			g02.addCoche(new Coche("Seat", "F3"));
//			g02.addCoche(new Coche("Seat", "F4"));
//			g02.addCoche(new Coche("Seat", "F5"));
//
//			Garaje g03 = new Garaje("Lone Wolf");
//			garajes.add(g03);
//			g03.addCoche(new Coche("5-Ferrari", "F50", 60));
//
//			Garaje g04 = new Garaje("Mancato");
//			garajes.add(g04);
//			g04.addCoche(new Coche("Fiat", "500"));
//			g04.addCoche(new Coche("Fiat", "600"));
//			g04.addCoche(new Coche("Fiat", "700"));
//
//			// carreras
//			CarreraEstandar c01 = new CarreraEstandar("Primera Carrera");
//			carreras.add(c01);
//			c01.addGaraje(g01);
//			c01.addGaraje(g02);
//			c01.addGaraje(g03);
//
//			CarreraEstandar c02 = new CarreraEstandar("Segunda carrera");
//			carreras.add(c02);
//			c02.addGaraje(g04);
//
//			CarreraEstandar c03 = new CarreraEstandar("Tercera carrera");
//			carreras.add(c03);
//			c03.addGaraje(g01);
//			c03.addGaraje(g02);
//			c03.addGaraje(g03);
//			c03.addGaraje(g04);
//
//			CarreraEliminacion c04 = new CarreraEliminacion("Carrera Eliminatoria");
//			carreras.add(c04);
//			c04.addGaraje(g01);
//			c04.addGaraje(g02);
//			c04.addGaraje(g03);
//			c04.addGaraje(g04);
//
//			// torneos
//			Torneo t01 = new Torneo("24 horas de limons");
//			torneos.add(t01);
//			t01.addCarrera(c01);
//			t01.addCarrera(c02);
//			t01.addCarrera(c03);
//			t01.addCarrera(c04);
//		} catch (SuperPilotException e) {
//			e.printStackTrace();
//		}
//
//	}

	@Override
	public String toString() {
		return "Control [garajes=" + garajes + ", carreras=" + carreras + ", torneos=" + torneos + "]";
	}

}
