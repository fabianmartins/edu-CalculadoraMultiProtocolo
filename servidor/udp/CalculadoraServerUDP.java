package servidor.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;

import common.ICalculadora;
import servidor.common.CalculadoraServer;
import util.Console;

public class CalculadoraServerUDP extends CalculadoraServer implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatagramSocket socket;
	private DateFormat dateFormat = DateFormat.getDateTimeInstance();
	private String host;
	private int port;
	private static final int BUFSIZE = 4096;

	public CalculadoraServerUDP() {
		this.host = ICalculadora.DEFAULT_HOST;
		this.port = ICalculadora.DEFAULT_PORT;
		init();
	}

	public CalculadoraServerUDP(int port) {
		this.host = ICalculadora.DEFAULT_HOST;
		this.port = port;
		init();
	}

	public CalculadoraServerUDP(String host, int port) {
		this.host = host;
		this.port = port;
		init();
	}
	
	/**
	 * Abre o socket
	 */
	private void init() {
		try {
			socket = new DatagramSocket(port, InetAddress.getByName(host));
			System.out.println("Calculadora UDP ativa e aguardando conexao.");
		} catch (IOException e) {
			System.out.println("Erro criando o socket na porta:" + port);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String request, response;
		Integer result;
		RequestProcessor requestproc = new RequestProcessor();
		ResponseProcessor responseproc = new ResponseProcessor();
		try {
			while (true) {
				request = requestproc.receiveRequest();
				result = this.processRequest(request);
				response = result.toString();
				responseproc.sendResponse(requestproc.getClientAddress(),
						requestproc.getClientPort(), response);
			}
		} catch (IOException ioe) {

		}
	}

	private class RequestProcessor {

		private InetAddress clientAddress;
		private Integer clientPort;

		public InetAddress getClientAddress() {
			return this.clientAddress;
		}

		public Integer getClientPort() {
			return this.clientPort;
		}
		
		private String getStandardLogHeader(String clientID) {
			return "["+dateFormat.format(Calendar.getInstance().getTime())+"] "+clientID+" : ";
		}
		
		private void notify(String msg, String clientID) {
			Console.println(getStandardLogHeader(clientID)+ msg);
		}

		public String receiveRequest() throws IOException {
			DatagramPacket receivePacket;
			byte[] buffer = new byte[BUFSIZE];
			String request = null;
			receivePacket = new DatagramPacket(buffer, BUFSIZE);
			socket.receive(receivePacket);
			clientAddress = receivePacket.getAddress();
			clientPort = receivePacket.getPort();
			request = new String(Arrays.copyOf(buffer,
					receivePacket.getLength()));
			notify("Mensagem recebida:"+request, clientAddress.toString()+":"+clientPort);
			return request;
		}
	}

	private class ResponseProcessor {
		
		private String getStandardLogHeader(String clientID) {
			return "["+dateFormat.format(Calendar.getInstance().getTime())+"] "+clientID+" : ";
		}
		
		private void notify(String msg, String clientID) {
			Console.println(getStandardLogHeader(clientID)+ msg);
		}
		
		public void sendResponse(InetAddress clientAddress, Integer clientPort,
				String response) throws IOException {
			DatagramPacket sendpacket = new DatagramPacket(response.getBytes(),
					response.length(), clientAddress, clientPort);
			socket.send(sendpacket);
			notify("Resposta enviada:"+response, clientAddress.toString()+":"+clientPort);
		}

	}

}
