package EjerciciosProgFuncional.Ej51to61;

import java.util.function.Function;

import java.util.stream.Stream;


public class Ej54 {
	public static void main(String[] args) {
		/*
		 * Crea un Stream de nombre de personas en mayúsculas, conviértelo en nombres
		 * con solo la primera en mayúscula (el resto en minúsculas), y muestra por
		 * pantalla, sólo los nombres que empiecen por J.
		 */
		Stream<String> stream = Stream.of("PACO", "JUAN", "ALICIA", "ARIA", "JAIME", "SUSANA");

		Function<String,String> capitalizar= it -> (it.toUpperCase().charAt(0) + it.substring(1).toLowerCase());
		
		stream.map(capitalizar).filter(it-> it.startsWith("J")).forEach(System.out::println);
		

	}
}
