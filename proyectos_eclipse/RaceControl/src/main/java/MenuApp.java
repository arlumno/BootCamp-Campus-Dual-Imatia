import java.util.ArrayList;
import java.util.Scanner;

import Excepciones.IncompleteException;

public class MenuApp {
	Control control;
	Scanner lector;

	public MenuApp(Control control) {
		this.control = control;
	}

	public void run() {
		boolean continuar = true;
		lector = new Scanner(System.in);
		do {
			try {
				continuar = menuAcciones();
			} catch (Exception e) {
				System.out.println("Error: " + e);
			}
		} while (continuar);

		lector.close();
	}

	private int mostrarMenu(ArrayList<String> opciones, String titulo) throws IncompleteException {
		if(opciones.size() == 0) {
			throw new IncompleteException("Menu '" + titulo+ "' sin opciones");
		}
		int nOpcion = 1;
		int seleccion = -1;
		boolean error = false;
		StringBuilder menuString = new StringBuilder();
		menuString.append("<<<<<<<<<< " + titulo + " >>>>>>>>>>\n");
		menuString.append("\nSelecciona una opción:");
		for (String opcion : opciones) {
			menuString.append("\n [" + nOpcion + "] ");
			menuString.append(opcion);
			nOpcion++;
		}
		menuString.append("\n [0] Salir");
		

		do {
			System.out.println(menuString.toString());
			try {
				seleccion = Integer.parseInt(lector.nextLine());
				System.out.println(seleccion);
				if(seleccion < 0 || opciones.size() < seleccion) {
					error = true;
				}else {
					error = false;
				}
			} catch (Exception e) {				
				error = true;
			}
			if(error) {
				System.err.println("Opción no valida, pulse ENTER para continuar");
				lector.nextLine();
				limpiarConsola();
			}
		} while (error);

		return seleccion;
	}

	private boolean menuAcciones() throws IncompleteException {
		boolean continuar = true;
		ArrayList<String> opciones = new ArrayList<>();
		opciones.add("Mostrar torneos");
		opciones.add("Mostrar torneo");
		int resultado = mostrarMenu(opciones, "Race Control");

		switch (resultado) {
		case 0: //
			continuar = false;
			break;
		}
		return continuar;
	}
	private void limpiarConsola() {
		StringBuilder saltos = new StringBuilder();
		int nSaltos = 100;
		for(int i= 0 ; i < nSaltos; i++) {
			saltos.append("\n");
		}
		System.out.println(saltos.toString());
	}
}
