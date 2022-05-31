package EjerciciosBasicosJava2.ej14;

public class Profesor extends Persona{
	private String rama;

	public Profesor(String dni, String nombre, int altura, int peso, String rama) {
		super(dni, nombre, altura, peso);
		this.rama = rama;
	}

	@Override
	public String toString() {
		return "Profesor [rama=" + rama + ", getDni()=" + getDni() + ", getNombre()=" + getNombre() + ", getAltura()="
				+ getAltura() + ", getPeso()=" + getPeso() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
}
