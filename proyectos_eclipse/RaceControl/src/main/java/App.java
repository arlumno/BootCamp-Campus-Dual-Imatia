import java.util.Scanner;

import controlador.Control;
import data.Data;
import menu.Menu;

public class App {

	public static void main(String[] args) {	
		Control control = new Control();
		Data data = new Data(control);
		data.loadData();
		
		Scanner lector = new Scanner(System.in);
		AppAcciones appAcciones = new AppAcciones(lector, control);		
		Menu menu = new Menu();
		menu.setTitulo("RaceControl");
		menu.addOpcion("Iniciar Torneo", appAcciones::iniciarTorneo);
		menu.addOpcion("Crear Coche", appAcciones::crearCoche);
		menu.addOpcion("Crear Garaje", appAcciones::crearGaraje);
		menu.addOpcion("Crear Carrera", appAcciones::crearCarrera);
		menu.addOpcion("Crear Torneo", appAcciones::crearTorneo);		
		menu.addOpcion("Listar Coches", appAcciones::listarCoches);
		menu.addOpcion("Listar Garajes", appAcciones::listarGarajes);
		menu.addOpcion("Listar Carreras", appAcciones::listarCarreras);
		menu.addOpcion("Listar Torneos", appAcciones::listarTorneos);
		menu.addOpcion("Asignar Coche a Garaje", appAcciones::addCocheGaraje);
		menu.addOpcion("Asignar Garaje a Carrera", appAcciones::addGarajeCarrera);
		menu.addOpcion("Añadir Carrera a Torneo", appAcciones::addCarreraTorneo);
		menu.addOpcion("Resetear Aplicación", control::resetAndLoadDefault);
		menu.addOpcion("Guardar Estado", data::saveData);
		menu.run();

		data.saveData();
		System.out.println("Programa finalizado");
	}
}
