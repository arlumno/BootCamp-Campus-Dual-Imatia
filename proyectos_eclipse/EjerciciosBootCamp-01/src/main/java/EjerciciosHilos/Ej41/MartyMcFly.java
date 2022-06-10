package EjerciciosHilos.Ej41;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MartyMcFly extends Thread {
	String[] frases = {
			"- Hey, Doc. No tenemos suficiente carretera para ir a 140 km.\n- �Carretera? A donde vamos, no necesitaremos carreteras",
			"- Pero Doc, �has construido una m�quina del tiempo con un De Lorean?\n- En mi opini�n, si vas a hacer algo como esto, hazlo con estilo.",
			"Supongo que ustedes no est�n preparados para esta m�sica. Pero a sus hijos les encantar�.",
			"Una �ltima cosa, si tienen hijos y uno de ellos, a los ocho a�os accidentalmente quema la alfombra de la sala,\n no se enojen demasiado.",
			"- �Marty, tienes que venir conmigo!\n- �A d�nde?\n- �De regreso al futuro!"

	};

	@Override
	public void run() {
		System.err.println(frase());
	}

	public String frase() {
		return frases[(int) (Math.random() * frases.length)];
	}		
}
