package EjerciciosProgFuncional.Ej48;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Ej48 {

	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		
		Function<Integer,Integer> aleatorio = (i)->  (int)(Math.random() *100d + 1d); 

		separador.run();
		
		
	}
}
