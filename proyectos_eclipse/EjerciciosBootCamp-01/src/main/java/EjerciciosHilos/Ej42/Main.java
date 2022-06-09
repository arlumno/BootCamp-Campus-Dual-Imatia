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
				if (contador.getAndIncrement() < 5) {
					System.out.println("\nxxxxxxxxxxxxxxxxxx  " + contador.get() + "  xxxxxxxxxxxxxxxxx");
					mmf.run(); // run en lugar de start() para que la tarea se pueda repetir
				} else {
					timer.cancel();
				}

			}
		};
		timer.schedule(task, 3000, 1000);

	}
}
