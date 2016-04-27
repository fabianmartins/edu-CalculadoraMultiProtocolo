package cliente.conexao.factory;

import cliente.conexao.ICalcFactory;
import common.ICalculadora;

/**
 * Classe que representa uma fábrica de sessão para o servidor "Calculadora".
 * O tipo de fábrica vai dependender de com o servidor está expondo seus serviços
 */
public class CalcFactory {

	private static ICalcFactory factory;
	
	public static ICalcFactory initFactory(CalcFactoryType factoryType) {
		switch (factoryType) {
		case TCP:
			factory = new CalcFactoryTCP();
			break;
		case UDP:
			factory = new CalcFactoryUDP();
			break;
		case RMI:
			factory = new CalcFactoryRMI();
			break;
		default:
			factory = null;
			break;
		}
		return factory;
	}

	public ICalculadora getSession() {
		return factory.getSession();
	}

}