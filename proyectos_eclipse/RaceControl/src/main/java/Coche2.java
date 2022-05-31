public class Coche2 {
	private final String BRAND;
	private final String MODEL;
	private String pegatinaGaraje = ""; 	
	public final int MAX_SPEED = 180;
	public final int GAP_ACCELERATION = 10;
	public final int MAX_TACHOMETER = 5000; 
	private int speedometer = 0;
	private boolean encendido = false;
	private Gears gear = Gears.N;
	private boolean reverse = false;
	private int habilidadPiloto; //% de probabilidades de acelerar
	public static enum Gears {
		N, D, R, P
	} 

	public Coche2(String bRAND, String mODEL, int habilidadPiloto) {		
		BRAND = bRAND;
		MODEL = mODEL;
		this.habilidadPiloto = habilidadPiloto;
	}
	
	public void preparar() {
		if(!isEncendido()) {
			start();		
		}
		if(getGear() != Gears.D) {
			setGear(Gears.D);		
		}
		speedometer = 0;
	}	
	
	public void conducir() {		
		if(((int)Math.random()*100+1) <= habilidadPiloto){
			accelerate();
		}else {
			brake();
		}
	}

	public String getPegatinaGaraje() {
		return pegatinaGaraje;
	}

	public boolean setPegatinaGaraje(String pegatinaGaraje) {
		boolean resultado = false;
		if(this.pegatinaGaraje.equals("")) {
			this.pegatinaGaraje = pegatinaGaraje;
			resultado = true;
		}
		return resultado;
	}

	public boolean isEncendido() {
		return encendido;
	}
	
	public boolean isReverse() {
		return reverse;
	}

	public boolean isParado() {
		return speedometer == 0;
	}
	
	public boolean start() {
		if (!isEncendido()) {
			encendido = true;
			return true;
		} else {
			ControlErrors.add(ControlErrors.ERROR_START_ENCENDIDO);
			return false;
		}
	}
	
	public boolean stop() {
		if (isParado()) {
			return true;
		} else {
			ControlErrors.add(ControlErrors.ERROR_STOP_NOT_PARADO);
			return false;
		}
	}

	public int getTachometer() {
		if (isEncendido()) {
			// proporción de velocidad/rpm con minimo de rpm
			return 1000 + speedometer * (MAX_TACHOMETER - 1000) / MAX_SPEED;
		} else {
			return 0;
		}

	}

	public boolean accelerate() {
		if (isEncendido()) {
			if (gear == Gears.D || gear == Gears.R) {
				if ((speedometer + GAP_ACCELERATION) > MAX_SPEED) {
					speedometer = MAX_SPEED;
				}else {
					speedometer += GAP_ACCELERATION;				
				}
				return true;
			} else {
				ControlErrors.add(ControlErrors.ERROR_ACCELERATE_WRONG_GEAR);
				return false;
			}
		} else {
			ControlErrors.add(ControlErrors.ERROR_ACCELERATE_STOPPED);
			return false;
		}
	}

	public boolean brake() {
		if (isEncendido()) {
			if (!isParado()) {				
				if ((speedometer - GAP_ACCELERATION) < 0) {
					speedometer = 0;
				}else {
					speedometer -= GAP_ACCELERATION;
				}
				return true;
			} else {
				ControlErrors.add(ControlErrors.ERROR_BRAKE_PARADO);
				return false;
			}
		} else {
			ControlErrors.add(ControlErrors.ERROR_BRAKE_STOPPED);
			return false;
		}
	}

	public boolean setGear(Gears gear) {
		switch (gear) {
			case N:
				this.gear = gear;
				break;
			case D:
				if(isReverse()) {
					if(isParado()) {
						this.gear = gear;
						reverse = false;
					}else {
						ControlErrors.add(ControlErrors.ERROR_GEAR_WRONG);
						return false;
					}					
				}else {
					this.gear = gear;
				}
				break;
			case R:
				if (isParado()) {
					this.gear = gear;
					reverse = true; //mantenemos este boleano por si pasamos por N 
					break;
				} else {
					ControlErrors.add(ControlErrors.ERROR_GEAR_WRONG);
					return false;
				}
			case P:
				if (isParado()) {
					this.gear = gear;
				} else {
					ControlErrors.add(ControlErrors.ERROR_GEAR_WRONG);
					return false;
				}
				break;
			default:
				ControlErrors.add(ControlErrors.ERROR_GEAR_NOT_EXIST);
				System.out.println("");
				break;
		}
		return true;
	}

	public String showDetails() {
		StringBuilder detalles = new StringBuilder();

		detalles.append("Detalles del vehículo:");
		detalles.append("\n\t Marca: " + getBRAND());
		detalles.append("\n\t Modelo: " + getMODEL());
		detalles.append("\n\t Velocidad Máxima: " + getMAX_SPEED());
		detalles.append("\n\t Velocidad actual: " + getSpeedometer());
		detalles.append("\n\t Revoluciones actuales: " + getTachometer());
		detalles.append("\n\t Marcha: " + getGear());
		detalles.append("\n\t Direccion: ");
		if (isReverse()) {
			detalles.append("marcha atrás");
		} else {
			detalles.append("marcha adelante");
		}

		return detalles.toString();
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

	public int getMAX_TACHOMETER() {
		return MAX_TACHOMETER;
	}


	public int getSpeedometer() {
		return speedometer;
	}


	public Gears getGear() {
		return gear;
	}

}