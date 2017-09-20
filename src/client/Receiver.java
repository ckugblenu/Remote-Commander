package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ui.FXMLDocumentController;

public class Receiver extends Thread {

	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger(Receiver.class.getName());

	ClientSocket clientSocket;
	FXMLDocumentController controller;

	public Receiver(ClientSocket clientSocket, FXMLDocumentController controller) {
		this.clientSocket = clientSocket;
		this.controller = controller;
	}

	public void run() {
		try {

			String line = null;
			
			LOGGER.info("Thread to listen for responses started");
			while ((line = clientSocket.input.readLine()) != null) {
				
				// Check if a terminal command of a fibonacci request
				String f_line;
				if (line.equals("fibonacci")) {
					f_line = clientSocket.input.readLine();
					javafx.application.Platform.runLater(() -> controller.fibonacciResult.setText(f_line));
				} else {
					f_line = line;
					javafx.application.Platform.runLater(() -> controller.resultText.appendText(f_line + "\n"));
				}
				
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.warning("Socket closed while still running response listen thread");
		}

	}

}
