package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import processing.Fibonacci;
import processing.SystemCommandExecutor;

public class Worker extends Thread {

	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( Worker.class.getName() );
		
	
	Socket clientSocket;
	int clientID = -1;
	boolean running = true;

	private BufferedReader input;
	private PrintWriter output;
	private Fibonacci loop;

	public Worker() {
		super("Client Worker Thread");
	}

	public Worker(Socket s, int i) throws IOException {
		clientSocket = s;
		clientID = i;

	}

	@Override
	public void run() {

		// Obtain the input stream and the output stream for the socket
		// A good practice is to encapsulate them with a BufferedReader
		// and a PrintWriter as shown below.

		// Print out details of this connection
		LOGGER.info(
				"Accepted Client : ID - " + clientID + " : Address - " + clientSocket.getInetAddress().getHostName());

		try {

			input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			output = new PrintWriter(clientSocket.getOutputStream(), true);
			
			String line = null;
			String response = null;
			LOGGER.info("Waiting for commands");
			while ((line = input.readLine()) != null) {
				if (line.equals("fibonacci")) {
					
					line = input.readLine();
					loop = new Fibonacci(line);
					output.println("fibonacci");
					output.println(loop.fibonacciLoop());
					
				} else {
					// Log the exact command run
					LOGGER.info("Command Run: " + line);
					response = executeCommand(line);
					output.println(response);
				}
			
			}
			
			
			output.close();
			input.close();
			
		
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
		} finally {
			// TODO: handle finally clause
		
			
		}
	}

	private String executeCommand(String command) throws IOException, InterruptedException {
		
		
	            List<String> commands = new ArrayList<String>();
	            commands.add("/bin/bash");
	            commands.add("-c");
	            commands.add(command);
	            
	            // execute the command
	            SystemCommandExecutor commandExecutor = new SystemCommandExecutor(commands);
	            int result = commandExecutor.executeCommand();

	            // get the stdout and stderr from the command that was run
	            StringBuilder stdout = commandExecutor.getStandardOutputFromCommand();
	            
	            // To showcase success
	            LOGGER.info("Command Executed Successfully");
	       	
	            return stdout.toString();
	    }
	
	
}
	


