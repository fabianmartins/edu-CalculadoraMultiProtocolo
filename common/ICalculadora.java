package common;

import java.io.Serializable;
import java.rmi.Remote;


public interface ICalculadora extends Remote, Serializable {
	
	public static final String DEFAULT_HOST = "0.0.0.0";
	public static final int DEFAULT_PORT = 1099;
	
	Integer somar(Integer v1, Integer v2) throws Exception;
	Integer subtrair(Integer v1, Integer v2) throws Exception;
	Integer multiplicar(Integer v1, Integer v2) throws Exception;
	Integer dividir(Integer v1, Integer v2) throws Exception;

}