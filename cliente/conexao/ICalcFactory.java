package cliente.conexao;

import common.ICalculadora;

public interface ICalcFactory {
	ICalculadora getSession();
	void setServer(String server, int port);
}
