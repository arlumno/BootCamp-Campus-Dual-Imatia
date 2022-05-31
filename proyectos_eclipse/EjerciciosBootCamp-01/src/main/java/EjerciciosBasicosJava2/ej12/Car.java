package EjerciciosBasicosJava2.ej12;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class Car {
	private final String BRAND;
	private final String MODEL;
	private final FuelTypes FUEL_TYPE;
	private final int MAX_SPEED;
	private final int MIN_WHEEL_ANGLE; // extra
	private final int MAX_WHEEL_ANGLE; // extra
	private final int GAP_ACCELERATION; // extra
	private final int MAX_TACHOMETER; // extra
//	private final CarErrors ERRORES= new CarErrors();
	private final LinkedList<String> ERRORS_HISTORY = new LinkedList<String>();
	private int speedometer = 0;
	private boolean encendido;
	private Gears gear = Gears.N;
	private int steeringWheelAngle = 0;

	private boolean reverse = false;
	// private int tachometer = 0;

	public static enum Gears {
		N, D, R, P
	} // extra, Neutro, Drive, Reverse, Parking

	public static enum FuelTypes {
		GASOLINA, DIESEL, HIBRIDO, ELECTRICO
	}

	public Car(String bRAND, String mODEL, int mAX_SPEED, FuelTypes fUEL_TYPE, int mIN_WHEEL_ANGLE, int mAX_WHEEL_ANGLE,
			int gAP_ACCELERATION, int mAX_TACHOMETER) {
		BRAND = bRAND;
		MODEL = mODEL;
		FUEL_TYPE = fUEL_TYPE;
		MAX_SPEED = mAX_SPEED;
		MIN_WHEEL_ANGLE = mIN_WHEEL_ANGLE;
		MAX_WHEEL_ANGLE = mAX_WHEEL_ANGLE;
		GAP_ACCELERATION = gAP_ACCELERATION;
		MAX_TACHOMETER = mAX_TACHOMETER;
	}

	public Car(String bRAND, String mODEL, int mAX_SPEED, FuelTypes fUEL_TYPE) {
		BRAND = bRAND;
		MODEL = mODEL;
		FUEL_TYPE = fUEL_TYPE;
		MAX_SPEED = mAX_SPEED;
		MAX_WHEEL_ANGLE = 400;
		MIN_WHEEL_ANGLE = -1 * MAX_WHEEL_ANGLE;
		GAP_ACCELERATION = 10;
		MAX_TACHOMETER = 5000;
	}

	public Car() {
		this("Opel", "Corsa", 120, FuelTypes.GASOLINA);
//		this.BRAND = "Opel";
//		this.MODEL = "Corsa";
//		this.MAX_SPEED = 120;
//		this.FUEL_TYPE = "Gasolina";
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
			ERRORS_HISTORY.add(CarErrors.ERROR_START_ENCENDIDO);
			return false;
		}
	}

	
	public boolean stop() {
		if (isParado()) {
			return true;
		} else {
			ERRORS_HISTORY.add(CarErrors.ERROR_STOP_NOT_PARADO);
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
				ERRORS_HISTORY.add(CarErrors.ERROR_ACCELERATE_WRONG_GEAR);
				return false;
			}
		} else {
			ERRORS_HISTORY.add(CarErrors.ERROR_ACCELERATE_STOPPED);
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
				ERRORS_HISTORY.add(CarErrors.ERROR_BRAKE_PARADO);
				return false;
			}
		} else {
			ERRORS_HISTORY.add(CarErrors.ERROR_BRAKE_STOPPED);
			return false;
		}
	}

	public void turnSteeringWheel(int angle) {
		if (angle > 0) {
			if ((steeringWheelAngle + angle) > MAX_WHEEL_ANGLE) {
				steeringWheelAngle = MAX_WHEEL_ANGLE;
			} else {
				steeringWheelAngle += angle;
			}
		} else if (angle < 0) {
			if ((steeringWheelAngle + angle) < MIN_WHEEL_ANGLE) {
				steeringWheelAngle = MIN_WHEEL_ANGLE;
			} else {
				steeringWheelAngle += angle;
			}
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
						ERRORS_HISTORY.add(CarErrors.ERROR_GEAR_WRONG);
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
					ERRORS_HISTORY.add(CarErrors.ERROR_GEAR_WRONG);
					return false;
				}
			case P:
				if (isParado()) {
					this.gear = gear;
				} else {
					ERRORS_HISTORY.add(CarErrors.ERROR_GEAR_WRONG);
					return false;
				}
				break;
			default:
				ERRORS_HISTORY.add(CarErrors.ERROR_GEAR_NOT_EXIST);
				System.out.println("");
				break;
		}
		return true;
	}

	public String showSteeringWheelDetail() { // mostrar el angulo de las ruedas
		StringBuilder resultado = new StringBuilder();
		if (steeringWheelAngle == 0) {
			resultado.append("El volante está de frente");
		} else {		
			int gradosAbsoluto = Math.abs(steeringWheelAngle);			
			int ratioGiro = MAX_WHEEL_ANGLE / gradosAbsoluto; // cuanto mas alto menos giro, 1 máximo giro
			resultado.append("El volante gira ");
			
			if (ratioGiro > 10) {
				resultado.append("levemente ");
			}else if (ratioGiro > 4) {
				resultado.append("");
			}else if(ratioGiro > 1) {
				resultado.append("pronunciadamente ");
			}else{
				resultado.append("totalmente ");
			}	
				
			if(steeringWheelAngle > 0) {
				resultado.append("a la derecha");
			}else {
				resultado.append("a la izquierda");
			}			
		}
		return resultado.toString();
	}


//	private void setReverse(boolean reverse) {
//		this.reverse = reverse;
//
//	}

	public String showDetails() {
		StringBuilder detalles = new StringBuilder();

		detalles.append("Detalles del vehículo:");
		detalles.append("\n\t Marca: " + getBRAND());
		detalles.append("\n\t Modelo: " + getMODEL());
		detalles.append("\n\t Velocidad Máxima: " + getMAX_SPEED());
		detalles.append("\n\t Tipo Gasolina: " + getFUEL_TYPE());
		detalles.append("\n\t Velocidad actual: " + getSpeedometer());
		detalles.append("\n\t Revoluciones actuales: " + getTachometer());
		detalles.append("\n\t Marcha: " + getGear());
		detalles.append("\n\t Direccion: ");
		if (isReverse()) {
			detalles.append("marcha atrás");
		} else {
			detalles.append("marcha adelante");
		}
		detalles.append("\n\t Dirección volante: " + showSteeringWheelDetail());

		return detalles.toString();
	}

	public String getLastError() {
		return ERRORS_HISTORY.getLast();
	}
	
	public Collection<String> getErrors() {
		return ERRORS_HISTORY;
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

	public int getMIN_WHEEL_ANGLE() {
		return MIN_WHEEL_ANGLE;
	}

	public int getMAX_WHEEL_ANGLE() {
		return MAX_WHEEL_ANGLE;
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

	public int getSteeringWheelAngle() {
		return steeringWheelAngle;
	}

	public FuelTypes getFUEL_TYPE() {
		return FUEL_TYPE;
	}

	public Gears getGear() {
		return gear;
	}
	
	
//	public static void main(String[] args) {
//
//		Car myCar = new Car();
//		String option = "";
//		System.out.println();
//		myCar.showDetails();
//
//	}

}