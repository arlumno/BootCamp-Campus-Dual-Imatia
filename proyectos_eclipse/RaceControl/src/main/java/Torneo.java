import java.util.ArrayList;
import java.util.TreeMap;

import Excepciones.IncompleteException;

public class Torneo {
	private final String NOMBRE;
	ArrayList<Carrera> carreras;
	private ArrayList<Garaje> garajes;
	boolean torneoFinalizado = false;
	private TreeMap<Coche, Integer> puntuacionCoches = new TreeMap<Coche, Integer>();
	private int[] puntuacionesCarreras= {20,10,5};
	
	public Torneo(String nOMBRE, ArrayList<Carrera> carreras) {
		NOMBRE = nOMBRE;
		this.carreras = carreras;
		
		for(Carrera carrera: carreras) {
			garajes.addAll(carrera.getGarajes());
		}
	}
	
	public Torneo(String nOMBRE) {
		this(nOMBRE,new ArrayList<Carrera>());
	}
	
	public void addCarrera(Carrera carrera) {
		if(!carreras.contains(carrera)) {
			carreras.add(carrera);
			garajes.addAll(carrera.getGarajes());
		}
	}
	
	public void iniciar() throws IncompleteException {
		if(!torneoFinalizado) {
			for(Carrera carrera: carreras) {
				carrera.iniciar();
				guardarPuntuacion(carrera.getPodio());
			}			
		}	
		torneoFinalizado = true;
	}
	
	public void guardarPuntuacion(ArrayList<ArrayList<Coche>> podio) {
		if(podio != null) {
			for(int i = 0; i < puntuacionesCarreras.length; i++) {
				for(Coche coche:podio.get(i)) {
					if(puntuacionCoches.containsKey(coche)) {
						puntuacionCoches.put(coche, puntuacionCoches.get(coche) + puntuacionesCarreras[0]);
					}else {
						puntuacionCoches.put(coche, puntuacionesCarreras[0]);					
					}
				}
				
			}
		}
	}
	
	public TreeMap<Coche, Integer> getPuntuacionCoches() {
		return puntuacionCoches;
	}
}
