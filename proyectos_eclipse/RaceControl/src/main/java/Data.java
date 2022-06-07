import java.util.ArrayList;
import java.util.List;

import Excepciones.SuperPilotException;

public class Data {
	Control control;
	List<Coche> coches = new ArrayList<>();
	List<Garaje> garajes = new ArrayList<>();
	List<Carrera> carreras = new ArrayList<>();
	List<Torneo> torneos = new ArrayList<>();
	public Data(Control control) {
		this.control = control;
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
			CarreraEstandar c01 = new CarreraEstandar("Primera Carrera");
			carreras.add(c01);
			c01.addGaraje(g01);
			c01.addGaraje(g02);
			c01.addGaraje(g03);

			CarreraEstandar c02 = new CarreraEstandar("Segunda carrera");
			carreras.add(c02);
			c02.addGaraje(g04);

			CarreraEstandar c03 = new CarreraEstandar("Tercera carrera");
			carreras.add(c03);
			c03.addGaraje(g01);
			c03.addGaraje(g02);
			c03.addGaraje(g03);
			c03.addGaraje(g04);

			CarreraEliminacion c04 = new CarreraEliminacion("Carrera Eliminatoria");
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
}
