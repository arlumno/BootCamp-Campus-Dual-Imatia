package EjerciciosProgFuncional.Ej51to61;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;
import java.util.function.Function;

import java.util.stream.Stream;

public class Ej56 {
	public static void main(String[] args) {
		/*
		 *  Crea un Stream de nombre de personas en minúscula, usa la operación
		 * distinct() y muéstralo por pantalla el nombre con la primera en mayúsculas 
		 * Añádele la operación reduce()
		 */

		List<String> lista = Arrays.asList("ana", "amalia", "alicia","ana", "aria", "asia","ana");

		Function<String, String> capitalizar = it -> (it.toUpperCase().charAt(0) + it.substring(1).toLowerCase());
		BinaryOperator<String>formatear= (acum,cadena)-> acum+cadena+",";
		
		//https://www.arquitecturajava.com/java-stream-reduce-eliminando-bucles/
		String resultado = lista.stream().distinct().map(capitalizar).reduce("",formatear);
		System.out.println(resultado);

	}
}
