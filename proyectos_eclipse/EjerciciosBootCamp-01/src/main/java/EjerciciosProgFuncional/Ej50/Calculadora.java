package EjerciciosProgFuncional.Ej50;

import java.util.function.BiFunction;

public class Calculadora {
	public double calcular(Double a, Double b, Arithmetic tipoOperacion) {				
		return tipoOperacion.operacion(a, b);
	}
	public double calcular(Double a, Double b, BiFunction<Double,Double,Double> tipoOperacion) {		
		return tipoOperacion.apply(a, b);
	}
}
