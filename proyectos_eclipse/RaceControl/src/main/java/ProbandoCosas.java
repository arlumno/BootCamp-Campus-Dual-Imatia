import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ProbandoCosas {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// String resultado = gson.toJson(loader);
		// System.out.println(resultado);

		Pruebas p = new Pruebas();

		p.add(new Prueba("cero"));
		p.add(p.get(0));
		p.add(new Prueba("dos"));

		System.out.println(p);
		System.out.println("***");

		p.get(0).setTexto("0");		
		p.get(1).setTexto("1");
		p.get(2).setTexto("2");

		System.out.println(p);
		System.out.println("***");

		String resultadoJson = gson.toJson(p);
		System.out.println(resultadoJson);

		Pruebas pFromJson = gson.fromJson(resultadoJson, Pruebas.class);

		System.out.println("***");
		System.out.println(p);
		System.out.println("***");

		p.get(0).setTexto("00");
		p.get(1).setTexto("11");
		p.get(2).setTexto("22");

		System.out.println(p);
		System.out.println("***");

	}

}

class Pruebas{
	ArrayList<Prueba> p = new ArrayList<Prueba>();
	public void add(Prueba prueba) {
		p.add(prueba);
	}
	public Prueba get(int index) {
		return p.get(index);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return p.toString();
	}
}
class Prueba {
	private String texto;
	private int id = 0;	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Prueba(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
	public String toString() {
		return texto;
	}	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id;
	}
	@Override
	public boolean equals(Object obj) {
		return true;
//		if(obj instanceof Prueba) {
//			return ((Prueba) obj).getId() == id;
//		}else {
//			return super.equals(obj);		
//		}
	}
}