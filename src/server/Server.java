package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( Server.class.getName() );
	ServerSocket remoteServerSocket;
	boolean ServerOn = true;
	static final int PORT = 9999;
	static int id = 0;
	
	public Server() {
		try {
			 remoteServerSocket = new ServerSocket(PORT);
			 
			 // Log information about server
			 LOGGER.info("Server created at port " + PORT);
		} catch (IOException ioe) {
			
			// Log as severe socket instantiation
			 LOGGER.log( Level.SEVERE, ioe.toString(), ioe );
			 LOGGER.severe("Could not create server socket on port 9999. Quitting.");
	         System.exit(-1); 
		}
		
		 // Successfully created Server Socket. Now wait for connections. 
        while(ServerOn) {
        	
        	try {
        		// Accept incoming connections. 
        		// accept() will block until a client connects to the server. 
                // If execution reaches this point, then it means that a client 
                // socket has been accepted. 
        		// Log information about multithreading
   			 	LOGGER.info("Ready to accept Multiple Connections at port " + PORT);
        		Socket clientSocket = remoteServerSocket.accept();
        		
        		
                
        		// For each client, we will start a service thread to 
                // service the client requests. This is to demonstrate a 
                // Multi-Threaded server. Starting a thread also lets our 
                // MultiThreadedSocketServer accept multiple connections simultaneously. 

                // Start a Service thread 

        		
                Worker workerThread = new Worker(clientSocket, id++);
                workerThread.start();


			} catch (IOException ioe) {
				// TODO: handle exception
			}
        }
        
        try 
        { 
            remoteServerSocket.close(); 
            LOGGER.info("Server Stopped");
      
        } 
        catch(Exception ioe) 
        { 
            System.out.println("Problem stopping server socket"); 
       	 	LOGGER.severe("Problem stopping server socket");
            System.exit(-1); 
        } 
	}
	
	public static void main(String args[]) {
		
		new Server();
	}
	
	

}
