package cliente.conexao.factory;

import common.ICalculadora;
import cliente.conexao.ICalcFactory;
import cliente.conexao.proxy.CalculadoraProxyUDP;

public class CalcFactoryUDP implements ICalcFactory {
	
	private String host = ICalculadora.DEFAULT_HOST;
	private int port = ICalculadora.DEFAULT_PORT;

	@Override
	public ICalculadora getSession() {
		return new CalculadoraProxyUDP(host,port);
	}

	@Override
	public void setServer(String server, int port) {
		this.host = server;
		this.port = port;
	}

}
