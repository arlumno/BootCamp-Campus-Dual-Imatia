import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Garaje {
	private final String NOMBRE;
	private List<Coche> coches = new ArrayList<>();
		
	
	public Garaje(String nOMBRE,List<Coche> coches) {		
		NOMBRE = nOMBRE;
		for(Coche coche: coches) {
			addCoche(coche);
		}
	}

	public Garaje(String nOMBRE) {					
		NOMBRE = nOMBRE;		
	}
	
	public boolean addCoche(Coche coche) {
		boolean resultado = false;
		if(coche.setPegatinaGaraje(NOMBRE) && !coches.contains(coche)) {
			coches.add(coche);
			resultado = true;			
		}
		return resultado;
	}

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
