package cliente.conexao.proxy;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import cliente.conexao.IServer;

public class CalculadoraProxyUDP extends CalculadoraProxy implements IServer {
	
	private static final long serialVersionUID = 1L;
	private static final int BUFSIZE = 4096;
	private InetAddress host;
	private int port;
	private DatagramSocket socket;

	public CalculadoraProxyUDP(String host, int port) {
		try {
			this.host = InetAddress.getByName(host);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		this.port = port;
	}

	@Override
	public void open() {
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		socket.close();
	}
	
	@Override
	int handleServerRequest(String message) throws Exception {
		DatagramPacket sendPacket, receivePacket;
		byte[] buffer = new byte[BUFSIZE];
		sendPacket = new DatagramPacket(message.getBytes(),message.length(),this.host,this.port);
		socket.send(sendPacket);
		receivePacket = new DatagramPacket(buffer,BUFSIZE);
		socket.receive(receivePacket);
		String resultAsString = new String(Arrays.copyOf(buffer, receivePacket.getLength()));
		int result = Integer.parseInt(resultAsString);
		return result;
	}
}
