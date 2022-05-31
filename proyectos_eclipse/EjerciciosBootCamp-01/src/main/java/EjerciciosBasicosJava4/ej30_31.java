package EjerciciosBasicosJava4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ej30_31 {

	public static void main(String[] args) {
		try {
			System.out.println("\n>>>Archivo no encontrado:  ");
			ej30("cualquiercosa.txt");

			System.out.println("\n>>>Archivo no encontrado 02:  ");
			ej30b("cualquiercosa.txt");


			System.out.println("\n>>>ruta corta exception:  ");
			ej30("a.a");

			System.out.println("\n>>>Excepción por el charset:  ");
			ej30("src/main/java/otros/coches.xml", "24235");
			
//			System.out.println("\n>>>Archivo correcto:  ");
//			ej30("src/main/java/otros/coches.xml");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void ej30(String ruta) throws RutaCortaException {
		ej30(ruta, "");
	}

	public static void ej30(String ruta, String charset) throws RutaCortaException {
		BufferedReader br = null;
		File archivo;
		FileReader fr;
		if (ruta.length() < 4) {
			throw new RutaCortaException("menos de 4 letras");
		}
		try {
			archivo = new File(ruta);
			if (charset.equals("")) {
				fr = new FileReader(archivo);
			} else {
				fr = new FileReader(archivo, Charset.forName(charset));
			}
			br = new BufferedReader(fr);
			String linea;
			System.out.println("Codificación: " + fr.getEncoding());
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el archivo " + ruta);
		} catch (Exception e) {
			System.out.println("Excepcionado: " + e);
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				System.out.println("Excepcionado: " + e);
			}
		}
	}

	public static void ej30b(String ruta) throws RutaCortaException {
		File archivo = new File(ruta);
		String linea;
		if (ruta.length() < 4) {
			throw new RutaCortaException("menos de 4 letras");
		}
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

}
