package EjerciciosHilos.Ej38;

import EjerciciosHilos.Ej36_37.ClaseRunnable;
import EjerciciosHilos.Ej36_37.ClaseThread;

public class Main {

	public static void main(String[] args) throws InterruptedException {

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
		
		cr1.join();
		cr2.join();
		cr3.join();
		ct1.join();
		ct2.join();
		ct3.join();

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
		System.err.println(">>> [" + thread.getName() + "]" + thread.getState());
	}

}
