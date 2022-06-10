package EjerciciosHilos.Ej41;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class MartyMcFly extends Thread {
	String[] frases = {
			"- Hey, Doc. No tenemos suficiente carretera para ir a 140 km.\n- ¿Carretera? A donde vamos, no necesitaremos carreteras",
			"- Pero Doc, ¿has construido una máquina del tiempo con un De Lorean?\n- En mi opinión, si vas a hacer algo como esto, hazlo con estilo.",
			"Supongo que ustedes no están preparados para esta música. Pero a sus hijos les encantará.",
			"Una última cosa, si tienen hijos y uno de ellos, a los ocho años accidentalmente quema la alfombra de la sala,\n no se enojen demasiado.",
			"- ¡Marty, tienes que venir conmigo!\n- ¿A dónde?\n- ¡De regreso al futuro!"

	};

	@Override
	public void run() {
		System.err.println(frase());
	}

	public String frase() {
		return frases[(int) (Math.random() * frases.length)];
	}		
}
