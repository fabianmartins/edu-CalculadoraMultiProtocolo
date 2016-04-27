package cliente.ui;

import common.ICalculadora;

import cliente.conexao.factory.CalcFactoryType;


public class RunClient {

	public static void main(String[] args) {
		ClienteUI ui = new ClienteUI(CalcFactoryType.UDP,ICalculadora.DEFAULT_HOST,ICalculadora.DEFAULT_PORT);
		ui.mostrarCabecalho();
		do {
			ui.obterOperacao();
			ui.obterOperando1();
			ui.obterOperando2();
			ui.mostrarResultado();
		} while (!ui.exit());
		ui.close();
	}

}
