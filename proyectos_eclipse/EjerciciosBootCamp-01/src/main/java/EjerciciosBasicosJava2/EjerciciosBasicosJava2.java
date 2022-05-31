package EjerciciosBasicosJava2;

import java.time.LocalDate;

import EjerciciosBasicosJava2.ej14.Doctor;
import EjerciciosBasicosJava2.ej14.Persona;
import EjerciciosBasicosJava2.ej14.Policia;
import EjerciciosBasicosJava2.ej14.Profesor;

public class EjerciciosBasicosJava2 {
	ControlRemoto mando = new ControlRemoto();
	Mercancia mercancia = new Mercancia(1, 15, "Platanos", "Ana", "Canarias", "Tenerife", "AC");
	MercanciaPerecedera mercanciaPerecedera  = new MercanciaPerecedera(0, 3, "Manzanas", "Paco", "Galicia", "Bueu", "J", LocalDate.parse("2022-11-05"));
	Doctor doctor = new Doctor("12345678Z", "House", 189, 90, "Diagnostico", "12211254DDSDDX");
	Policia policia = new Policia("65432198D", "Paco", 175, 75,"Capitán", "656612");
	Profesor profesor = new Profesor("71034798D", "Ana", 170, 70,"Tecnología");
	public void imprimirResultados() {
		System.out.println("Ej11: inicial:  " + mando.toString());
		mando.subirVolumen();
		mando.subirVolumen();
		mando.aumenarCanal();
		mando.aumenarCanal();
		mando.cambiarEstado();
		System.out.println("Ej11: inicial:  " + mando.toString());
		mando.bajarVolumen();
		mando.disminuirCanal();
		mando.cambiarEstado();
		System.out.println("Ej11: inicial:  " + mando.toString());		
		
		System.out.println();		
		System.out.println("Ej14: " + doctor.toString());
		System.out.println("Ej14: " + ((Persona) doctor).toString());
		System.out.println("Ej14: " + policia.toString());
		System.out.println("Ej14: " + ((Persona)policia).toString());
		System.out.println("Ej14: " + profesor.toString());
		System.out.println("Ej14: " + ((Persona)profesor).toString());
		
		
		System.out.println();		
		System.out.println("Ej15: " + mercancia.toString());
		System.out.println("Ej15: " + mercanciaPerecedera.toString());

		
		
		
	}

}
