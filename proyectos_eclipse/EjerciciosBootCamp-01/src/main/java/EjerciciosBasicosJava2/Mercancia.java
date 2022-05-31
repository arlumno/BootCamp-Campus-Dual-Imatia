package EjerciciosBasicosJava2;

public class Mercancia {
	private int id, cantidad;
	private String nombre, responsable, zona, area, estanteria;
	
	public Mercancia(int id, int cantidad, String nombre, String responsable, String zona, String area,String estanteria) {		
		this.id = id;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.responsable = responsable;
		this.zona = zona;
		this.area = area;
		this.estanteria = estanteria;
	}
	
	@Override
	public String toString() {
		return "Mercancia [id=" + id + ", cantidad=" + cantidad + ", nombre=" + nombre + ", responsable=" + responsable
				+ ", zona=" + zona + ", area=" + area + ", estanteria=" + estanteria + "]";
	}
	
}
