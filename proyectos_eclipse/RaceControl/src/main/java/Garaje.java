import java.util.ArrayList;
import java.util.Collection;

public class Garaje {
	private ArrayList<Coche> coches = new ArrayList<Coche>();
	private final String NOMBRE;
	
	
	public Garaje(String nOMBRE) {			
		NOMBRE = nOMBRE;
	}
	
	public boolean addCoche(Coche coche) {
		boolean resultado = false;
		if(coche.setPegatinaGaraje(NOMBRE)) {
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
}
