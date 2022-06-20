package EjerciciosProgFuncional.Ej51to61;

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

public class Ej58 {
	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 *  Crea un Stream de nombres y obtén un IntStream con la longitud de cada uno
		 * de ellos  Crea un IntSummaryStatistics para obtener las estadística (máximo
		 * valor, mínimo, sumatorio, contar, media,….)
		 */
		
		Stream<String> streamStrings = Stream.of("ana", "amalia", "alicia", "aria", "asia");
		IntStream streamLongitudes = streamStrings.mapToInt(String::length);
		
		IntSummaryStatistics estadisticas = streamLongitudes.summaryStatistics();
		imprimir.accept("Max: " + estadisticas.getMax());
		imprimir.accept("Min: " + estadisticas.getMin());
		imprimir.accept("Sum: " + estadisticas.getSum());
		imprimir.accept("Count: " + estadisticas.getCount());
		imprimir.accept("Average: " + estadisticas.getAverage());
		
	}
}
