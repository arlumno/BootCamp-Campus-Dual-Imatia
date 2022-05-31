import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import Excepciones.SuperPilotException;

public class Loader {
	public static Control load() throws SuperPilotException {
		Control control = null;
		
		Set<Coche> listaCoches = new TreeSet<Coche>();
		ArrayList<Garaje> garajes = new ArrayList<Garaje>();
		ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>(); 
		ArrayList<Torneo> listaTorneos = new ArrayList<Torneo>();
		Torneo torneo;
		Carrera carrera;
		Garaje garaje;
		Coche coche;
		
		listaCoches.clear();
		
		coche = new Coche("Renaul", "Clio", 70);
		coche.setCounterKm((int)(Math.random()*100));
		listaCoches.add(coche);
		
		coche = new Coche("Renaul", "cosa", 70);
		coche.setCounterKm((int)(Math.random()*100));
		listaCoches.add(coche);
		
		coche = new Coche("Renaul", "megane", 80);
		coche.setCounterKm((int)(Math.random()*100));
		listaCoches.add(coche);
		
		
		coche = new Coche("Renaul", "Clio", 90);
		coche.setCounterKm((int)(Math.random()*100));
		listaCoches.add(coche);
		
		
		coche = new Coche("Renaul", "Clio", 90);
		coche.setCounterKm(100);
		listaCoches.add(coche);
		
		coche = new Coche("Renaul", "traffic", 90);
		coche.setCounterKm(100);
		listaCoches.add(coche);
		
		
		
		
//		
//		listaCoches.add(new Coche("Renaul", "Clio", 70));	
//		listaCoches.add(new Coche("Renaul", "cosa", 70));		
//		listaCoches.add(new Coche("Renaul", "megane", 80));		
//		listaCoches.add(new Coche("Renaul", "Clio", 90));
		
//		for(Coche car: listaCoches) {
//			car.setCounterKm((int)(Math.random()*100));
//		}
		
		System.out.println(listaCoches.size());
		System.out.println(listaCoches);
		
		torneo = new Torneo("24 horas de limons", listaCarreras);		
		
		return control;
	}

}
