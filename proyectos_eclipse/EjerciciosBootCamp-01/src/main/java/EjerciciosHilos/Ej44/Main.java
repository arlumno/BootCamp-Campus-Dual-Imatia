package EjerciciosHilos.Ej44;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;


public class Main {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		DeLorean dl = new DeLorean();
		long inicio;
		long fin;		
		// https://www.baeldung.com/java-future
		ExecutorService ex = Executors.newSingleThreadExecutor();

		/** ejemplo 1 **/
		inicio = System.currentTimeMillis();
		long primerInicio = inicio; 
		Future<Integer> f1 = ex.submit(() -> {
			Thread.sleep(2000);
			System.out.println("---Anonima---");
			return ThreadLocalRandom.current().nextInt(1, 101);

		});
		
		System.out.println("Finalizada: " + f1.isDone() );
		ex.shutdown();
		ex.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Finalizada: " + f1.isDone() );
		fin = System.currentTimeMillis();
		System.out.println("Inicio: " + inicio);
		System.out.println("Fin: " + fin);
		System.out.println("Duracion: " + (fin - inicio));
		System.out.println("Duracion desde inicio programa: " + (fin - primerInicio));
		System.err.println("RESULTADO : " + f1.get());

		System.out.println();
		System.out.println("xxxxxxxxxxxxxxxx");
		System.out.println();
		
		/** ejemplo 2 **/
		inicio = System.currentTimeMillis();
		Future<Integer> f2 = dl.destino();
		
		System.out.println("Finalizada: " + f2.isDone() );
		dl.executor.shutdown();
		dl.executor.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Finalizada: " + f2.isDone() );
		fin = System.currentTimeMillis();
		System.out.println("Inicio: " + inicio);
		System.out.println("Fin: " + fin);
		System.out.println("Duracion: " + (fin - inicio));
		System.out.println("Duracion desde inicio programa: " + (fin - primerInicio));
		System.err.println("RESULTADO : " + f2.get());
		

		System.out.println();
		System.out.println("xxxxxxxxxxxxxxxx");
		System.out.println();
		
		/** ejemplo 3 **/
		ex = Executors.newSingleThreadExecutor();
		inicio = System.currentTimeMillis();
		Future<Integer> f3 = ex.submit(dl);
			
		System.out.println("Finalizada: " + f3.isDone() );
		ex.shutdown();
		ex.awaitTermination(10, TimeUnit.SECONDS);
		System.out.println("Finalizada: " + f3.isDone() );
		fin = System.currentTimeMillis();
		System.out.println("Inicio: " + inicio);
		System.out.println("Fin: " + fin);
		System.out.println("Duracion: " + (fin - inicio));
		System.out.println("Duracion desde inicio programa: " + (fin - primerInicio));
		System.err.println("RESULTADO : " + f3.get());

		
		System.out.println();
		System.out.println("xxxxxxxxxxxxxxxx");
		System.out.println();
		
		fin = System.currentTimeMillis();
		System.out.println("Inicio del PROGRAMA: " + primerInicio);
		System.out.println("Fin: " + fin);
		System.err.println("DURACION TOTAL: " + (fin - primerInicio));
		
	}
}
