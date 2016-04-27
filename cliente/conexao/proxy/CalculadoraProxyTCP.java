package cliente.conexao.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import cliente.conexao.IServer;

public class CalculadoraProxyTCP extends CalculadoraProxy implements IServer {

	private static final long serialVersionUID = 1L;
	private Socket socket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private String host;
	private int port;

	public CalculadoraProxyTCP(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void open() {
		try {
			socket = new Socket(host, port);
			socketOut = new PrintWriter(socket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	int handleServerRequest(String message) throws Exception {
		String sResult;
		int iResult = -1;
		socketOut.println(message);
		try {
			sResult = socketIn.readLine();
			iResult = Integer.parseInt(sResult);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return iResult;
	}

}
