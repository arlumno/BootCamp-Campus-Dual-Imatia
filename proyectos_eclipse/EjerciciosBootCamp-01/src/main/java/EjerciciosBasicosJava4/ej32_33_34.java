package EjerciciosBasicosJava4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class ej32_33_34 {

	public static void main(String[] args) {
		System.out.println("\n>>>Lee coched.xml :");
		ej32("src/main/java/otros/coches.xml");

		System.out.println("\n>>>Escribe pruebas.txt");
		String texto = "Ejercicio 32 \r\n"
						+ "\t Crea un método que lea de un fichero y muestra su contenido por pantalla \r\n"
						+ "Ejercicio 33: \r\n"
						+ "\t  Crea un método que escriba en un fichero. \r\n"
						+ "Ejercicio 34: \r\n"
						+ "\t  Crea un método que borre un fichero dado.";
		ej33("src/main/java/otros/pruebas.txt",texto); // escribe

		System.out.println("\n>>>Lee pruebas.txt:");
		ej32("src/main/java/otros/pruebas.txt"); // lee

		System.out.println("\n>>>Elimina pruebas.txt:");
		ej34("src/main/java/otros/pruebas.txt"); // lee

		System.out.println("\n>>>Lee pruebas.txt:");
		ej32("src/main/java/otros/pruebas.txt"); // lee
	}

	public static void ej32(String ruta) {
		File archivo = new File(ruta);
		String linea;
		try (BufferedReader br = new BufferedReader(new FileReader(archivo));) {
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo " + ruta);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void ej33(String ruta, String texto) {
		File archivo = new File(ruta);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));) {
			bw.write(texto);
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo " + ruta);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void ej34(String ruta) {
		File archivo = new File(ruta);
		try {

			if (archivo.exists()) {
				archivo.delete();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
