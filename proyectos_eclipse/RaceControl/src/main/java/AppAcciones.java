import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Excepciones.IncompleteException;
import controlador.Control;
import menu.EntradasConsola;
import menu.Menu;
import pojos.Carrera;
import pojos.CarreraEliminacion;
import pojos.CarreraEstandar;
import pojos.Coche;
import pojos.Garaje;
import pojos.Torneo;

public class AppAcciones {
	Scanner lector;
	Control control;

	/**
	 * Gestor de acciones del menu.
	 * 
	 * @param lector  Flujo de entrada para pedir datos por consola
	 * @param control Controlador de acciones con la aplicación
	 */
	public AppAcciones(Scanner lector, Control control) {
		this.lector = lector;
		this.control = control;
	}

	/**
	 * Imprime por consola el listado de coches
	 */
	public void listarCoches() {
		System.out.println(control.listarCoches());
	}

	/**
	 * Imprime por consola el listado de garajes
	 */
	public void listarGarajes() {
		System.out.println(control.listarGarajes());
	}

	/**
	 * Imprime por consola el listado de carreras
	 */
	public void listarCarreras() {
		System.out.println(control.listarCarreras());
	}

	/**
	 * Imprime por consola el listado de torneos
	 */
	public void listarTorneos() {
		System.out.println(control.listarTorneos());
	}

	/**
	 * Pide por consola la marca y modelo de un nuevo coche, y lo añade a la app.
	 * Imprime error si el coche ya existe.
	 */
	public void crearCoche() {
		System.out.println("Introduzca la marca del vehículo:");
		String marca = EntradasConsola.pedirString(lector);
		System.out.println("Introduzca el modelo del vehículo:");
		String modelo = EntradasConsola.pedirString(lector);

		Coche coche = new Coche(marca, modelo);
		if (control.addCoche(coche)) {
			System.out.println("Coche creado");
		} else {
			System.err.println("Error al crear coche");
		}
	}

	/**
	 * Pide por consola el nombre de un nuevo garaje, y lo añade a la app. Imprime
	 * error si el garaje ya existe.
	 */
	public void crearGaraje() {
		System.out.println("Introduzca el nombre del garaje.");
		Garaje garaje = new Garaje(EntradasConsola.pedirString(lector));

		if (control.addGaraje(garaje)) {
			System.out.println("Garaje creado");
		} else {
			System.err.println("Error al crear garaje");
		}
	}

	/**
	 * Pide por consola el nombre de una nueva carrera, y la añade a la app. Imprime
	 * error si la carrera ya existe.
	 */
	public void crearCarrera() {
		Menu menuTipoCarrera = new Menu(false);
		menuTipoCarrera.setTitulo("Tipos de Carreras");
		menuTipoCarrera.setSubTitulo("Selecciona el tipo de carrera");
		menuTipoCarrera.addOpcion("Estándar", null);
		menuTipoCarrera.addOpcion("Eliminación", null);
		menuTipoCarrera.run();
		System.out.println("Introduzca el nombre de la Carrera.");
		Carrera carrera = null;
		try {

			switch (menuTipoCarrera.getTextSeleccion()) {
			case "Estándar":
				carrera = new CarreraEstandar(EntradasConsola.pedirString(lector));
				break;
			case "Eliminación":
				carrera = new CarreraEliminacion(EntradasConsola.pedirString(lector));
				break;
			default:
				throw new Exception("Tipo de carrera no válida: " + menuTipoCarrera.getTextSeleccion());
			}

			if (control.addCarrera(carrera)) {
				System.out.println("Carrera creada");
			} else {
				System.err.println("Error al crear carrera");
			}
		} catch (Exception e) {
			System.err.println("Error al crear carrera:" + e.getMessage());
		}
	}

	/**
	 * Pide por consola el nombre de un nuevo Torneo, y la añade a la app. Imprime
	 * error si el torneo ya existe.
	 */
	public void crearTorneo() {
		System.out.println("Introduzca el nombre del Torneo.");
		Torneo torneo = new Torneo(EntradasConsola.pedirString(lector));

		if (control.addTorneo(torneo)) {
			System.out.println("Torneo creado");
		} else {
			System.err.println("Error al crear Torneo");
		}
	}

	/**
	 * Selecciona una o varias carreras que no estén asignadas a un torneo y que no
	 * finalizasen, para añadirlas a un torneo no finalizado.
	 */
	public void addCarreraTorneo() {
		List<Torneo> torneosValidos = new ArrayList<>();
		List<Carrera> carrerasAsignables = control.getCarrerasSinAsignar();
		torneosValidos.addAll(control.getTorneos());
		torneosValidos.removeIf(it -> it.isTorneoFinalizado());

		if (torneosValidos.isEmpty()) {
			System.out.println("No hay torneos disponibles.");
		} else if (carrerasAsignables.isEmpty()) {
			System.out.println("No hay mas carreras disponibles para asignar");
		} else {
			Menu menuTorneo = new Menu(false);
			menuTorneo.setTitulo("Torneos Disponibles");
			menuTorneo.setSubTitulo("A que torneo quieres añadir carreras?");
			for (Torneo torneo : torneosValidos) {
				menuTorneo.addOpcion(torneo.getNOMBRE(), null);
			}
			menuTorneo.run();

			if (menuTorneo.getSeleccion() != 0) {
				Menu menuCarrera = new Menu(false);// sin false, es true, el menu se repite hasta salir.
				menuCarrera.setTitulo("Carreras Disponibles");
				menuCarrera.setSubTitulo(
						"Que carrera quieres añadir a " + torneosValidos.get(menuTorneo.getIndexOpcion()).getNOMBRE());
				do {
					menuCarrera.resetOptions();
					for (Carrera carrera : carrerasAsignables) {
						menuCarrera.addOpcion(carrera.getNOMBRE(), () -> control
								.addCarreraTorneo(torneosValidos.get(menuTorneo.getIndexOpcion()), carrera));
					}
					menuCarrera.run();
					if (menuCarrera.getSeleccion() != 0) {
						carrerasAsignables.remove(menuCarrera.getIndexOpcion());
					}
				} while (menuCarrera.getSeleccion() != 0 && carrerasAsignables.isEmpty());
			}
		}
	}

	/**
	 * Selecciona uno o varios coches no asignados a garajes, para añadirlo a un
	 * garaje.
	 */
	public void addCocheGaraje() {
		List<Coche> cochesAsignables = control.getCochesSinAsignar();
		if (control.getGarajes().isEmpty()) {
			System.out.println("No hay Garajes disponibles.");
		} else if (cochesAsignables.isEmpty()) {
			System.out.println("No hay Coches disponibles.");
		} else {
			Menu menuGarajes = new Menu(false);
			menuGarajes.setTitulo("Garajes");
			menuGarajes.setSubTitulo("¿A que garaje quieres añadir coches?");
			for (Garaje garaje : control.getGarajes()) {
				menuGarajes.addOpcion(garaje.getNOMBRE(), null);
			}
			menuGarajes.run();

			if (menuGarajes.getSeleccion() != 0) {
				Menu menuCoches = new Menu(false);
				menuCoches.setTitulo("Coches Disponibles");
				menuCoches.setSubTitulo("Que coche quieres añadir a "
						+ control.getGarajes().get(menuGarajes.getIndexOpcion()).getNOMBRE());
				do {
					menuCoches.resetOptions();
					for (Coche coche : cochesAsignables) {
						menuCoches.addOpcion(coche.getMARCA() + " - " + coche.getMODELO(), () -> control
								.addCocheGaraje(control.getGarajes().get(menuGarajes.getIndexOpcion()), coche));
					}
					menuCoches.run();
					if (menuCoches.getSeleccion() != 0) {
						cochesAsignables.remove(menuCoches.getIndexOpcion());
					}

				} while (menuCoches.getSeleccion() != 0 && !cochesAsignables.isEmpty());
			}
		}
	}

	/**
	 * Selecciona y añade uno o varios garajes a una carrera no finalizada.
	 */
	public void addGarajeCarrera() {
		List<Carrera> carrerasPendientes = control.getCarrerasPendientes();
		if (control.getGarajes().isEmpty()) {
			System.out.println("No hay Garajes disponibles.");
		} else if (carrerasPendientes.isEmpty()) {
			System.out.println("No hay carreras disponibles.");
		} else {
			Menu menuCarreras = new Menu(false);
			menuCarreras.setTitulo("Carreras disponibles");
			menuCarreras.setSubTitulo("¿A que Carrera quieres añadir garajes?");
			for (Carrera carrera : carrerasPendientes) {
				menuCarreras.addOpcion(carrera.getNOMBRE(), null);
			}
			menuCarreras.run();

			if (menuCarreras.getSeleccion() != 0) {
				List<Garaje> garajesAsignables = control.getGarajes();
				// quito los garajes que ya están asignados
				garajesAsignables
						.removeAll(carrerasPendientes.get(menuCarreras.getIndexOpcion()).getGarajesParticipantes());
				Menu menuGarajes = new Menu(false);// sin false, es true, el menu se repite hasta salir.
				menuGarajes.setTitulo("Garajes Disponibles");
				menuGarajes.setSubTitulo("Que garaje quieres añadir a "
						+ carrerasPendientes.get(menuCarreras.getIndexOpcion()).getNOMBRE());
				do {
					menuGarajes.resetOptions();
					for (Garaje garaje : garajesAsignables) {
						menuGarajes.addOpcion(garaje.getNOMBRE(), () -> control
								.addGarajeCarrera(carrerasPendientes.get(menuCarreras.getIndexOpcion()), garaje));
					}
					menuGarajes.run();
					if (menuGarajes.getSeleccion() != 0) {
						garajesAsignables.remove(menuGarajes.getIndexOpcion());
					}
				} while (menuGarajes.getSeleccion() != 0 && !garajesAsignables.isEmpty());
			}
		}
	}

	/**
	 * Selecciona un torneo no finalizado y lo inicia
	 */
	public void iniciarTorneo() {
		List<Torneo> torneosPendientes = new ArrayList<>();
		torneosPendientes.addAll(control.getTorneos());
		torneosPendientes.removeIf(it -> it.isTorneoFinalizado());
		if (!torneosPendientes.isEmpty()) {
			Menu menu = new Menu(false);
			menu.setTitulo("Iniciar Torneo");
			menu.setSubTitulo("Iniciar");
			for (Torneo torneo : torneosPendientes) {
				menu.addOpcion(torneo.getNOMBRE(), null);
			}
			menu.run();
			try {
				torneosPendientes.get(menu.getIndexOpcion()).iniciar();
				torneosPendientes.get(menu.getIndexOpcion()).mostrarPuntuacionesCoches();
			} catch (IncompleteException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("No hay torneos pendientes");
		}

	}

	/**
	 * Selecciona una carrera no finalizada y la inicia
	 */
	public void iniciarCarrera() {
		List<Carrera> carrerasPendientes = control.getCarrerasPendientes();
		if (!carrerasPendientes.isEmpty()) {
			Menu menu = new Menu(false);
			menu.setTitulo("Iniciar Carrera");
			menu.setSubTitulo("Iniciar");
			for (Carrera carrera: carrerasPendientes) {
				menu.addOpcion(carrera.getNOMBRE(), null);
			}
			menu.run();
			try {
				carrerasPendientes.get(menu.getIndexOpcion()).iniciar();
				//carrerasPendientes.get(menu.getIndexOpcion()).mostrarPuntuacionesCoches();
			} catch (IncompleteException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("No hay carreras pendientes");
		}

	}
}
