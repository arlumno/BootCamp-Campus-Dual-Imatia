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

public class Ej59 {
	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 * Con un Stream de nombres y usando la operación flatMap devuelve el nombre en
		 * mayúsculas si el nombre empieza por A y devuelve el nombre en minúsculas si
		 * empieza por T (por ejemplo, lo puedes adaptar a tu ejemplo)
		 */
		// https://www.delftstack.com/es/howto/java/flatmap-in-java/
		Stream<String> stream = Stream.of("Paco", "Juan", "Alberto", "Alicia", "Jaime", "Susana");
		Function<String, Stream<String>> funcion = (it) -> {
			List<String> lista = new ArrayList<>();
			lista.add(it);
			switch (it.charAt(0)) {
			case 'J':
				lista.add(it.toUpperCase());
				break;
			case 'P':
				lista.add(it.toLowerCase());
				break;
			}
			return lista.stream();
		};
		stream.flatMap(funcion).forEach(System.out::println);
	}
}
