package EjerciciosHilos.Ej43;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import EjerciciosHilos.Ej41.MartyMcFly;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		MartyMcFly mmf = new MartyMcFly();
		TimerTask task = new TimerTask() {
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				mmf.run(); // run en lugar de start() para que la tarea se pueda repetir
			}
		};

		ExecutorService ex = Executors.newSingleThreadExecutor();

		long fin;
		long inicio = System.currentTimeMillis();

		ex.submit(task);
		ex.shutdown();
		ex.awaitTermination(10, TimeUnit.SECONDS);

		fin = System.currentTimeMillis();
		System.out.println("Inicio: " + inicio);
		System.out.println("Fin: " + fin);
		System.out.println("Duracion: " + (fin - inicio));

	}
}
