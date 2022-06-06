import java.util.ArrayList;
import java.util.List;

import Excepciones.SuperPilotException;

public class Control {
	List<Coche> coches = new ArrayList<>();
	List<Garaje> garajes = new ArrayList<>();
	List<Carrera> carreras = new ArrayList<>();
	List<Torneo> torneos = new ArrayList<>();

	public Control(List<Garaje> garajes, List<Carrera> carreras, List<Torneo> torneos) {
		this.garajes = garajes;
		this.carreras = carreras;
		this.torneos = torneos;
	}

	public Control() {

	}

	public boolean addTorneo(Torneo torneo) {
		boolean resultado = true;
		if (torneos.contains(torneo)) {
			resultado = false;
		} else {
			torneos.add(torneo);
		}
		return resultado;
	}

	public void listarTorneos() {
		System.out.println("Resultado Torneos (" + torneos.size() + "):");
		for (Torneo torneo : torneos) {
			System.out.println(torneo.mostrarPuntuacionesCoches());
		}
	}

	public boolean addCarrera(Carrera carrera) {
		boolean resultado = true;
		if (carreras.contains(carrera)) {
			resultado = false;
		} else {
			carreras.add(carrera);
		}
		return resultado;
	}

	public boolean addGaraje(Garaje garaje) {
		boolean resultado = true;
		if (garajes.contains(garaje)) {
			resultado = false;
		} else {
			garajes.add(garaje);
		}
		return resultado;
	}

	public void listarGarajes() {
		System.out.println("Garajes: (" + garajes.size() + "):");
		for (Garaje garaje : garajes) {
			System.out.println(garaje.toString());
		}
	}

	public boolean addCoche(Coche coche) {
		boolean resultado = true;
		if (coches.contains(coche)) {
			resultado = false;
		} else {
			coches.add(coche);
		}
		return resultado;
	}

	public void listarCoches() {
		System.out.println("Coches: (" + coches.size() + "):");
		for (Coche coche : coches) {
			System.out.println(coche.toString());
		}
	}

	public void mostrarResultadoTorneo(int index) {
		if (index < torneos.size()) {
			System.out.println(torneos.get(index).mostrarPuntuacionesCoches());
		} else {
			System.err.println(ControlErrors.ERROR_TORNEO_NO_EXISTE);
		}
	}

	public void mostrarResultadoTorneo(String nombre) {
		boolean encontrado = false;
		for (int i = 0; i < torneos.size() && !encontrado; i++) {
			if (torneos.get(i).getNOMBRE().equals(nombre)) {
				System.out.println(torneos.get(i).mostrarPuntuacionesCoches());
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.err.println(ControlErrors.ERROR_TORNEO_NO_EXISTE);
		}
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

	public void syncAllReferences() {

		for(Garaje garaje: garajes) {
			garaje.syncReferences(coches);
		}
		
		for(Carrera carrera: carreras) {
			carrera.syncReferences(garajes,coches);
		}
		
		for(Torneo torneo: torneos) {
			torneo.syncReferences(carreras, garajes,coches);
		}
		System.err.println(">> Referencias sincronizadads");
	}
	
	public static <T> void syncReferences(List<T> listMain, List<T> listToUpdateReferences) {
		for(int i = 0; i < listToUpdateReferences.size();i++) {
			if(listMain.contains(listToUpdateReferences.get(i))){
				listToUpdateReferences.set(i, listMain.get(listMain.indexOf(listToUpdateReferences.get(i))));
			}else {
				listMain.add(listToUpdateReferences.get(i));
			}
		}		
	}
	
	public void resetAndLoadDefault() {
		System.err.println("********************************************");
		System.err.println("**********CARGANDO DATOS POR DEFECTO********");
		System.err.println("********************************************");
		garajes = new ArrayList<>();
		carreras = new ArrayList<>();
		torneos = new ArrayList<>();

		try {
			// garajes
			Garaje g01 = new Garaje("Los Manquiñas");
			garajes.add(g01);
			g01.addCoche(new Coche("Renaul", "Clio"));
			g01.addCoche(new Coche("Ford", "Fiesta"));
			g01.addCoche(new Coche("Renaul", "Traffic"));

			Garaje g02 = new Garaje("Los Flipados");
			garajes.add(g02);
			g02.addCoche(new Coche("Seat", "F1"));
			g02.addCoche(new Coche("Seat", "F2"));
			g02.addCoche(new Coche("Seat", "F3"));
			g02.addCoche(new Coche("Seat", "F4"));
			g02.addCoche(new Coche("Seat", "F5"));

			Garaje g03 = new Garaje("Lone Wolf");
			garajes.add(g03);
			g03.addCoche(new Coche("5-Ferrari", "F50", 60));

			Garaje g04 = new Garaje("Mancato");
			garajes.add(g04);
			g04.addCoche(new Coche("Fiat", "500"));
			g04.addCoche(new Coche("Fiat", "600"));
			g04.addCoche(new Coche("Fiat", "700"));

			// carreras
			Carrera c01 = new Carrera("Primera Carrera");
			carreras.add(c01);
			c01.addGaraje(g01);
			c01.addGaraje(g02);
			c01.addGaraje(g03);

			Carrera c02 = new Carrera("Segunda carrera");
			carreras.add(c02);
			c02.addGaraje(g04);

			Carrera c03 = new Carrera("Tercera carrera");
			carreras.add(c03);
			c03.addGaraje(g01);
			c03.addGaraje(g02);
			c03.addGaraje(g03);
			c03.addGaraje(g04);

			Carrera c04 = new CarreraEliminacion("Carrera Eliminatoria");
			carreras.add(c04);
			c04.addGaraje(g01);
			c04.addGaraje(g02);
			c04.addGaraje(g03);
			c04.addGaraje(g04);

			// torneos
			Torneo t01 = new Torneo("24 horas de limons");
			torneos.add(t01);
			t01.addCarrera(c01);
			t01.addCarrera(c02);
			t01.addCarrera(c03);
			t01.addCarrera(c04);
		} catch (SuperPilotException e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	public String toString() {
		return "Control [garajes=" + garajes + ", carreras=" + carreras + ", torneos=" + torneos + "]";
	}

}
