package common;

public class CalculadoraEncoder {

	public static String somar(Integer v1, Integer v2) {
		String msg = "+" + ";" + v1 + ";" + v2;
		return msg;
	}

	public static String subtrair(Integer v1, Integer v2) {
		String msg = "-" + ";" + v1 + ";" + v2;
		return msg;

	}

	public static String multiplicar(Integer v1, Integer v2) {
		String msg = "*" + ";" + v1 + ";" + v2;
		return msg;
	}

	public static String dividir(Integer v1, Integer v2) {
		String msg = "/" + ";" + v1 + ";" + v2;
		return msg;
	}

}
