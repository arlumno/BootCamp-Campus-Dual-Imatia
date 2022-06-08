package pojos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import controlador.Control;

public class Garaje {
	private final String NOMBRE;
	private List<Coche> coches = new ArrayList<>();
		
	/**
	 * 
	 * @param nOMBRE Nombre del garaje
	 * @param coches Lista de coches a asignar. Solo as�gna los coches aptos y la lista no matiene la referencia
	 */
	public Garaje(String nOMBRE,List<Coche> coches) {		
		NOMBRE = nOMBRE;
		for(Coche coche: coches) {
			addCoche(coche);
		}
	}

	/**
	 * 
	 * @param nOMBRE Nombre del garaje
	 */
	public Garaje(String nOMBRE) {					
		NOMBRE = nOMBRE;		
	}
	
	/**
	 * Asigna un Coche al garaje y lo a�ade a la lista de coches si se le puede asignar la pegatina con el nombre del garaje 
	 * @param coche Objeto Coche a asignar
	 * @return True si asigna el coche, False si no puede asignarlo porque pertenece a otro garaje o ya est� en la lista de coches
	 */
	public boolean addCoche(Coche coche) {
		boolean resultado = false;
		if(coche.setPegatinaGaraje(NOMBRE) && !coches.contains(coche)) {
			coches.add(coche);
			resultado = true;			
		}
		return resultado;
	}

	/**
	 * Obtiene un coche de manera aleatoria de la lista.
	 * @return Coche aleatorio de la lista. 
	 */
	public Coche getCoche() {
		Coche coche = null;
		if(!coches.isEmpty()) {
			coche = coches.get((int) Math.random()*coches.size());
		}
		return coche;
	}

	public Collection<Coche> getCoches() {
		return coches;
	}

	public void setCoches(List<Coche> coches) {
		this.coches = coches;
	}

	public String getNOMBRE() {
		return NOMBRE;
	}
	
	/**
	 * Actualiza y vincula las referencias de los coches que son iguales, en la
	 * lista de coches. Si el coche no existe en listaMain, se a�ade.
	 * 
	 * @param cochesMain Lista de coches maestra de donde se obtiene las
	 *                   referencias. Si no existe el coche de la lista garaje se
	 *                   a�ade a cochesMain
	 */
	public void syncReferences(List<Coche> cochesMain) {
		Control.syncReferences(cochesMain, coches);		
	}
	
	@Override
	public int hashCode() {		 
		return NOMBRE.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Garaje) {
			return ((Garaje) obj).getNOMBRE().equals(NOMBRE);
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return "\n Garaje [NOMBRE=" + NOMBRE + ", coches=\n" + coches + "]";
	}
	
	
}
