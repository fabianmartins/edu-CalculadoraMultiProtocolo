package cliente.conexao.factory;

import cliente.conexao.ICalcFactory;
import cliente.conexao.proxy.CalculadoraProxyTCP;
import common.ICalculadora;

public class CalcFactoryTCP implements ICalcFactory {
	
	private String host = ICalculadora.DEFAULT_HOST;
	private int port = ICalculadora.DEFAULT_PORT;

	@Override
	public ICalculadora getSession() {
		return new CalculadoraProxyTCP(host,port);
	}

	@Override
	public void setServer(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
}
