package pojos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.management.monitor.Monitor;

import App.AppConfig;
import Excepciones.IncompleteException;

/*9
 * RaceControl con Threads: 
4. Implementar en "RaceControl" una carrera "Dragster", que no puede ser añadida a 
ningún torneo, y que los coches que participen en ella se implementen mediante hilos. 
5. PARA FUTURO: Utilizar programación funcional cuando se pueda 
6. PARA FUTURO: Tratar de gestionar algo de acceso limitado, como puede ser un PitStop, 
para el bloqueo de hilos
 */
public class CarreraDragster extends Carrera {
	public final static int DURACION_MINUTOS_DEFAULT = 180;

	// protected String tipo = "Eliminación";
	/**
	 * 
	 * @param nOMBRE           Nombre de la carrera
	 * @param garajes          Listado de garajes participantes
	 * @param dURACION_MINUTOS Duración de la carrera, por defecto 180
	 */
	public CarreraDragster(String nOMBRE, List<Garaje> garajes, int dURACION_MINUTOS) {
		super(nOMBRE, garajes, dURACION_MINUTOS);
		setTipo("Estandar");
	}

	public CarreraDragster(String nOMBRE, List<Garaje> garajes) {
		this(nOMBRE, garajes, DURACION_MINUTOS_DEFAULT);
	}

	public CarreraDragster(String nOMBRE, int dURACION_MINUTOS) {
		this(nOMBRE, new ArrayList<>(), DURACION_MINUTOS_DEFAULT);
	}

	public CarreraDragster(String nOMBRE) {
		this(nOMBRE, DURACION_MINUTOS_DEFAULT);
	}

	/**
	 * Inicia la carrera, y calcula la velocidad recorrida durante la duración de la
	 * carrera
	 * 
	 * @throws IncompleteException Si la carrera no está lista para ser iniciada
	 */
	public void iniciar() throws IncompleteException {
		// preparamos la carrera:
		preparar();
		try {
			// empieza la carrera.
			ExecutorService cochesHilos = Executors.newFixedThreadPool(cochesParticipantes.size());

			class Pista {
				private boolean enCarrera = true;

				public boolean isEnCarrera() {
					return enCarrera;
				}

				public void finCarrera() {
					this.enCarrera = false;
					notifyAll();

				}

				public void stop() {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				public void start() {
					notifyAll();
				}
			}

			Pista mosca = new Pista();
			for (Coche coche : cochesParticipantes) {
				cochesHilos.submit(() -> {
					synchronized (mosca) {
						mosca.stop();
						while (mosca.isEnCarrera()) {
							coche.conducir();
//							printStatus(coche);
							mosca.stop();
						}
					}

				});
			}
			AppConfig.get().setMultiplicadorUnidadTiempo(1000);
			cuentaAtras();
			for (int i = 0; i < DURACION_MINUTOS; i++) {
				Thread.sleep(AppConfig.get().getUnidadTiempoMs());
				flipadaStatus(cochesParticipantes,i+1);
				synchronized (mosca) {
					mosca.start();
				}

			}
			synchronized (mosca) {
				mosca.finCarrera();
			}

			cochesHilos.shutdown();
			if (cochesHilos.awaitTermination(3, TimeUnit.SECONDS)) {
				setCarreraFinalizada(true);
				calcularPodio();
			} else {
				System.err.println("Tiempo de carrera finalizado, quedan coches sin terminar.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	

//	public void iniciar() throws IncompleteException {
//		// preparamos la carrera:
//		preparar();
//		try {
//			// empieza la carrera.
//			ExecutorService pista = Executors.newFixedThreadPool(cochesParticipantes.size());
//			for (Coche coche : cochesParticipantes) {
//				pista.submit(() -> {
//					for (int i = 0; i < DURACION_MINUTOS; i++) {
//						try {
//							Thread.sleep(AppConfig.get().getUnidadTiempoMs());							
//							coche.conducir();
//							printStatus(coche);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				});
//			}
//			pista.shutdown();
//			if(pista.awaitTermination((long) (DURACION_MINUTOS_DEFAULT * AppConfig.get().getUnidadTiempoMs()*1.5), TimeUnit.MILLISECONDS)) {
//				setCarreraFinalizada(true);
//				calcularPodio();				
//			}else {
//				System.err.println("Tiempo de carrefa finalizado, quedan coches sin terminar.");
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}

	private void printStatus(Coche coche) {
		StringBuilder texto = new StringBuilder();
		texto.append(coche.getMARCA() + " - " + coche.getMODELO() + " - Distancia: " + coche.getCuentaKilometros()
				+ " (" + coche.getVelocimetro() + "km/h)");
		System.out.println(texto.toString());
	}

}
