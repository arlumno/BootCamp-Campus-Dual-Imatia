package EjerciciosHilos.Ej36_37;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ClaseThread extends Thread {
	int rondas;

	public ClaseThread(int rondas) {
		this.rondas = rondas;
	}

	@Override
	public void run() {
		for (int i = 0; i < rondas; i++) {
			System.out.println(getName());	
			try {
				TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(500, 2000));
//				TimeUnit.SECONDS.sleep((int) (Math.random()*4));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
