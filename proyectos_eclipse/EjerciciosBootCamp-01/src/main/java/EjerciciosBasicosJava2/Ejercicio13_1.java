package EjerciciosBasicosJava2;

public class Ejercicio13_1 {
	private int actualFuel = 10;

	public void showDetails() {
		System.out.println("La capacidad actual es de " + this.actualFuel + " litros.");
	}

	public static void main(String[] args) {
		Ejercicio13_1 cO = new Ejercicio13_1();
		cO.showDetails();
		System.out.println("Actualización capacidad");
		cO.setActualFuel(-8);
		cO.showDetails();	
	}

	private void setActualFuel(int i) {
		if(i >= 0) {
			actualFuel = i;
		}
		
	}
}
