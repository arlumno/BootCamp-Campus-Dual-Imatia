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

public class Ej60 {
	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 * Usa la función generate() para mostrar números aleatorios por pantalla cada X
		 * minisegundos (se duerme el hilo durante ese tiempo), con un límite de 25
		 * números
		 */
//https://www.geeksforgeeks.org/stream-generate-method-java/
		Stream.generate(() -> {
			int rdm = (int) (Math.random() * 2000);
			
			return rdm;
		}).limit(25).forEach(it-> {
			System.out.println("Pausando "+it+" ms.");
			try {
				Thread.sleep(it);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		
		});
	}
}
