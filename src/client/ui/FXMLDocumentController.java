package client.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import client.ConnectManager;
import client.Receiver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class FXMLDocumentController implements Initializable {

	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( FXMLDocumentController.class.getName() );
	
	ConnectManager connectManager = ConnectManager.getInstance();
	Receiver listenFromServer;
	String response = null;
	
	@FXML
    private ImageView terminalTab, loopTab;

    @FXML
    private AnchorPane commandWindow, loopWindow, mainWindow;

    @FXML
    private Button runButton, clearButton, launchLoopButton, disconnectButton;

    @FXML
    private TextField commandText, loopCountNumber;

    @FXML
    public TextArea resultText;
    
    @FXML 
    public Label fibonacciResult;
    

    @FXML
    // Method to switch between Command Window and Loop Window
    private void handleButtonAction(MouseEvent event) {

    	if (event.getTarget() == terminalTab) {
            commandWindow.setVisible(true);
            loopWindow.setVisible(false);
            // Name the client functionality currently running
            LOGGER.info("On Terminal Interface");
        } else {
            commandWindow.setVisible(false);
            
            loopWindow.setVisible(true);
         // Name the client functionality currently running
            LOGGER.info("On Fibonacci Interface");
        }
    }

    @FXML
    private void executeCommand(ActionEvent event) throws IOException {
     	resultText.clear();
    	connectManager.sendCommand(commandText.getText());
    }

    @FXML
    private void handleDisconnect(ActionEvent event) {
    	
    	connectManager.disconnect();
    }
    @FXML
    // Clear Command Prompt
    private void clear(ActionEvent event) {
    	resultText.clear();
    	commandText.clear();
    }
    
    @FXML
    private void launchLoop(ActionEvent event) {
    	connectManager.sendNumber(loopCountNumber.getText());
    }

 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Handle Validation for only numbers
       
		listenFromServer = new Receiver(connectManager.clientSocket, this);
		listenFromServer.start();
	

       
       
    }

}
