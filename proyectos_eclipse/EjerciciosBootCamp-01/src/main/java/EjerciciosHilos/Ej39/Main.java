package EjerciciosHilos.Ej39;

public class Main {

	public static void main(String[] args) {

		Thread c1 = new Thread(new Coche("Seat","Panda"));
		Thread c2 = new Thread(new Coche("Seat","Ibiza"));
		Thread c3 = new Thread(new Coche("Ford","Fiesta"));
		
		c1.start();
		c2.start();
		c3.start();
		try {
			c1.join();
			c2.join();
			c3.join();		
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Fin del programa");
		estado(c1);
		estado(c2);
		estado(c3);
	}

	protected static void estado(Thread thread) {
		System.err.println(">>> [" + thread.getName() + "]" + thread.getState() );
	}


}
