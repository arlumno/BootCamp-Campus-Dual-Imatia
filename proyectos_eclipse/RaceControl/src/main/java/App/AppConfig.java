package App;

public class AppConfig {
	private int multiplicadorUnidadTiempo = 6000; // para que un minuto pase mas rapido. 100 => 60segs = 0,6seg, 0 para
	private boolean infoMode = false;												// instantaneo
	private static AppConfig config;

	private AppConfig() {
	}

	public static AppConfig get() {
		if (config == null) {
			config = new AppConfig();
		}
		return config;
	}

	public int getMultiplicadorUnidadTiempo() {
		return multiplicadorUnidadTiempo;
	}

	public void setMultiplicadorUnidadTiempo(int multiplicadorUnidadTiempo) {
		if(multiplicadorUnidadTiempo<0) {
			multiplicadorUnidadTiempo = 0;
		}
		this.multiplicadorUnidadTiempo = multiplicadorUnidadTiempo;
	}

	public boolean isInfoMode() {
		return infoMode;
	}

	public void setInfoMode(boolean infoMode) {
		this.infoMode = infoMode;
	}
		
	public long  getUnidadTiempoMs() {
		if(multiplicadorUnidadTiempo == 0) {
			return 0;
		}else {
			
			return 60000 / multiplicadorUnidadTiempo; //pasamos de minutos a milisegundos proporcionales
		}
	}
	
}
