package EjerciciosProgFuncional.Ej49;

import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Ej49 {

	public static void main(String[] args) {
		Runnable separador = () -> System.out.println("\n--------\n");
		Consumer<String> imprimir = (it) -> {
			separador.run();
			System.out.println(it);
			separador.run();
		};
		
		Predicate<Integer> isPar = i-> i%2 == 0; 
		 	
		imprimir.accept("33 es par: " +isPar.test(3));	
		imprimir.accept("22 es par: " +isPar.test(2));
		
		//BiPredicate<String,String> iguales = (a,b)-> { return a.equalsIgnoreCase(b);};
		BiPredicate<String,String> iguales = (a,b)-> { return a.toLowerCase().compareTo(b.toLowerCase()) == 0 ;};
	//	BiPredicate<String,String> iguales = (a,b)-> { return a.compareToIgnoreCase(b)== 0 ;};
		imprimir.accept("ARMANDO armando: " +iguales.test("ARMANDO","armando"));
		imprimir.accept("ARMANDO fernando: " +iguales.test("ARMANDO","fernando"));
		
		String a = new String("a");
		String b = new String("a");
		String n = null;
		
		System.out.println(a.equals(b));
		System.out.println(a.compareTo(b));
		System.out.println(a.equalsIgnoreCase(b));
		
		
		//System.out.println(a.compareTo(b));

		
	}
	
}
