package EjerciciosProgFuncional.Ej47;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.Supplier;

public class Ej47 {

	public static void main(String[] args) {
		PersonaJava8 per1 = new PersonaJava8("Roberto", "Merino");
		System.out.println(per1.toString());

		Runnable separador = () -> System.out.println("\n--------\n");
		separador.run();
		
		// BiConsumer<PersonaJava8, String> cambiarNombre = (p,name) ->
		// p.setNombre(name); //opcion A
		BiConsumer<PersonaJava8, String> cambiarNombre = PersonaJava8::setNombre;// opcion B
		cambiarNombre.accept(per1, "Paco");
		System.out.println(per1.toString());

		separador.run();

		List<String> lista = Arrays.asList("uno", "dos", "tres", "cuatro");
//		Consumer<String> imprimir = in-> System.err.println(in); //opcion A
		Consumer<String> imprimir = System.out::println; // opcion B

		lista.forEach(imprimir);

		separador.run();

		Supplier<Integer> aleatorio = ()->  (int)(Math.random() *100d + 1d); // Opción A
		System.out.println(aleatorio.get());
		IntSupplier aleatorio2 = ()->  (int)(Math.random() *100d + 1d); // Opción B
		System.out.println(aleatorio2.getAsInt());
		
		separador.run();

		Supplier<PersonaJava8> crearPersona = ()-> new PersonaJava8("Paquito", "Chocolatero");
		PersonaJava8 per2 = crearPersona.get();
		System.out.println(per2.toString());

	}
}
