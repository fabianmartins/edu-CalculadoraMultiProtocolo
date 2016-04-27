package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe para facilitar I/O pela console.
 * @author fabianmartins
 *
 */
public class Console {
	
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void print(Object msg) {
		System.out.print(msg);
	}
	
	public static String readLine() {
		try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static String readLine(Object message) {
		Console.print(message);
		return Console.readLine();
	}
	
	public static void println(Object value) {
		System.out.println(value);
	}
	
}
