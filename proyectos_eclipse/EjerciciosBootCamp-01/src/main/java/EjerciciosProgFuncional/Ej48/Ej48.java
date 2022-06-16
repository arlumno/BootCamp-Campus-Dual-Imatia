package EjerciciosProgFuncional.Ej48;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class Ej48 {

	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 * Crea una expresión Function que devuelva un valor aleatorio entre 1 y un
		 * valor introducido por parámetro. Crea una expresión Function que reciba una
		 * cadena y la devuelva convertida en mayúsculas. Crea una expresión BiFunction
		 * que reciba 2 números, que compare cual es mayor y devuelva un número al azar
		 * entre ambos.
		 */

		Function<Integer, Integer> aleatorio = (i) -> (int) (Math.random() * i + 1);
		imprimir.accept(aleatorio.apply(33).toString());

		Function<String, String> upUp = String::toUpperCase;
		imprimir.accept(upUp.apply("minúsculas"));

		BiFunction<Integer, Integer, Integer> aleatorio2 = (i, e) -> {
			int min = i;
			int max = e;
			if (i > e) {
				min = e;
				max = i;
			}
			return (int) (Math.random() * (max - min + 1) + min);
		};

		imprimir.accept(aleatorio2.apply(20, 10).toString());
	}
}
