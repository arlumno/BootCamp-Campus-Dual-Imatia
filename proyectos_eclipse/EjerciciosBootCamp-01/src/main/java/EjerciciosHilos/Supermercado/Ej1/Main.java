package EjerciciosHilos.Supermercado.Ej1;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Cliente cliente = new Cliente();
		Thread cajera = new Thread(new Cajera(cliente));

		Long inicio = System.currentTimeMillis();
		cajera.start();
		cajera.join();
		System.out.println("Tiempo de trabajo: " + (System.currentTimeMillis() - inicio) + " ms");

	}
}
