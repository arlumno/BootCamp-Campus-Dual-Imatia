package EjerciciosHilos.Ej40;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Coche implements Comparable, Runnable{
	private final String MARCA;
	private final String MODELO;
	private String pegatinaGaraje = "";
	public final int VELOCIDAD_MAXIMA = 180;
	public final int TRAMO_ACELERACION = 10;
	private transient int velocimetro = 0;
	private transient float cuentaKilometros = 0;
	private int habilidadPiloto; // % de probabilidades de acelerar

	/**
	 * 
	 * @param bRAND           Marca del coche
	 * @param mODEL           Modelo
	 * @param habilidadPiloto debe ser un numero entre 1 y 100
	 * @throws SuperPilotException
	 */
	public Coche(String bRAND, String mODEL, int habilidadPiloto)  {
		MARCA = bRAND;
		MODELO = mODEL;
			this.habilidadPiloto = habilidadPiloto;
		
	}

	/**
	 * 
	 * @param bRAND Marca del coche
	 * @param mODEL Modelo
	 */
	public Coche(String bRAND, String mODEL) {
		MARCA = bRAND;
		MODELO = mODEL;
		this.habilidadPiloto = (int) (Math.random() * 61 + 30);
	}

	/**
	 * Preparaa el coche inicializando los valores a 0a
	 */
	public void preparar() {
		velocimetro = 0;
		cuentaKilometros = 0;
	}

	/**
	 * Calcula una etapa de carrera (1 minuto), acelerando o frendando según la
	 * habilidad del piloto
	 */
	public void conducir() {
		int aleatorio = ((int) (Math.random() * 100 + 1));
		if (aleatorio <= habilidadPiloto) {
			accelerate();
		} else {
			brake();
		}
	}

	/**
	 * Asigna el nombre de un garaje al coche, solo si coche no tenia garaje
	 * asignado
	 * 
	 * @param pegatinaGaraje Nombre del garaje
	 * @return True si el coche está asignado al garaje, False si está asginado a
	 *         otro garaje distinto
	 */
	public boolean setPegatinaGaraje(String pegatinaGaraje) {
		boolean resultado = false;
		if (this.pegatinaGaraje.equals("")) {
			this.pegatinaGaraje = pegatinaGaraje;
			resultado = true;
		} else if (this.pegatinaGaraje.equals(pegatinaGaraje)) {
			resultado = true;
		}
		return resultado;
	}

	/**
	 * Obtiene el nombre del garaje en el que está asignado, o String "" si no está
	 * asignado.
	 * 
	 * @return String con nombre del garaje o vacio
	 */
	public String getPegatinaGaraje() {
		return pegatinaGaraje;
	}

	/**
	 * Aumenta la velocidad del velocimetro con un límite máximo, y actualiza la
	 * distancia reccorida
	 */
	public void accelerate() {
		if ((velocimetro + TRAMO_ACELERACION) > VELOCIDAD_MAXIMA) {
			velocimetro = VELOCIDAD_MAXIMA;
		} else {
			velocimetro += TRAMO_ACELERACION;
		}
		actualizarCuentaKilometros();
	}

	/**
	 * Reduce la velocidad del velocimetro con un límite mínimo de 0, y actualiza la
	 * distancia reccorida
	 */
	public void brake() {
		if ((velocimetro - TRAMO_ACELERACION) < 0) {
			velocimetro = 0;
		} else {
			velocimetro -= TRAMO_ACELERACION;
		}
		actualizarCuentaKilometros();
	}

	/**
	 * Actualiza la velocidad recorrida en 1 minuto
	 */
	private void actualizarCuentaKilometros() {
		cuentaKilometros += velocimetro / 60F;
	}

	public String getMARCA() {
		return MARCA;
	}

	public String getMODELO() {
		return MODELO;
	}

	public int getVELOCIDAD_MAXIMA() {
		return VELOCIDAD_MAXIMA;
	}

	public int getTRAMO_ACELERACION() {
		return TRAMO_ACELERACION;
	}

	public int getVelocimetro() {
		return velocimetro;
	}

	public float getCuentaKilometros() {
		return cuentaKilometros;
	}

	public void setCounterKm(int counterKm) {
		this.cuentaKilometros = counterKm;
	}

	@Override
	public int hashCode() {
		return MARCA.hashCode() * MODELO.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coche) {
			return ((Coche) obj).getMARCA().equals(MARCA) && ((Coche) obj).getMODELO().equals(MODELO);
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(Object o) {
		if (((Coche) o).getCuentaKilometros() > getCuentaKilometros()) {
			return 1;
		} else if (((Coche) o).getCuentaKilometros() == getCuentaKilometros()) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Coche [BRAND=" + MARCA + ", MODEL=" + MODELO + ", counterKm=" + cuentaKilometros + ", garaje="
				+ pegatinaGaraje + " habilidadPiloto=" + habilidadPiloto + "]\n";
	}

	@Override
	public void run() {
		for(int i = 0; i<15;i++) {
			conducir();
			System.out.println( ">" + MARCA + " - " + MODELO + ", Velocimetro= " + velocimetro+ "km/h , distancia= " + cuentaKilometros);
			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}