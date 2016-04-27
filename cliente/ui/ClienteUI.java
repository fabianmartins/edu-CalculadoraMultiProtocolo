package cliente.ui;

import util.Console;
import cliente.conexao.ICalcFactory;
import cliente.conexao.IServer;
import cliente.conexao.factory.CalcFactory;
import cliente.conexao.factory.CalcFactoryType;
import cliente.controller.CalcController;
import common.ICalculadora;

/**
 * Interface simples para demonstrar o funcionamento da calculadora
 * 
 * @author fm
 *
 */
public class ClienteUI {

	private ICalculadora server;
	private CalcFactoryType type;
	private String dest_host;
	private int dest_port;
	private CalcController controller;
	
	public ClienteUI(CalcFactoryType serverType, String host, int port) {
		this.type = serverType;
		this.dest_host = host;
		this.dest_port = port;
		init();
	}

	private void init() {
		ICalcFactory factory = CalcFactory.initFactory(type);
		factory.setServer(this.dest_host,this.dest_port);
		server = factory.getSession();
		((IServer) server).open();
		this.controller = new CalcController();
		controller.setServer(server);
	}

	public void mostrarCabecalho() {
		System.out.println("Sistema CALCULADORA");
		System.out.println("Funcionando sobre : " + type);
		System.out.println("--------------------------------");
	}

	public void obterOperando1() {
		String o1 = Console.readLine("Operando 1:");
		Integer operando1 = Integer.parseInt(o1);
		controller.setOperando1(operando1);
	}

	public void obterOperando2() {
		String o2 = Console.readLine("Operando 2:");
		Integer operando2 = Integer.parseInt(o2);
		controller.setOperando2(operando2);
	}

	public void obterOperacao() {
		String operacao = Console.readLine("Operacao [+ , -, * , / ou qualquer outra coisa para sair ]:").trim();
		controller.setOperacao(operacao);
	}

	public void mostrarResultado() {
		Integer resultado = controller.processarRequisicao();
		Console.println("O resultado da operacao e':" + resultado);
	}
	
	public boolean exit() {
		return "n".equalsIgnoreCase(Console.readLine("Deseja fazer outra operacao (s/n)? ").trim());
	}
	
	public void close() {
		((IServer) server).close();
	}
	
}
