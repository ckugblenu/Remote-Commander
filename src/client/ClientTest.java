package client;

import java.io.IOException;

public class ClientTest {

	
	
	public static void main(String args[]) throws IOException, InterruptedException {
		
		ClientSocket clientSocket = new ClientSocket("localhost");
		clientSocket.connect();
	
		clientSocket.sendCommand("ls");
		
		Thread.sleep(10000);
		clientSocket.disconnect();

	}
}
