package EjerciciosProgFuncional.Ej46;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Ej46 {

	public static void main(String[] args) {
		Consumer<String> imprimir = in-> System.out.println(in);
		imprimir.accept("Rojo");
		
		System.out.println("----");
		BiConsumer<String,Integer> imprimirXVeces = (in, i) -> {
			while(i>0) {
				System.out.println(in);			
				i--;
			}
		};				
		imprimirXVeces.accept("cinco", 5);
	}

}
