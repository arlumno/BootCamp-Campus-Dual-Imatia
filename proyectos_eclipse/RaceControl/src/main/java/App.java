import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Excepciones.SuperPilotException;

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
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			try {
				control = new Control();
				control.resetAndLoadDefault();
			} catch (SuperPilotException e) {
				e.printStackTrace();
			}

		}

		System.out.println(control);

//			int option = -1;
//			do {
//				
//			
//			}while(option != 0);

//			Gson gson = new Gson();
		// System.out.println(resultado);

		salir();
	}

	public static void salir() {
		String controlJson = gson.toJson(control);
		System.out.println(controlJson);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));) {
			bw.write(controlJson);
			System.err.println("Archivo de datos actualizado");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
