package EjerciciosBasicosJava1;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class EjerciciosBasicosJava1 {

	public void imprimirResultados() {
		System.out.println("Ej01 - Holita con maven");
		System.out.println();
		System.out.println("Ej02a - Área de un circulo de 15 de radio: " + getArea(15));
		System.out.println("Ej02b - La longitud de un circulo de 15 de radio: " + getLongitud(15));
		System.out.println();
		System.out.println("Ej03 - Login armando//123456: " + login("armando", "123456"));
		System.out.println("Ej03 - Login pedro//123456: " + login("pedro", "123456"));
		System.out.println("Ej03 - Login armando//armando: " + login("armando", "armando"));
		System.out.println();
		System.out.println("Ej04a - Suma los 5 primeros números: " + sumaCincoPrimerosNumeros());
		System.out.println("Ej04ab - Suma los 5 primeros números: " + sumaCincoPrimerosNumeros2());
		System.out.println("Ej04b - Suma los 20 primeros números pares: " + sumaVeintePrimerosPares());
		System.out.println("Ej04bb - Suma los 20 primeros números pares: " + sumaVeintePrimerosPares2());
		System.out.println("Ej04b: - Factorial de 10: " + getFactorialDiez());
		System.out.println("Ej04ba: - Factorial de 10: " + getFactorial2(10));
		System.out.println();
		System.out.println("Ej05a:" + isPositivoNegativoNeutro(-33));
		System.out.println("Ej05a:" + isPositivoNegativoNeutro(22));
		System.out.println("Ej05b:" + isMultiplo(22, 33));
		System.out.println("Ej05b:" + isMultiplo(22, 44));
		System.out.println("Ej05c:" + isMayorQue(22, 44));
		System.out.println("Ej05c:" + isMayorQue(44, 22));
		System.out.println();
		System.out.println("Ej06a: " + estacionActual());
		System.out.println("Ej06b: " + parteDia());
		System.out.println("Ej06a++: " + estacionActualMejorado());
		System.out.println();
		System.out.println("Ej07a: mostrar 15 numeros :  " + mostrarNumerosNaturales(15));
		System.out.println("Ej07b: sumar primeros 100 numeros:  " + sumarNumerosNaturales(100));
		System.out.println(
				"Ej07c: indice 3 de array  {\"cero\", \"uno\", \"dos\", \"tres\", \"cuatro\"}:  " + indiceArray(4));
		System.out.println();
		System.out.println("Ej08: incrementar valor, tope 100 :  " + incrementarValorConTope(100));
		System.out.println("Ej09: incrementar valor, tope 100 :  " + incrementarValorConTope2(100));
		System.out.println();
		System.out.println("Ej09: Bolas: " + bolasAleatorias());
	}

	// Ej 02
	public double getArea(double radio) {
		return Math.pow(Math.PI * radio, 2);
	}

	public double getLongitud(double radio) {
		return 2 * Math.PI * radio;
	}

	// Ej 03
	public String saludo(String nombre) {
		return "Holita " + nombre;
	}

	public void errorCredenciales() {
		System.err.println("nombre o clave no coinciden.");
	}

	public String login(String nombre, String password) {
		final String USUARIO_VALIDO = "ARMANDO";
		final String PASSWORD_VALIDO = "123456";
		String resultado = "";
		if (nombre.toUpperCase().equals(USUARIO_VALIDO) && password.equals(PASSWORD_VALIDO)) {
			resultado = saludo(nombre);
		} else {
			errorCredenciales();
		}
		return resultado;
	}

	// Ej 04
	public int sumaCincoPrimerosNumeros() {
		return 1 + 2 + 3 + 4 + 5;
	}

	public int sumaCincoPrimerosNumeros2() {
		int resultado = 0;
		for (int i = 1; i <= 5; i++) {
			resultado += i;
		}
		return resultado;
	}

	public int sumaVeintePrimerosPares() {
		int par = 2;
		int resultado = par;
		resultado += par * 2;
		resultado += par * 3;
		resultado += par * 4;
		resultado += par * 5;
		resultado += par * 6;
		resultado += par * 7;
		resultado += par * 8;
		resultado += par * 9;
		resultado += par * 10;
		resultado += par * 11;
		resultado += par * 12;
		resultado += par * 13;
		resultado += par * 14;
		resultado += par * 15;
		resultado += par * 16;
		resultado += par * 17;
		resultado += par * 18;
		resultado += par * 19;
		resultado += par * 20;

		return resultado;
	}

	public int sumaVeintePrimerosPares2() {
		int resultado = 0;
		int par = 0;
		for (int i = 0; i < 20; i++) {
			par += 2;
			resultado += par;
		}
		return resultado;
	}

	public int getFactorialDiez() {
		return 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2;
	}

	public int getFactorial2(int numero) {
		int resultado = 1;
		while (numero > 1) {
			resultado *= numero;
			numero--;
		}
		return resultado;
	}

	// Ej 05
	public String isPositivoNegativoNeutro(int numero) {
		String resultado = "El numero " + numero + " es: ";
		if (numero > 0) {
			resultado += "positivo";
		} else if (numero < 0) {
			resultado += "negativo";
		} else {
			resultado += "neutro";
		}
		return resultado;
	}

	public String isMultiplo(int multiple, int numero) {
		String resultado = "El numero " + multiple;
		if (numero % 2 == 0) {
			resultado += " es multiplo de " + numero;
		} else {
			resultado += " NO es multiplo de " + numero;
		}
		return resultado;
	}

	public String isMayorQue(int numero, int numero2) {
		String resultado = "El numero " + numero;
		if (numero > numero2) {
			resultado += " es mayor que " + numero2;
		} else {
			resultado += " es menor que " + numero2;
		}
		return resultado;
	}

	// Ej 06
	public String estacionActual() {
		String resultado = "";
		LocalDate hoy = LocalDate.now();
		resultado = "Hoy " + hoy + " es ";
		switch (hoy.getMonthValue()) {
			case 1:
			case 2:
			case 3:
				resultado += "Invierno";
				break;
			case 4:
			case 5:
			case 6:
				resultado += "Primavera";
				break;
			case 7:
			case 8:
			case 9:
				resultado += "Verano";
				break;
			default:
				resultado += "Otoño";
				break;
			}

		return resultado;
	}

	public String estacionActualMejorado() {
		String resultado = "";
		LocalDate hoy = LocalDate.now();
		resultado = "Hoy " + hoy + " es ";
		/*
		 * Primavera: 21 marzo hasta 20 junio Verano: 21 junio hasta 20 septiembre
		 * Otoño: 21 septiembre hasta 20 diciembre Invierno: 21 diciembre hasta 20 marzo
		 */

		if (hoy.isAfter(LocalDate.of(hoy.getYear(), 12, 20))) {
			resultado += "Invierno";
		} else if (hoy.isAfter(LocalDate.of(hoy.getYear(), 9, 20))) {
			resultado = "Otoño";
		} else if (hoy.isAfter(LocalDate.of(hoy.getYear(), 6, 20))) {
			resultado += "Verano";
		} else if (hoy.isAfter(LocalDate.of(hoy.getYear(), 3, 20))) {
			resultado += "Primavera";
		} else {
			resultado += "Invierno";
		}

		return resultado;
	}

	public String parteDia() {
		String resultado = "";
		LocalTime ahora = LocalTime.now();
		resultado = "Ahora a las " + ahora.format(DateTimeFormatter.ofPattern("H:mm")) + " es por la ";
		/*
		 * las partes del día se dividen en Mañana: de 6 a 12, Tarde: de 12 a 19 ,Noche:
		 * de 19 a 24 y Madrugada de 24 a 6.
		 */
		switch (ahora.getHour()) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			resultado += "Madrugada";
			break;
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
			resultado += "Mañana";
			break;
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
			resultado += "Tarde";
			break;
		default:
			resultado += "Noche";
			break;

		}

//			if (ahora.isAfter(LocalTime.of(19, 0))) {
//				resultado += "Noche";
//			} else if (ahora.isAfter(LocalTime.of(12, 0))) {
//				resultado += "Tarde";
//			} else if (ahora.isAfter(LocalTime.of(6, 0))) {
//				resultado += "Mañana";
//			} else if (ahora.isAfter(LocalTime.of(0, 0))) {
//				resultado += "Madrugada";
//			}
		return resultado;
	}

	// Ej 07
	public String mostrarNumerosNaturales(int cantidad) {
		StringBuilder resultado = new StringBuilder();
		for (int i = 1; i <= cantidad; i++) {
			resultado.append(i + " ");
		}

		return resultado.toString();
	}

	public Long sumarNumerosNaturales(int cantidad) {
		long resultado = 0;
		for (int i = 1; i <= cantidad; i++) {
			resultado += i;
		}
		return resultado;
	}

	public String indiceArray(int index) {
		String[] array = { "cero", "uno", "dos", "tres", "cuatro" };
		String resultado = "";
		if (index < array.length) {
			resultado = array[index];
		} else {
			resultado = "---Indice fuera de rango---";
		}
		return resultado;
	}

	// Ej 08
	public int incrementarValorConTope(int limite) {
		int variable = 0;
		while (variable < limite) {
			variable++;
		}
		return variable;
	}

	// Ej 09
	public int incrementarValorConTope2(int limite) {
		int variable = -1;
		do {
			variable++;
		} while (variable < limite);

		return variable;
	}

	// Ej 10
	public String bolasAleatorias() {
		StringBuilder resultado = new StringBuilder();
		final int CANTIDAD_BOLAS = 10;
		String[] tipoBolas = { "azul", "roja", "verde" };
		int nAleatorio;
		int contador = 0;
		boolean flagAzul = false;

		for (int i = 1; i <= 10; i++) {
			nAleatorio = (int) (Math.random() * 3);

			if (!flagAzul) {
				resultado.append(tipoBolas[nAleatorio] + " ");
			}

			if (tipoBolas[nAleatorio].equals("azul")) {
				contador++;
				flagAzul = true;
			}
		}
		resultado.append("--> Han salido " + contador + " bolas azules!");

		return resultado.toString();
	}

}
