import java.util.ArrayList;

public class Control {

	ArrayList<Carrera> listaCarreras = new ArrayList<Carrera>();
	ArrayList<Torneo> listaTorneos = new ArrayList<Torneo>();
	
	public Control() {
		// TODO Auto-generated constructor stub
	}
	
	public Control(ArrayList<Carrera> listaCarreras, ArrayList<Torneo> listaTorneos) {
		super();
		this.listaCarreras = listaCarreras;
		this.listaTorneos = listaTorneos;
	}

}
