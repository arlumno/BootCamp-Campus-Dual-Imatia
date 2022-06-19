package EjerciciosProgFuncional.Ej51to61;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ej61 {
	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 *  Usa la función generate() para mostrar números aleatorios por pantalla cada
		 * X minisegundos (muestra este tiempo también por pantalla), con un límite de
		 * 25 números.  Haz lo mismo que el punto anterior pero usando la función
		 * parallel().
		 */
//https://www.geeksforgeeks.org/stream-generate-method-java/
		Stream.generate(() -> {
			int rdm = (int) (Math.random() * 2000);

			return rdm;
		}).limit(25).parallel().forEach(it -> {
			System.out.println("Pausando " + it + " ms.");
			try {
				Thread.sleep(it);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Fin Pausa " + it + " ms.");

		});
	}
}
