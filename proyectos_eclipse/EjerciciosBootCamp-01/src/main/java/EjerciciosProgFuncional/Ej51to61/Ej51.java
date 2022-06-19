package EjerciciosProgFuncional.Ej51to61;

import java.util.stream.Stream;

public class Ej51 {
	public static void main(String[] args) {
		/*
		 * Crea un Stream de nombres de personas y muestra sus datos por consola (en 2 líneas de código)
		 */
		Stream<String> stream = Stream.of("Ana","Amalia","Alicia","Aria","Asia");
		stream.forEach(System.out::println);
	}
}
