package servidor.common;

import java.util.Arrays;
import java.util.StringTokenizer;

import common.ICalculadora;

/**
 * Esta classe implementa o comportamento que efetivamente dever ser realizado pelo servidor.
 * 
 * @author fm
 *
 */
public class CalculadoraServer implements ICalculadora {
	
	private static final long serialVersionUID = 1L;
	
	public CalculadoraServer() {
	
	}
	
	/**
	 * Este método faz o parsing do comando enviado pelo cliente e executa a operacao correspondente Espera-se que a
	 * mensagem seja enviada no formato <operador>;<operando1>;<operando2> (ex:
	 * +;3;5 devera' resultar em 8)
	 * 
	 * @param operation
	 * @return
	 */
	public Integer processRequest(String operation) {
		StringTokenizer tokenizer = new StringTokenizer(operation, ";");
		Integer tokenCount = 0, valor1 = 0, valor2 = 0, result = -1;
		String token = "", operador = "";
		String[] operadores = { "+", "*", "/", "-" };

		Arrays.sort(operadores);
		try {
			while (tokenizer.hasMoreTokens()) {
				token = tokenizer.nextToken();
				token.trim();
				if (tokenCount == 0) {
					operador = token;
					if (Arrays.binarySearch(operadores, operador) < 0)
						throw new IllegalArgumentException("Operador invalido:"
								+ operador);
				}
				if (tokenCount == 1)
					valor1 = Integer.parseInt(token);
				if (tokenCount == 2)
					valor2 = Integer.parseInt(token);
				tokenCount++;
			}
			// Agora, chama o servico de acordo com a operacao
			try {
				if ("+".equalsIgnoreCase(operador))
					result = somar(valor1, valor2);
				if ("-".equalsIgnoreCase(operador))
					result = subtrair(valor1, valor2);
				if ("*".equalsIgnoreCase(operador))
					result = multiplicar(valor1, valor2);
				if ("/".equalsIgnoreCase(operador))
					result = dividir(valor1, valor2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Erro no operando: " + tokenCount
					+ " - Nao e' um numero:" + token);
			nfe.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public Integer somar(Integer v1, Integer v2) throws Exception {
		return v1 + v2;
	}

	@Override
	public Integer subtrair(Integer v1, Integer v2) throws Exception {
		return v1 - v2;
	}

	@Override
	public Integer multiplicar(Integer v1, Integer v2) throws Exception {
		return v1 * v2;
	}

	@Override
	public Integer dividir(Integer v1, Integer v2) throws Exception {
		return v1 / v2;
	}

}
