package EjerciciosBasicosJava2;


import javax.accessibility.AccessibleState;

public class ControlRemoto {
	int canal = 1;
	byte volumen = 10;
	boolean encendido;

	public ControlRemoto() {
	}

	public void aumenarCanal() {
		canal++;
	}

	public void disminuirCanal() {
		canal--;
	}

	public void subirVolumen() {
		volumen++;
	}

	public void bajarVolumen() {
		volumen--;
	}

	public void cambiarEstado() {
		if (encendido) {
			encendido = false;
		} else {
			encendido = true;
		}
	}

	@Override
	public String toString() {
		String estado = "apagado";
		if(encendido) {
			estado = "encendido";
		}
		return "Estado:" + estado + " - Volumen: " + volumen + " - Canal: " + canal; 
	}
}
