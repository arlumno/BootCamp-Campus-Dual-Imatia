
import Excepciones.SuperPilotException;

public class Coche implements Comparable{
	private final String BRAND;
	private final String MODEL;
	private String pegatinaGaraje = "";
	public final int MAX_SPEED = 180;
	public final int GAP_ACCELERATION = 10;
	private int speedometer = 0;
	private float counterKm = 0;
	private int habilidadPiloto; // % de probabilidades de acelerar

	/**
	 * 
	 * @param bRAND
	 * @param mODEL
	 * @param habilidadPiloto debe ser un numero entre 1 y 100
	 * @throws SuperPilotException 
	 */
	public Coche(String bRAND, String mODEL, int habilidadPiloto) throws SuperPilotException {
		BRAND = bRAND;
		MODEL = mODEL;
		if(habilidadPiloto > 0 && habilidadPiloto <101 ) {
			this.habilidadPiloto = habilidadPiloto;		
		}else{
			throw new SuperPilotException(Integer.toString(habilidadPiloto));
		}
	}
	
	public Coche(String bRAND, String mODEL) throws SuperPilotException {
		this(bRAND, mODEL, (int) Math.random()*61+30); //piloto con habilidad aleatoria entre 30 y 90%		
	}
	
	public void preparar() {
		speedometer = 0;
		counterKm = 0;
	}

	public void conducir() {
		int aleatorio = ((int) (Math.random() * 100 + 1));
		if (aleatorio <= habilidadPiloto) {
			accelerate();
		} else {
			brake();
		}
	}


	public boolean setPegatinaGaraje(String pegatinaGaraje) {
		boolean resultado = false;
		if (this.pegatinaGaraje.equals("")) {
			this.pegatinaGaraje = pegatinaGaraje;
			resultado = true;
		}else if(this.pegatinaGaraje.equals(pegatinaGaraje)) {
			resultado = true;
		}
		return resultado;
	}
	
	public String getPegatinaGaraje() {
		return pegatinaGaraje;
	}

	public void accelerate() {
		if ((speedometer + GAP_ACCELERATION) > MAX_SPEED) {
			speedometer = MAX_SPEED;
		} else {
			speedometer += GAP_ACCELERATION;
		}		
		updateCounterKm();
	}
		
	public void brake() {
		if ((speedometer - GAP_ACCELERATION) < 0) {
			speedometer = 0;
		} else {
			speedometer -= GAP_ACCELERATION;
		}
		updateCounterKm();
	}

	private void updateCounterKm() {
		counterKm += speedometer / 60F;
	}
	
	public String getBRAND() {
		return BRAND;
	}

	public String getMODEL() {
		return MODEL;
	}

	public int getMAX_SPEED() {
		return MAX_SPEED;
	}

	public int getGAP_ACCELERATION() {
		return GAP_ACCELERATION;
	}
	public int getSpeedometer() {
		return speedometer;
	}

	public float getCounterKm() {
		return counterKm;
	}

	public void setCounterKm(int counterKm) {
		this.counterKm = counterKm;
	}

	@Override
	public int hashCode() {
		return  BRAND.hashCode() * MODEL.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Coche) {
			return ((Coche) obj).getBRAND().equals(BRAND) && ((Coche) obj).getMODEL().equals(MODEL);
		}
		return super.equals(obj);
	}

	@Override
	public int compareTo(Object o) {		
		if(((Coche) o).getCounterKm() > getCounterKm()){
			return 1;
		}else if(((Coche) o).getCounterKm() == getCounterKm()){
			return 0;
		}else {
			return -1;
		}		
	}

	@Override
	public String toString() {
		return "Coche [BRAND=" + BRAND + ", MODEL=" + MODEL + ", counterKm=" + counterKm + ", garaje="+ pegatinaGaraje+" habilidadPiloto="
				+ habilidadPiloto + "]\n";
	}
	
	
	
}