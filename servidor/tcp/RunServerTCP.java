package servidor.tcp;

import common.ICalculadora;



/**
 * Executa a calculadora TCP
 * @author fm
 *
 */
public class RunServerTCP {

	
public static void main(String[] args) {
		CalculadoraManagerTCP calc = new CalculadoraManagerTCP(ICalculadora.DEFAULT_HOST,ICalculadora.DEFAULT_PORT);
		calc.run();
	}

}
