import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Excepciones.IncompleteException;

public class AppAcciones {
	Scanner lector;
	Control control;

	public AppAcciones(Scanner lector, Control control) {
		this.lector = lector;
		this.control = control;
	}

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

	public void listarCoches() {
		System.out.println(control.listarCoches());
	}

	public void crearGaraje() {
		System.out.println("Introduzca el nombre del garaje.");
		Garaje garaje = new Garaje(EntradasConsola.pedirString(lector));

		if (control.addGaraje(garaje)) {
			System.out.println("Garaje creado");
		} else {
			System.err.println("Error al crear garaje");
		}
	}

	public void listarGarajes() {
		System.out.println(control.listarGarajes());
	}

	public void listarCarreras() {
		System.out.println(control.listarCarreras());
	}

	public void listarTorneos() {
		System.out.println(control.listarTorneos());
	}

	public void crearCarrera() {
		System.out.println("Introduzca el nombre de la Carrera.");
		Carrera carrera = new Carrera(EntradasConsola.pedirString(lector));

		if (control.addCarrera(carrera)) {
			System.out.println("Carrera creada");
		} else {
			System.err.println("Error al crear carrera");
		}
	}

	public void crearTorneo() {
		System.out.println("Introduzca el nombre del Torneo.");
		Torneo torneo = new Torneo(EntradasConsola.pedirString(lector));

		if (control.addTorneo(torneo)) {
			System.out.println("Torneo creado");
		} else {
			System.err.println("Error al crear Torneo");
		}
	}

	public void addCarreraTorneo() {
		List<Torneo> torneosValidos = new ArrayList<>();
		List<Carrera> carrerasAsignables = control.getCarrerasSinAsignar();
		torneosValidos.addAll(control.getTorneos());
		torneosValidos.removeIf(it -> it.torneoFinalizado);

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
						menuCoches.addOpcion(coche.getBRAND() + " - " + coche.getMODEL(), () -> control
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
				garajesAsignables.removeAll(carrerasPendientes.get(menuCarreras.getIndexOpcion()).getGarajes());
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

	public void iniciarTorneo() {
		List<Torneo> torneosPendientes = new ArrayList<>();
		torneosPendientes.addAll(control.getTorneos());
		torneosPendientes.removeIf(it -> it.isTorneoFinalizado());
		if (!torneosPendientes.isEmpty()) {
			Menu menu = new Menu(false);
			menu.setTitulo("Iniciar Carrera");
			menu.setSubTitulo("Iniciar");
			for (Torneo torneo : torneosPendientes) {
				menu.addOpcion(torneo.getNOMBRE(), null);
			}
			menu.run();
			try {
				torneosPendientes.get(menu.getIndexOpcion()).iniciar();
			} catch (IncompleteException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("No hay torneos pendientes");
		}

	}
}
