package servidor.udp;

import common.ICalculadora;



/**
 * Executa a calculadora TCP
 * @author fm
 *
 */
public class RunServerUDP {

	
public static void main(String[] args) {
		CalculadoraServerUDP calc = new CalculadoraServerUDP(ICalculadora.DEFAULT_HOST,ICalculadora.DEFAULT_PORT);
		calc.run();
	}

}
