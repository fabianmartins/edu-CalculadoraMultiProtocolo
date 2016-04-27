package test;

import cliente.conexao.ICalcFactory;
import common.ICalculadora;
import cliente.conexao.IServer;
import cliente.conexao.factory.CalcFactory;
import cliente.conexao.factory.CalcFactoryType;

public class ClienteCalculadora {

	private static ICalculadora server;

	private static void somar(int a, int b) {
		int result = -1;
		try {
			result = server.somar(a, b);
			System.out.println("A soma entre " + a + " e " + b + " e' " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ICalcFactory factory = CalcFactory.initFactory(CalcFactoryType.TCP);
		factory.setServer("localhost", 1099);
		server = factory.getSession();
		((IServer) server).open();
		somar(10,15);
		somar(20,25);
		((IServer) server).close();
		System.exit(0);
	}
}
