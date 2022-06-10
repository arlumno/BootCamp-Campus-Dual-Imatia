package EjerciciosHilos.Ej42;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import EjerciciosHilos.Ej41.MartyMcFly;

public class Main {

	public static void main(String[] args) {
		MartyMcFly mmf = new MartyMcFly();
		Timer timer = new Timer();
		AtomicInteger contador = new AtomicInteger();

		TimerTask task = new TimerTask() {
			public void run() {
				int i;
				if ((i = contador.getAndIncrement()) < 5) {
					System.out.println("\nxxxxxxxxxxxxxxxxxx  " + i + "  xxxxxxxxxxxxxxxxx");
//					mmf.run(); // run en lugar de start() para que la tarea se pueda repetir
					System.out.println(mmf.frase());
				} else {
					timer.cancel();
				}

			}
		};
		timer.schedule(task, 3000, 1000);

	}
}
