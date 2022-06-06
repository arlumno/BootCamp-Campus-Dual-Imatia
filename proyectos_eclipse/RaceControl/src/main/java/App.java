import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App {
	private static Control control = null;
	private static final File dataFile = new File("src/main/java/data.json");
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	// https://danielme.com/2013/07/11/json-y-java-android-introduccion-a-gson/

	public static void main(String[] args) {
		if (dataFile.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(dataFile));) {
				String linea;
				StringBuilder dataJsonSB = new StringBuilder();
				while ((linea = br.readLine()) != null) {
					dataJsonSB.append(linea);
				}
				control = gson.fromJson(dataJsonSB.toString(), Control.class);
				control.syncAllReferences();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			control = new Control();
			control.resetAndLoadDefault();
		}

		Scanner lector = new Scanner(System.in);
		AppAcciones appAcciones = new AppAcciones(lector, control);

		Menu menu = new Menu();
		menu.setTitulo("RaceControl");
		menu.addOpcion("Iniciar Torneo", appAcciones::iniciarTorneo);
		menu.addOpcion("Crear Coche", appAcciones::crearCoche);
		menu.addOpcion("Listar Coches", appAcciones::listarCoches);
		menu.addOpcion("Crear Garaje", appAcciones::crearGaraje);
		menu.addOpcion("Listar Garajes", appAcciones::listarGarajes);
		menu.addOpcion("Crear carrera", appAcciones::crearCarrera);
		menu.addOpcion("Listar carreras", appAcciones::listarCarreras);
		menu.addOpcion("Crear torneo", appAcciones::crearTorneo);
		menu.addOpcion("Listar torneos", appAcciones::listarTorneos);
		menu.addOpcion("Resetear Aplicación", control::resetAndLoadDefault);
		menu.run();

		System.out.println("Programa finalizado");
		salir();
	}

	public static void salir() {
		String controlJson = gson.toJson(control);
//		System.out.println(controlJson);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));) {
			bw.write(controlJson);
			System.err.println("Archivo de datos guardado");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
