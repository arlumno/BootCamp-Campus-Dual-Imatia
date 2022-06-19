package EjerciciosProgFuncional.Ej51to61;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ej52 {
	public static void main(String[] args) {
		/*
		 * Crea un Stream de nombre de personas en mayúsculas, conviértelo en nombres
		 * con solo la primera en mayúscula (el resto en minúsculas), muéstralo por
		 * pantalla y luego mételo dentro de una lista.
		 */
		Stream<String> stream = Stream.of("ANA", "AMALIA", "ALICIA", "ARIA", "ASIA");
		Function<String, String> capitalizar = i -> (i.charAt(0) + i.substring(1).toLowerCase());

		List<String> resultado = stream.map(capitalizar).peek(System.out::println).collect(Collectors.toList());

		System.out.println(resultado);
	}
}
