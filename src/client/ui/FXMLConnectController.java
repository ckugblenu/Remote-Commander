package client.ui;



import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import client.ConnectManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;



public class FXMLConnectController  implements Initializable {
	
	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( FXMLConnectController.class.getName() );
	
	ConnectManager connectManager = ConnectManager.getInstance();
	
    @FXML
    private AnchorPane connectWindow;
    
    @FXML
    private TextField serverName;

    @FXML
    private Button connectButton;

    // Method to switch between Command Window and Loop Window
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        connectManager.connect(serverName.getText());
    }

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

}
