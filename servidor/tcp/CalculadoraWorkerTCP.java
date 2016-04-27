package servidor.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Calendar;

import servidor.common.CalculadoraServer;
import util.Console;

/**
 * Objeto que realiza o atendimento a um cliente
 * 
 * @author fm
 * 
 */
public class CalculadoraWorkerTCP extends CalculadoraServer implements Runnable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private String clientID;
	private DateFormat dateFormat = DateFormat.getDateTimeInstance();

	public CalculadoraWorkerTCP(Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.clientID = clientSocket.getInetAddress().getHostAddress()+":"+clientSocket.getPort();
		try {
			socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
			socketIn = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private String getStandardLogHeader() {
		return "["+dateFormat.format(Calendar.getInstance().getTime())+"] "+clientID+" : ";
	}
	
	private void notify(String msg) {
		Console.println(getStandardLogHeader()+ msg);
	}

	/**
	 * Gerencia uma requisicao.
	 * 
	 * @param clientSocket
	 *            - Socket produzido no accept.
	 */
	@Override
	public void run() {
		String operation="";
		notify("CLIENTE CONECTADO");
		boolean connected = true;
		try {
			// O atendimento ocorre enquanto o cliente estiver conectado
			while(connected) {
				if (socketIn.ready()) {
					operation = operation+socketIn.readLine();
					notify("Operacao "+operation);
					int result = this.processRequest(operation);
					socketOut.println(Integer.toString(result));
					operation="";
				} 
				else {
					// Este e' um artificio para detectar a desconexao por parte do cliente 
					int i = socketIn.read();
					if (i==-1) connected = false; // Se voce faz a leitura em uma inputstream e obtem -1, a conexao foi desfeita.
					else operation=operation+((char) i); // senao o valor e' util e voce nao pode perde-lo. Por isso o concatenamos `a "operacao"
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}; 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (clientSocket != null)
					clientSocket.close();
				notify("Thread terminada para "+this.clientID);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		notify("CLIENTE DESCONECTADO");
	}
}
