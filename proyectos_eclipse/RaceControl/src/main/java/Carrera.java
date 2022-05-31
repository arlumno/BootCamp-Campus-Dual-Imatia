import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import Excepciones.IncompleteException;

public class Carrera {
	private final String NOMBRE;
	private final int DURACION_MINUTOS;
	public final static int DURACION_MINUTOS_DEFAULT = 180;
	private ArrayList<Garaje> garajes;
	private ArrayList<Coche> coches = new ArrayList<Coche>();

	private TreeMap<Coche, Double> distanciaRecorrida = new TreeMap<Coche, Double>();
	//private List<Entry<Coche, Double>> resultados;
	
	private ArrayList<ArrayList<Coche>> podio;
	boolean carreraFinalizada = false;

	public Carrera(String nOMBRE, ArrayList<Garaje> garajes,int dURACION_MINUTOS) {
		NOMBRE = nOMBRE;
		DURACION_MINUTOS = dURACION_MINUTOS;
		this.garajes = garajes;		
				
		podio.add(new ArrayList<Coche>());//puesto 1
		podio.add(new ArrayList<Coche>());//puesto 2
		podio.add(new ArrayList<Coche>());//puesto 3
	}
	
	public Carrera(String nOMBRE, ArrayList<Garaje> garajes) {
		this(nOMBRE,garajes, DURACION_MINUTOS_DEFAULT);		
	}
	
	public Carrera(String nOMBRE, int dURACION_MINUTOS) {
		this(nOMBRE,new ArrayList<Garaje>(), dURACION_MINUTOS);		
	}

	public Carrera(String nOMBRE) {
		this(nOMBRE, DURACION_MINUTOS_DEFAULT);
	}
	
	
	public ArrayList<Garaje> getGarajes() {
		return garajes;
	}

	public void setGarajes(ArrayList<Garaje> garajes) {
		this.garajes = garajes;
	}

	public void addGaraje(Garaje garaje) {
		if (!garajes.contains(garaje)) {
			garajes.add(garaje);
		}
	}

	private void preparar() throws IncompleteException {
		coches.clear();
		if(garajes.size() > 1) {
			for(Garaje garaje: garajes) {
				coches.add(garaje.getCoche());
			}
		}else if(garajes.size() == 1) {
			coches.addAll(garajes.get(0).getCoches());
		}
		if(coches.size() < 3) {
			throw new IncompleteException("La carrena requiere mínimo 3 vehículos");
		}
		
		for(Coche coche: coches) {
			coche.preparar();
			distanciaRecorrida.clear();
			distanciaRecorrida.put(coche, 0D);
		}
		
		
	}

	public void iniciar() throws IncompleteException {
		// preparamos la carrera:
		preparar();


		// empieza la carrera.
		double tramo = 0;
		for (int i = 0; i < DURACION_MINUTOS; i++) {
			for (Coche coche : coches) {
				coche.conducir();
				tramo = ((double) coche.getSpeedometer()) / 60D;// paso km/h a minutos
				distanciaRecorrida.put(coche, distanciaRecorrida.get(coche) + tramo);

			}
		}

		// https://www.delftstack.com/es/howto/java/how-to-sort-a-map-by-value-in-java/
		List<Entry<Coche, Double>> resultados  = new ArrayList<Entry<Coche, Double>>(distanciaRecorrida.entrySet());
		resultados.sort(Entry.comparingByValue());
		
		int puestoPodio = 0;
		double aux= -1;
		
		for (int i = resultados.size() - 1; i >= 0; i--) {
			if(aux == -1) {//primero a añadir
				podio.get(puestoPodio).add(resultados.get(i).getKey());				
			} else {
				if (aux > resultados.get(i).getValue()) {
					puestoPodio++;				
					if(puestoPodio == 3) { // se excede el podio
						break;
					}
				}
				podio.get(puestoPodio).add(resultados.get(i).getKey());				
			}
			aux = resultados.get(i).getValue();
		}
		carreraFinalizada = true;
	}

	
	public ArrayList<ArrayList<Coche>> getPodio() {
		if(carreraFinalizada) {
			return podio;			
		}else {
			return null;
		}
	}

	@Override
	public int hashCode() {
		return NOMBRE.hashCode();
	}
	
}
