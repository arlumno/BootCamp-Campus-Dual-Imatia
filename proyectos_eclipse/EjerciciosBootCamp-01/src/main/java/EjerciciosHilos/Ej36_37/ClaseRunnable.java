package EjerciciosHilos.Ej36_37;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ClaseRunnable implements Runnable {
	String nombre;
	int rondas;

	public ClaseRunnable(int rondas) {
		this.rondas = rondas;
	}

	@Override
	public void run() {
		for (int i = 0; i < rondas; i++) {
			System.out.println(Thread.currentThread().getName());	
			try {
				TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(300, 2500));
//				TimeUnit.SECONDS.sleep((int) (Math.random()*4));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
