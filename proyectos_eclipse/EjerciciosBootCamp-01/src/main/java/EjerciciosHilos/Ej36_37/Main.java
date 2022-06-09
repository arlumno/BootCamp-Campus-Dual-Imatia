package EjerciciosHilos.Ej36_37;

public class Main {

	public static void main(String[] args) {

		Thread cr1 = new Thread(new ClaseRunnable(5));
		Thread cr2 = new Thread(new ClaseRunnable(5));
		Thread cr3 = new Thread(new ClaseRunnable(5));
		ClaseThread ct1 = new ClaseThread(5);
		ClaseThread ct2 = new ClaseThread(5);
		ClaseThread ct3 = new ClaseThread(5);
		cr1.start();
		cr2.start();
		cr3.start();
		ct1.start();
		ct2.start();
		ct3.start();
		while (cr1.isAlive() || cr2.isAlive() || cr3.isAlive() || ct1.isAlive() || ct2.isAlive() || ct3.isAlive()) {
			estado(cr1);
			estado(cr2);
			estado(cr3);
			estado(ct1);
			estado(ct2);
			estado(ct3);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		System.out.println("Fin del programa");
		estado(cr1);
		estado(cr2);
		estado(cr3);
		estado(ct1);
		estado(ct2);
		estado(ct3);
	}

	protected static void estado(Thread thread) {
		System.err.println(">>> [" + thread.getName() + "]" + thread.getState() );
	}


}
