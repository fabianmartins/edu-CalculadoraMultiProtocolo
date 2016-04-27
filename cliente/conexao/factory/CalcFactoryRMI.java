package cliente.conexao.factory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import cliente.conexao.ICalcFactory;
import common.ICalculadora;

public class CalcFactoryRMI implements ICalcFactory {
	
	private String host = ICalculadora.DEFAULT_HOST;
	private int port = ICalculadora.DEFAULT_PORT;

	@Override
	public ICalculadora getSession() {
		ICalculadora calc = null;
		try {
			Registry reg = LocateRegistry.getRegistry(host, port);
			calc = (ICalculadora) reg.lookup("CALCX");

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		return calc;
	}

	@Override
	public void setServer(String host, int port) {
		this.host = host;
		this.port = port;
	}

}
