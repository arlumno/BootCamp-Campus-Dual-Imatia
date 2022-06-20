package EjerciciosProgFuncional.Ej51to61;

import java.util.IntSummaryStatistics;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ej57 {
	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 * Crea un Stream de int y añádele una operación que sume sus valores  Crea un
		 * IntStream con números entre el 1 y el 50  A partir de IntStream anterior,
		 * crea un IntStream con número aleatorios entre el valor y el valor + 50.  Con
		 * el IntStream resultado, muestra por pantalla algunas de sus estadísticas
		 * (máximo valor, mínimo, sumatorio, contar, media,….)
		 */
		
		Stream<Integer> streamInts = Stream.of(1,2,30,14,5,96);
		int resultado = streamInts.reduce(0,Integer::sum);
		imprimir.accept("Total: "+resultado);
		
		IntStream streamInts2 = IntStream.range(1,51);
		IntStream streamRandoms = streamInts2.map(it -> (int) (Math.random()*51+it)).peek(System.out::println);
		
//		imprimir.accept("Mínimo: "+streamRandoms.min().getAsInt());
		
		IntSummaryStatistics estadisticas = streamRandoms.summaryStatistics();
		imprimir.accept("Max: " + estadisticas.getMax());
		imprimir.accept("Min: " + estadisticas.getMin());
		imprimir.accept("Sum: " + estadisticas.getSum());
		imprimir.accept("Count: " + estadisticas.getCount());
		imprimir.accept("Average: " + estadisticas.getAverage());	}
}
