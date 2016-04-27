package cliente.conexao.proxy;

import common.CalculadoraEncoder;
import common.ICalculadora;

public abstract class CalculadoraProxy implements ICalculadora {
	
	private static final long serialVersionUID = 1L;

	abstract int handleServerRequest(String message) throws Exception;
	
	@Override
	public Integer somar(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.somar(v1, v2);
		Integer result = handleServerRequest(msg);
		return result;
	}

	@Override
	public Integer subtrair(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.subtrair(v1, v2);
		Integer result = handleServerRequest(msg);
		return result;
	}

	@Override
	public Integer multiplicar(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.multiplicar(v1, v2);
		Integer result = handleServerRequest(msg);
		return result;
	}

	@Override
	public Integer dividir(Integer v1, Integer v2) throws Exception {
		String msg = CalculadoraEncoder.dividir(v1, v2);
		int result = handleServerRequest(msg);
		return result;
	}

}
