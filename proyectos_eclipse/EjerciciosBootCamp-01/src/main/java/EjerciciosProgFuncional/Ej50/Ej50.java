package EjerciciosProgFuncional.Ej50;

import java.util.function.BiFunction;
import java.util.function.Consumer;

public class Ej50 {

	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		/*
		 * Crea una interfaz funcional que contenga un método “operacion” al que se le
		 * pasen dos números (o doubles). Crea una clase calculadora que tenga un método
		 * al que se le pasen dos enteros (o doubles) y un objeto tipo Arithmetic y
		 * realice la operación entre ambos valores. Crea otro método que haga lo mismo
		 * pero en vez de pasarle un objeto tipo Arithmetic se le pase una BiFuncional.
		 * En el main, crea dos operaciones diferentes (suma y resta, por ejemplo) de
		 * tipo Arithmetic y creando un objeto tipo calculadora mira las diferentes
		 * posibilidades que hay de llamar a sus métodos.
		 */
		

		Calculadora calculadora = new Calculadora();
		Arithmetic multiplicarArithmetic = (uno, dos) -> uno * dos;
		Arithmetic dividirArithmetic = (uno, dos) -> uno / dos;

		BiFunction<Double, Double, Double> multiplicarBiFunction = (uno, dos) -> uno * dos;
		BiFunction<Double, Double, Double> dividirBiFunction = (uno, dos) -> uno / dos;

		imprimir.accept("5x7 es : " + calculadora.calcular(5d, 7d, multiplicarArithmetic));
		imprimir.accept("3x33 es : " + calculadora.calcular(3d, 33d, multiplicarBiFunction));

		imprimir.accept("5/7 es : " + calculadora.calcular(5d, 7d, dividirArithmetic));
		imprimir.accept("3/33 es : " + calculadora.calcular(3d, 33d, dividirBiFunction));

	}

}
