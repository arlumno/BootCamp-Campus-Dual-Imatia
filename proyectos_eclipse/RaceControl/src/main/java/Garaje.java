import java.util.ArrayList;
import java.util.Collection;

public class Garaje {
	private final String NOMBRE;
	private ArrayList<Coche> coches = new ArrayList<Coche>();
		
	
	public Garaje(String nOMBRE,ArrayList<Coche> coches) {		
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
		if(coches.size()>0) {
			coche = coches.get((int) Math.random()*coches.size());
		}
		return coche;
	}

	public Collection<Coche> getCoches() {
		return coches;
	}

	public void setCoches(ArrayList<Coche> coches) {
		this.coches = coches;
	}

	public String getNOMBRE() {
		return NOMBRE;
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
