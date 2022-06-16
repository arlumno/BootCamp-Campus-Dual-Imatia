package EjerciciosProgFuncional.Ej47;

public class PersonaJava8 {
	private String nombre, apellidos;

	public PersonaJava8(String nombre, String apellidos) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Override
	public String toString() {
		return "PersonaJava8 [nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
}
