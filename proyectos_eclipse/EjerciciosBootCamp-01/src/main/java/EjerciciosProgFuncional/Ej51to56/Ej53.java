package EjerciciosProgFuncional.Ej51to56;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ej53 {
	public static void main(String[] args) {
		/*
		 * Crea un Stream de nombre de personas en minúscula, conviértelo en nombres con
		 * solo la primera en minúscula , muéstralo por pantalla , luego haz a la
		 * inversa (primera mayúscula y las siguientes minúsculas), muéstralo por
		 * pantalla y luego mételo dentro de una lista.
		 */
		Stream<String> stream = Stream.of("ana", "amalia", "alicia", "aria", "asia");
		Function<String, String> capitalizar = i -> (i.toUpperCase().charAt(0) + i.substring(1).toLowerCase());
		Function<String, String> capitalizarNegativo = i -> (i.charAt(0) + i.substring(1).toUpperCase());

		List<String> resultado = stream.map(capitalizarNegativo).peek(System.out::println).map(capitalizar)
				.peek(System.out::println).collect(Collectors.toList());

		System.out.println(resultado);
	}
}
