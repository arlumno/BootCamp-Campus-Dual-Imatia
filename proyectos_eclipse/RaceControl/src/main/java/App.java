import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import controlador.Control;
import data.Data;
import data.InterfaceAdapter;
import menu.Menu;
import pojos.Carrera;

public class App {
	private static Control control = null;
//	private static final File dataFile = new File("src/main/java/data.json");
	// https://danielme.com/2013/07/11/json-y-java-android-introduccion-a-gson/

	public static void main(String[] args) {

//		if (dataFile.exists()) {
//			try (BufferedReader br = new BufferedReader(new FileReader(dataFile));) {
//				String linea;
//				StringBuilder dataJsonSB = new StringBuilder();
//				while ((linea = br.readLine()) != null) {
//					dataJsonSB.append(linea);
//				}
//				control = getGson().fromJson(dataJsonSB.toString(), Control.class);
//				control.syncAllReferences();
//			} catch (IOException e) {
//				System.out.println(e.getMessage());
//			}
//		} else {
//			control = new Control();
//			control.resetAndLoadDefault();
//		}
		
		control = new Control();
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
		menu.run();

		data.saveData();
		System.out.println("Programa finalizado");
//		salir();
	}

//	public static void salir() {
//		String controlJson = getGson().toJson(control);
//		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));) {
//			bw.write(controlJson);
//			System.err.println("Archivo de datos guardado");
//		} catch (Exception e) {
//			System.out.println(e.toString());
//		}
//	}
//	public static Gson getGson() {
//		GsonBuilder gsonBuilder = new GsonBuilder();
//		gsonBuilder.registerTypeAdapter(Carrera.class, new InterfaceAdapter<Carrera>());
//		gsonBuilder.setPrettyPrinting();
//		Gson gson = gsonBuilder.create();
//		return gson;
//	}
}
