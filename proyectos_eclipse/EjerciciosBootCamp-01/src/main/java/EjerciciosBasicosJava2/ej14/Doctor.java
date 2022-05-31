package EjerciciosBasicosJava2.ej14;

public class Doctor extends Persona {
	private String especializacion, colegiado;

	public Doctor(String dni, String nombre, int altura, int peso, String especializacion, String colegiado) {
		super(dni, nombre, altura, peso);
		this.especializacion = especializacion;
		this.colegiado = colegiado;
	}

	@Override
	public String toString() {
		return "Doctor [especializacion=" + especializacion + ", colegiado=" + colegiado + ", getDni()=" + getDni()
				+ ", getNombre()=" + getNombre() + ", getAltura()=" + getAltura() + ", getPeso()=" + getPeso()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
