package EjerciciosHilos.Ej44;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class DeLorean implements Callable<Integer>{
	Future<Integer> destino;
	public ExecutorService executor = Executors.newSingleThreadExecutor();

	public Future<Integer> destino() throws InterruptedException{
		Thread.sleep(3000);
		System.out.println("---DeLorean---");
		return executor.submit(()-> {return ThreadLocalRandom.current().nextInt(101,5001);});
	}
//	public <T> destino2(){
//		return ThreadLocalRandom.current().nextInt(1,101);
//	}
//	public Future<Integer> destin3(){
//		return executor.submit(()-> {return destino2());
//	}
//	public Future<Integer> destino4(){
//		return (Future<Integer>) ThreadLocalRandom.current().nextInt(1,101);
//	}

	@Override
	public Integer call() throws Exception {
		Thread.sleep(2000);
		System.out.println("---DeLorean CALLABLE---");
		return  ThreadLocalRandom.current().nextInt(50001,2000001);
	}
	
}
