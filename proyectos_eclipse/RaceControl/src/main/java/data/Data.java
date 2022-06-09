package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import controlador.Control;
import pojos.Carrera;
import pojos.CarreraEliminacion;
import pojos.CarreraEstandar;
import pojos.Coche;
import pojos.Garaje;
import pojos.Torneo;

public class Data {
	Control control;
	List<Coche> coches;
	List<Garaje> garajes;
	List<Carrera> carreras;
	List<Torneo> torneos;
	private final File dataFileCoches = new File("src/main/java/data/coches.json");
	private final File dataFileGarajes = new File("src/main/java/data/garajes.json");
	private final File dataFileCarreras = new File("src/main/java/data/carreras.json");
	private final File dataFileTorneos = new File("src/main/java/data/torneos.json");
	private Gson gson = null;

	public Data(Control control) {
		this.control = control;
		// delegación
		coches = control.getCoches();
		garajes = control.getGarajes();
		carreras = control.getCarreras();
		torneos = control.getTorneos();		
	}

	public void loadData() {

		if (dataFileCoches.exists() && dataFileGarajes.exists() && dataFileCarreras.exists()
				&& dataFileTorneos.exists()) {
			coches = getGson().fromJson(stringFromFile(dataFileCoches), new TypeToken<List<Coche>>() {
			}.getType());
			garajes = getGson().fromJson(stringFromFile(dataFileGarajes), new TypeToken<List<Garaje>>() {
			}.getType());
			torneos = getGson().fromJson(stringFromFile(dataFileTorneos), new TypeToken<List<Torneo>>() {
			}.getType());
			carreras = getGson().fromJson(stringFromFile(dataFileCarreras), new TypeToken<List<Carrera>>() {}.getType());
			control.syncAllReferences();
		} else {
			loadDefault();
		}
	}

	public void saveData() {
		// Guardar coches a Json
		if (stringToFile(dataFileCoches, getGson().toJson(coches))) {
			System.err.println("Coches guardados con éxito en " + dataFileCoches.getName());
		} else {
			System.err.println("Error al guardar Coches en " + dataFileCoches.getName());
		}

		// Guardar garajes a Json
		if (stringToFile(dataFileGarajes, getGson().toJson(garajes))) {
			System.err.println("Garajes guardados con éxito en " + dataFileGarajes.getName());
		} else {
			System.err.println("Error al guardar Garajes en " + dataFileGarajes.getName());
		}

		// Guardar carreras a Json
		if (stringToFile(dataFileCarreras, getGson().toJson(carreras,new TypeToken<List<Carrera>>() {}.getType()))) {
			System.err.println("Carreras guardadas con éxito en " + dataFileCarreras.getName());
		} else {
			System.err.println("Error al guardar Carreras en " + dataFileCarreras.getName());
		}
		// Guardar torneos a Json
		if (stringToFile(dataFileTorneos, getGson().toJson(torneos))) {
			System.err.println("Torneos guardados con éxito en " + dataFileTorneos.getName());
		} else {
			System.err.println("Error al guardar Torneos en " + dataFileTorneos.getName());
		}

	}

	private String stringFromFile(File file) {
		StringBuilder result = new StringBuilder();
		if (file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file));) {
				String linea;
				while ((linea = br.readLine()) != null) {
					result.append(linea);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return result.toString();
	}

	private boolean stringToFile(File file, String content) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file));) {
			bw.write(content);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Gson getGson() {
		if (gson == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(Carrera.class, new InterfaceAdapter<Carrera>());
			gsonBuilder.setPrettyPrinting();
			gson = gsonBuilder.create();
		}
		return gson;
	}

	private void loadDefault() {
		System.err.println("********************************************");
		System.err.println("**********CARGANDO DATOS POR DEFECTO********");
		System.err.println("********************************************");

		// coches
		control.addCoche(new Coche("Renaul", "Clio"));// 0
		control.addCoche(new Coche("Ford", "Fiesta"));// 1
		control.addCoche(new Coche("Renaul", "Traffic"));
		control.addCoche(new Coche("Seat", "F1"));
		control.addCoche(new Coche("Seat", "F2"));
		control.addCoche(new Coche("Seat", "F3"));// 5
		control.addCoche(new Coche("Seat", "F4"));
		control.addCoche(new Coche("Seat", "F5"));
		control.addCoche(new Coche("5-Ferrari", "F50"));// 8
		control.addCoche(new Coche("Fiat", "500"));
		control.addCoche(new Coche("Fiat", "600"));
		control.addCoche(new Coche("Fiat", "700"));// 11
		control.addCoche(new Coche("Ford", "Mustang"));
		control.addCoche(new Coche("Ford", "Mondeo"));

		// garajes
		Garaje g01 = new Garaje("Los Manquiñas");
		g01.addCoche(coches.get(0));
		g01.addCoche(coches.get(1));
		g01.addCoche(coches.get(2));
		control.addGaraje(g01);

		Garaje g02 = new Garaje("Los Setas");
		g02.addCoche(coches.get(3));
		g02.addCoche(coches.get(4));
		g02.addCoche(coches.get(5));
		g02.addCoche(coches.get(6));
		g02.addCoche(coches.get(7));
		control.addGaraje(g02);

		Garaje g03 = new Garaje("Lone Wolf");
		g03.addCoche(coches.get(8));
		control.addGaraje(g03);

		Garaje g04 = new Garaje("Mancato");
		g04.addCoche(coches.get(9));
		g04.addCoche(coches.get(10));
		g04.addCoche(coches.get(11));
		control.addGaraje(g04);

		Garaje g05 = new Garaje("Los Fordinangs");
		g05.addCoche(coches.get(12));
		g05.addCoche(coches.get(13));
		control.addGaraje(g05);

		// carreras
		CarreraEstandar c01 = new CarreraEstandar("Gran Prince");
		c01.addGaraje(g01);
		c01.addGaraje(g02);
		c01.addGaraje(g03);
		control.addCarrera(c01);

		CarreraEstandar c02 = new CarreraEstandar("Civil War");
		c02.addGaraje(g04);
		control.addCarrera(c02);

		CarreraEstandar c03 = new CarreraEstandar("Vigo Rally");
		c03.addGaraje(g01);
		c03.addGaraje(g02);
		c03.addGaraje(g03);
		c03.addGaraje(g04);
		control.addCarrera(c03);

		CarreraEliminacion c04 = new CarreraEliminacion("Carrera Eliminatoria");
		c04.addGaraje(g01);
		c04.addGaraje(g02);
		c04.addGaraje(g03);
		c04.addGaraje(g04);
		control.addCarrera(c04);

		// torneos
		Torneo t01 = new Torneo("24 horas de limons");
		t01.addCarrera(c01);
		t01.addCarrera(c02);
		t01.addCarrera(c03);
		t01.addCarrera(c04);
		control.addTorneo(t01);

	}
}
