package EjerciciosHilos.Ej44;

import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import EjerciciosHilos.Ej41.MartyMcFly;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		MartyMcFly mmf = new MartyMcFly();
		//https://www.baeldung.com/java-future
		ExecutorService ex = Executors.newSingleThreadExecutor();
		TimerTask task = new TimerTask() {
			Future<Integer> numeroAleatorio;
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				numeroAleatorio = (Future<Integer>) ThreadLocalRandom.current().nextInt(1,101);
				System.err.println(numeroAleatorio); 
				ex.submit(numeroAleatorio){;
			}
			
		};

		TimerTask task2 = new TimerTask() {
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(ThreadLocalRandom.current().nextInt(1,101)); 
			}
		};
		

		long finF1;
		long finF2;
		long inicio = System.currentTimeMillis();
		Future<?> f1 = ex.submit(task);
		Future<?> f2 = ex.submit(task2);
		f1.isDone();
		
		ex.shutdown();
		
		System.out.println(ex.awaitTermination(10, TimeUnit.SECONDS));

		fin = System.currentTimeMillis();
		System.out.println("Inicio: " + inicio);
		System.out.println("Fin: " + fin);
		System.out.println("Duracion: " + (fin - inicio));

	}
}
