import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Excepciones.IncompleteException;

public class Menu {
	Control control;
	Scanner lector;
	private ArrayList<String> opciones = new ArrayList<String>();
	private ArrayList<Runnable> accionOpciones = new ArrayList<Runnable>();
	private String titulo = "Menu";
	private String subTitulo = "Selecciona una opci�n";
	int seleccion = 0;
	boolean infinito;

	public Menu() {
		this(true);
	}

	public Menu(boolean infinito) {
		this.infinito = infinito;
		addOpcion("Salir", this::salir);
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public void run() {
		boolean continuar = true;
		lector = new Scanner(System.in);
		do {
//			try {
			continuar = mostrarMenu();
//			} catch (Exception e) {
//				System.out.println("Error: " + e);
//			}
		} while (continuar);

		// lector.close();
	}

	private boolean mostrarMenu() {
		boolean error = false;
		boolean continuar = true;

		StringBuilder menuString = new StringBuilder();
		menuString.append("<<<<<<<<<< " + titulo + " >>>>>>>>>>\n");
		menuString.append("\n" + subTitulo + ":");
		for (int i = 1; i < opciones.size(); i++) {
			menuString.append("\n [" + i + "] ");
			menuString.append(opciones.get(i));
		}
		menuString.append("\n [0] " + opciones.get(0));
		do {
			do {
				limpiarConsola();
				System.out.println(menuString.toString());
				try {
					seleccion = Integer.parseInt(lector.nextLine());
					System.out.println(seleccion);
					if (seleccion < 0 || opciones.size() < seleccion) {
						error = true;
					} else {
						error = false;
					}
				} catch (InputMismatchException e) {
					error = true;
				}
				if (error) {
					System.err.println("Opci�n no valida, pulse ENTER para continuar");
					lector.nextLine();
				}
			} while (error);

			if (accionOpciones.get(seleccion) != null) {
				accionOpciones.get(seleccion).run();
			}
			if (seleccion == 0 || !infinito) {
				continuar = false;
			} else {
				System.out.println(">> Pulse ENTER para continuar");
				lector.nextLine();
			}

		} while (continuar);
		return continuar;
	}

	public int getIndexOpcion() {
		return seleccion - 1;
	}

	public void salir() {// override to add exit options.

	}

	public void addOpcion(String opcion, Runnable accion) {
		opciones.add(opcion);
		accionOpciones.add(accion);
	}

	private void limpiarConsola() {
		StringBuilder saltos = new StringBuilder();
		int nSaltos = 20;
		for (int i = 0; i < nSaltos; i++) {
			saltos.append("\n");
		}
		System.out.println(saltos.toString());
	}
}
