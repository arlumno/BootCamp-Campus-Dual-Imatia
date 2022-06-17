package EjerciciosProgFuncional.Ej51to56;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
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
		Stream<Integer> streamInts = Stream.of(1,2,3,4,5,6);
		int resultado = streamInts.reduce(0,Integer::sum);
		imprimir.accept("Total: "+resultado);
		
		IntStream streamInts2 = IntStream.range(1,51);
		//listadoInts2.forEach(System.out::println);
		//IntStream listadoRandoms = listadoInts2.map(it -> (int) (Math.random()*51+it)).forEach(System.out::println);
//		List<Integer> listaRandoms = streamInts2.mapToInt(it -> (int) (Math.random()*51+it));
		IntStream streamRandoms = streamInts2.map(it -> (int) (Math.random()*51+it)).peek(System.out::println);
//		List<Integer> listaRandoms = streamInts2.mapToInt(it -> (int) (Math.random()*51+it));
		
				

//				
		imprimir.accept("Mínimo: "+streamRandoms.min().getAsInt());
		//imprimir.accept("Máximo: "+streamRandoms.max().getAsInt());
		
	}
}
