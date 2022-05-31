import java.util.ArrayList;

import Excepciones.SuperPilotException;

public class Loader {
	public static Control load() throws SuperPilotException {
		Control control = null;
		
		ArrayList<Coche> listaCoches;
		ArrayList<Garaje> garajes = new ArrayList<Garaje>();
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>(); 
		ArrayList<Torneo> listaTorneos = new ArrayList<Torneo>();
		Torneo torneo;
		Carrera carrera;
		Garaje garaje;
		Coche coche;
		
		listaCoches = new ArrayList<Coche>();
		listaCoches.add(new Coche("Renaul", "Clio", 70));
		listaCoches.add(new Coche("Renaul", "Clio", 70));
		listaCoches.add(new Coche("Renaul", "Clio", 70));
		System.out.println(listaCoches.size());
		torneo = new Torneo("24 horas de limons", listaCarreras);		
		
		return control;
	}

}
