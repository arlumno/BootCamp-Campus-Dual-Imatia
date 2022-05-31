package EjerciciosBasicosJava2.ej14;

public class Policia extends Persona {
	private String rango, nPlaca;

	public Policia(String dni, String nombre, int altura, int peso, String rango, String nPlaca) {
		super(dni, nombre, altura, peso);
		this.rango = rango;
		this.nPlaca = nPlaca;
	}

	@Override
	public String toString() {
		return "Policia [rango=" + rango + ", nPlaca=" + nPlaca + ", getDni()=" + getDni() + ", getNombre()="
				+ getNombre() + ", getAltura()=" + getAltura() + ", getPeso()=" + getPeso() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
