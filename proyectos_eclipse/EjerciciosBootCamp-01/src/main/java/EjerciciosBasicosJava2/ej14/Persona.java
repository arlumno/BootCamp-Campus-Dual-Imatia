package EjerciciosBasicosJava2.ej14;

public abstract class Persona {
	private String dni, nombre;
	private int altura, peso;
	public Persona(String dni, String nombre, int altura, int peso) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.altura = altura;
		this.peso = peso;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", altura=" + altura + ", peso=" + peso + ", getDni()="
				+ getDni() + ", getNombre()=" + getNombre() + ", getAltura()=" + getAltura() + ", getPeso()="
				+ getPeso() + "]";
	}
	
	
	
}
