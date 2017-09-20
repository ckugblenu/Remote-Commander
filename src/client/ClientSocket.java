package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientSocket {
	
	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( ClientSocket.class.getName() );

	// define a constant used as size of buffer
	static final int PORT = 9999;

	String serverAddress;
	Socket serverSocket = null;
	PrintWriter output;
	BufferedReader input;
	
	public ClientSocket(String serverAddress) {

		this.serverAddress = serverAddress;

	}

	public void connect() throws IOException{
		
	    try {

	    	serverSocket = new Socket(serverAddress, PORT);
	    	output = new PrintWriter(serverSocket.getOutputStream(), true);
	    	input = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
	    	LOGGER.info("Client Connected Successfully");
            
            
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverAddress);
            LOGGER.log(Level.SEVERE, e.toString(), e);
            LOGGER.severe("Don't know about host: " + serverAddress);
            System.exit(1);
        } catch (IOException e) {
        	LOGGER.log(Level.SEVERE, e.toString(), e);
            LOGGER.severe("Couldn't get I/O for "+ "the connection to: " + serverAddress+ " at the port: "+PORT);
            System.exit(1);
        }
	}
	
	public void disconnect() throws IOException{
		
		output.close();
		input.close();
		serverSocket.close();
		LOGGER.info("Client Disconnect Successfully");
		
	}
	
	public void sendCommand(String command) {
			output.println(command);
			LOGGER.info("Command sent successfully");
			
	}
	
	public void sendNumber(String number) {
		// Using "fibonacci" as a keyword for processing fibonacci numbers
		output.println("fibonacci");
		output.println(number);
		LOGGER.info("Number sent successfully");
	}

}
