package cliente.controller;

import util.Console;
import common.ICalculadora;

public class CalcController {
	
	private ICalculadora server;
	private Integer operando1, operando2;
	private String operacao;

	public void setServer(ICalculadora server) {
		this.server = server;
	}
	
	public Integer processarRequisicao() {
		Integer resultado = null;
		try {
			switch (operacao) {
			case "+":
				resultado = server.somar(operando1, operando2);
				break;
			case "-":
				resultado = server.subtrair(operando1, operando2);
				break;
			case "*":
				resultado = server.multiplicar(operando1, operando2);
				break;
			case "/":
				resultado = server.dividir(operando1, operando2);
				break;
			default:
				Console.println("Operacao invalida");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public void setOperando1(Integer operando1) {
		this.operando1 = operando1;
	}

	public void setOperando2(Integer operando2) {
		this.operando2 = operando2;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

}
