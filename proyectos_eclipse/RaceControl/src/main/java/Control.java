import java.util.ArrayList;

import Excepciones.SuperPilotException;

public class Control {

	ArrayList<Garaje> garajes = new ArrayList<Garaje>();
	ArrayList<Carrera> carreras = new ArrayList<Carrera>(); 
	ArrayList<Torneo> torneos = new ArrayList<Torneo>();
	
	public Control(ArrayList<Garaje> garajes, ArrayList<Carrera> carreras, ArrayList<Torneo> torneos) {
		this.garajes = garajes;
		this.carreras = carreras;
		this.torneos = torneos;
	}
	public Control() {

	}
	public boolean crearTorneo() {
		boolean resultado = false;
		return resultado;
	}
	
	public boolean crearCarrera() {
		boolean resultado = false;
		return resultado;
	}
	
	public boolean crearGaraje() {
		boolean resultado = false;
		return resultado;
	}
	
	public boolean crearCoche() {
		boolean resultado = false;
		return resultado;
	}

	public void mostrarResultadoTorneo(int index) {
		if(index < torneos.size()) {		
			System.out.println(torneos.get(index).mostrarPuntuacionesCoches());
		}else {		
			System.err.println(ControlErrors.ERROR_TORNEO_NO_EXISTE);
		}
	}
	
	public void mostrarResultadoTorneo(String nombre) {
		boolean encontrado = false;
		for(int i = 0; i < torneos.size() && !encontrado ; i ++) {
			if(torneos.get(i).getNOMBRE().equals(nombre)) {
				System.out.println(torneos.get(i).mostrarPuntuacionesCoches());
				encontrado  = true;
			}
		}
		if(!encontrado) {
			System.err.println(ControlErrors.ERROR_TORNEO_NO_EXISTE);
		}		
	}
	
	public void mostrarResultadoTorneos() {
		System.out.println("Resultado Torneos (" + torneos.size()+"):");
		for(Torneo torneo: torneos) {
			System.out.println(torneo.mostrarPuntuacionesCoches());
		}
	}
	
	
	public void resetAndLoadDefault() throws SuperPilotException {
		System.err.println("********************************************");
		System.err.println("**********CARGANDO DATOS POR DEFECTO********");
		System.err.println("********************************************");
		garajes = new ArrayList<Garaje>();
		carreras = new ArrayList<Carrera>(); 
		torneos = new ArrayList<Torneo>();
		
		//garajes
		Garaje g01 = new Garaje("Los Manquiñas");
		garajes.add(g01);
		g01.addCoche(new Coche("Renaul", "Clio"));
		g01.addCoche(new Coche("Ford", "Fiesta"));
		g01.addCoche(new Coche("Renaul", "Traffic"));		
		
		Garaje g02  = new Garaje("Los Flipados");
		garajes.add(g02);
		g02.addCoche(new Coche("Seat", "F1"));
		g02.addCoche(new Coche("Seat", "F2"));
		g02.addCoche(new Coche("Seat", "F3"));
		g02.addCoche(new Coche("Seat", "F4"));
		g02.addCoche(new Coche("Seat", "F5"));	
		
		Garaje g03  = new Garaje("Lone Wolf");
		garajes.add(g03);
		g03.addCoche(new Coche("5-Ferrari", "F50", 60));			
		
		Garaje g04  = new Garaje("Mancato");
		garajes.add(g04);
		g04.addCoche(new Coche("Fiat", "500"));
		g04.addCoche(new Coche("Fiat", "600"));
		g04.addCoche(new Coche("Fiat", "700"));
		
		//carreras
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
		
		//torneos 
		Torneo t01 = new Torneo("24 horas de limons");
		torneos.add(t01);
		t01.addCarrera(c01);
		t01.addCarrera(c02);
		t01.addCarrera(c03);
		t01.addCarrera(c04);
		
	}
	@Override
	public String toString() {
		return "Control [garajes=" + garajes + ", carreras=" + carreras + ", torneos=" + torneos + "]";
	}
	
}
