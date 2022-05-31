package EjerciciosBasicosJava4;

public class ej29 {
	public static void main(String[] args) {
		try {
			int division = 3 / 0;
		} catch (ArithmeticException e) {
			System.out.println("No se puede dividir entre 0");
		} catch (Exception e) {
			System.out.println("Excepcionado: " + e.getMessage());
		} finally {
			System.out.println("fin del programa");
		}
	}
}
