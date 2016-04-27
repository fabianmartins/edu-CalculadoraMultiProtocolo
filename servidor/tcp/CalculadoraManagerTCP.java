package servidor.tcp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import common.ICalculadora;

/**
 * Servidor TCP para o serviço calculadora. Este servidor apenas recebe o cliente e o passa para atendimento por meio de outro objeto.
 * 
 * @author Fabian Martins
 **/
public class CalculadoraManagerTCP implements Runnable {

	private ServerSocket socket;
	private String host;
	private int port;

	public CalculadoraManagerTCP() {
		this.host = ICalculadora.DEFAULT_HOST;
		this.port = ICalculadora.DEFAULT_PORT;
		init();
	}

	public CalculadoraManagerTCP(int port) {
		this.host = ICalculadora.DEFAULT_HOST;
		this.port = port;
		init();
	}

	public CalculadoraManagerTCP(String host, int port) {
		this.host = host;
		this.port = port;
		init();
	}

	/**
	 * Abre o socket
	 */
	private void init() {
		try {
			socket = new ServerSocket(port, 500, InetAddress.getByName(host));
			System.out.println("Calculadora TCP ativa e aguardando conexao.");
		} catch (IOException e) {
			System.out.println("Erro criando o socket na porta:" + port);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que determina o inicio de atendimento a uma requisicao.
	 */
	public void run() {

		Socket clientSocket = null;
		Thread worker;
		while(true) {
			if ((socket != null) && (socket.isBound())) { //Verifica se o socket existe e que está vinculado a um par ip/porta
				try {
					worker = null;
					clientSocket = null;
					clientSocket = socket.accept(); // Aguarda um pedido de conexão
					if (clientSocket != null) {
						// existe um cliente para ser atendido, representado por "clientSocket"
						worker = new Thread(new CalculadoraWorkerTCP(clientSocket)); // Instancia um worker para atender ao cliente
						worker.start(); // Coloca o worker para trabalhar
					}
				} catch (SocketTimeoutException timeoutException) {
					System.out.println("Terminando por time-out");
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
					try {
						socket.close();
					} catch (IOException ec) {
						ec.printStackTrace();
					}
				}
			}
		}
	}
}
