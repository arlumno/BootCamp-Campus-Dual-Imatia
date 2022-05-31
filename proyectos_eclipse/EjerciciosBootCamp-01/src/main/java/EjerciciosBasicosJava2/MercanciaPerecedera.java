package EjerciciosBasicosJava2;

import java.time.LocalDate;

public class MercanciaPerecedera extends Mercancia{
	private LocalDate fechaCaducidad;

	public MercanciaPerecedera(int id, int cantidad, String nombre, String responsable, String zona, String area, String estanteria, LocalDate fechaCaducidad) {
		super(id, cantidad, nombre, responsable, zona, area, estanteria);
		this.fechaCaducidad = fechaCaducidad;
	}

	@Override
	public String toString() {
		return "MercanciaPerecedera [fechaCaducidad=" + fechaCaducidad + "]" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}
