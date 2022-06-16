package EjerciciosProgFuncional.Ej51to56;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import java.util.stream.Stream;

public class Ej55 {
	public static void main(String[] args) {
		/*
		 * Crea un Stream de nombre de personas en mayúsculas, conviértelo en nombres
		 * con solo la primera en mayúscula (el resto en minúsculas), y usa las
		 * siguientes operaciones terminales (puedes inventarte tu la condición… que
		 * empiece por L, que acabe en “o”,…):  anyMatch  noneMatch  allMatch
		 */
		List<String> lista = Arrays.asList("PACO", "JUAN", "ALICIA", "ARIA", "JAIME", "SUSANA","  ");

		Function<String, String> capitalizar = it -> (it.toUpperCase().charAt(0) + it.substring(1).toLowerCase());
		boolean resultado;
		
		resultado = lista.stream().map(capitalizar).anyMatch(String::isBlank);
		System.out.println(resultado);
		
		resultado = lista.stream().map(capitalizar).noneMatch(it -> it.startsWith("R"));
		System.out.println(resultado);

		resultado = lista.stream().map(capitalizar).allMatch(it -> it.length()> 1);
		System.out.println(resultado);
	}
}
