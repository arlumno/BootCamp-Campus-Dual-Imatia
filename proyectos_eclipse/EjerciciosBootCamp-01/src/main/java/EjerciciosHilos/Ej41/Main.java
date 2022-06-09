package EjerciciosHilos.Ej41;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

	public static void main(String[] args) {
		MartyMcFly mmf = new MartyMcFly();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				mmf.start();
				timer.cancel();
			}
		};
		timer.schedule(task, 2000);
		
	}

}
