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
	
	public void  crearCoche() {
		System.out.println("Introduzca la marca del vehículo:");
		String marca = EntradasConsola.pedirString(lector);
		System.out.println("Introduzca el modelo del vehículo:");
		String modelo = EntradasConsola.pedirString(lector);
		
		Coche coche = new Coche(marca, modelo);
		if(control.addCoche(coche)) {
			System.out.println("Coche creado");
		}else {
			System.err.println("Error al crear coche");
		}
	}
	public void listarCoches() {
		control.listarCoches();
	}
	
	public void crearGaraje() {
		System.out.println("Introduzca el nombre del garaje.");		
		Garaje garaje = new Garaje(EntradasConsola.pedirString(lector));
		
		if(control.addGaraje(garaje)) {
			System.out.println("Garaje creado");
		}else {
			System.err.println("Error al crear garaje");
		}
	}
	
	public void listarGarajes() {
		control.listarGarajes();
	}
	
	public void crearCarrera() {
		System.out.println("Introduzca el nombre de la Carrera.");		
		Carrera carrera= new Carrera(EntradasConsola.pedirString(lector));
		
		if(control.addCarrera(carrera)) {
			System.out.println("Carrera creado");
		}else {
			System.err.println("Error al crear carrera");
		}
	}
	
	public void listarCarreras() {
		control.listarGarajes();
	}
	
	public void crearTorneo() {
		System.out.println("Introduzca el nombre del Torneo.");		
		Torneo torneo= new Torneo(EntradasConsola.pedirString(lector));
		
		if(control.addTorneo(torneo)) {
			System.out.println("Torneo creado");
		}else {
			System.err.println("Error al crear Torneo");
		}
	}
	
	public void listarTorneos() {
		control.listarTorneos();
	}
	
	public void asignarCocheGaraje() {
		List<Coche> cochesValidos = new ArrayList<>();
		cochesValidos.addAll(control.getCoches());		
		cochesValidos.removeIf(it-> !it.getPegatinaGaraje().equals(""));
		System.out.println("indica a que garaje quieres añadir vehiculos");	
	}
	
	public void iniciarTorneo() {
		List<Torneo> torneosPendientes= new ArrayList<>();
		torneosPendientes.addAll(control.getTorneos());
		torneosPendientes.removeIf(it-> it.isTorneoFinalizado());
		if(!torneosPendientes.isEmpty()) {
			Menu menu = new Menu(false);
			menu.setTitulo("Iniciar Carrera");
			menu.setSubTitulo("Iniciar");
			for(Torneo torneo: torneosPendientes) {				
				menu.addOpcion(torneo.getNOMBRE(), null);
			}
			menu.run();
			//System.err.println("Seleccionado el " + menu.getIndexOpcion());
			try {
				torneosPendientes.get(menu.getIndexOpcion()).iniciar();
			} catch (IncompleteException e) {
				e.printStackTrace();
			}
		}else {
			System.err.println("No hay torneos pendientes");
		}
		
	}
}
