package EjerciciosHilos.Ej45;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import EjerciciosHilos.Ej41.MartyMcFly;
import EjerciciosHilos.Ej44.DeLorean;

public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		DeLorean dl = new DeLorean();
		long inicio = System.currentTimeMillis();
		long fin;
		Long duracionF1 = null;
		Long duracionF3 = null;
		// https://www.baeldung.com/java-future
		ExecutorService ex = dl.executor;
		ex = Executors.newFixedThreadPool(2);
				
		Future<Integer> f1 = ex.submit(() -> {
			Thread.sleep(2000); 
			System.out.println("---Anonima---");
			return ThreadLocalRandom.current().nextInt(1, 101);
		});
		Future<Integer> f3 = ex.submit(dl);
		
		while (!(f1.isDone() && f3.isDone())) {
			Thread.sleep(500);
			System.out.println("\n<<< EESTADO >>>");
			if (f1.isDone()) {
				System.out.println("[f1] FINALIZADO -> " + f1.get());
				if(duracionF1 == null) {
					duracionF1 = System.currentTimeMillis() - inicio;
				}
			} else {
				System.out.println("[f1] EN PROCESO");
			}


			if (f3.isDone()) {
				System.out.println("[f3] FINALIZADO -> " + f3.get());
				if(duracionF3 == null) {
					duracionF3 = System.currentTimeMillis() - inicio;
				}
			} else {
				System.out.println("[f3] EN PROCESO");
			}
		}

		System.out.println();
		System.out.println("xxxxxxxxxxxxxxxx");
		System.out.println();

		ex.shutdown();
		fin = System.currentTimeMillis();
		System.out.println("Inicio del PROGRAMA: " + inicio);
		System.out.println("Fin del PROGRAMA: " + fin);
		System.out.println("Duracion f1: " + duracionF1);
		System.out.println("Duracion f3: " + duracionF3);
		System.out.println("DURACION TOTAL: " + (fin - inicio));

	}
}
