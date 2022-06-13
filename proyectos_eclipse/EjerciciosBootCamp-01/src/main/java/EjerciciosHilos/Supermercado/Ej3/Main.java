package EjerciciosHilos.Supermercado.Ej3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		List<Caja> cajas = new ArrayList<>();
		cajas.add(new Caja(1));
		cajas.add(new Caja(2));
		cajas.add(new Caja(3));

		Cliente c1 = new Cliente(cajas, "Sra. Herminia");
		Cliente c2 = new Cliente(cajas, "Don pin pon");
		Cliente c3 =new Cliente(cajas, "Sra. María Luisa");
		
		
		Cajera cajero1 = new Cajera(cajas.get(0), "Pedro");
		Cajera cajero2 = new Cajera(cajas.get(1), "Mart");
		Cajera cajero3 = new Cajera(cajas.get(2), "Roberto");

		ExecutorService executor = Executors.newFixedThreadPool(3);
		Long inicio = System.currentTimeMillis();
//		c1.start();
//		c2.start();
//		c3.start();
		executor.submit(c1);
		executor.submit(c2);
		executor.submit(c3);

		cajero1.start();
		cajero2.start();
		cajero3.start();
//		cajera.start();
//		
//		cajera.join();
		Long finCajero1 = null;
		Long finCajero2 = null;
		Long finCajero3 = null;

		while (finCajero1 == null  || finCajero2 == null  || finCajero3 == null ) {
			if (finCajero1 == null  && !cajero1.isAlive()) {
				finCajero1 = System.currentTimeMillis();
			}
			if (finCajero2 == null && !cajero2.isAlive()) {
				finCajero2 = System.currentTimeMillis();
			}
			if (finCajero3 == null && !cajero3.isAlive()) {
				finCajero3 = System.currentTimeMillis();
			}
		}
		System.out.println("Tiempo de trabajo total: " + (System.currentTimeMillis() - inicio) +"ms");
		System.out.println("["+cajero1.getNombre()+"] "+(finCajero1- inicio) + " ms");
		System.out.println("["+cajero2.getNombre()+"] "+(finCajero2- inicio) + " ms");
		System.out.println("["+cajero3.getNombre()+"] "+(finCajero3- inicio) + " ms");
		
	}
}
