package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.ui.FXMLDocumentController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConnectManager extends Application {
	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( ConnectManager.class.getName() );
	
	public ClientSocket clientSocket;
	public Receiver listenFromServer;
	static FXMLDocumentController controller;
	private Stage stage;
	private static ConnectManager instance;

	public ConnectManager() {
		instance = this;
	}

	public static ConnectManager getInstance() {

		return instance;

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			 
			stage = primaryStage;
			// gotoLogin();
			connectView();
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(ConnectManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private Parent replaceSceneContent(String fxml) throws Exception {
		Parent root = (AnchorPane) FXMLLoader.load(ConnectManager.class.getResource(fxml), null,
				new JavaFXBuilderFactory());
		Scene scene = stage.getScene();
		
		if (scene == null) {
			scene = new Scene(root);
			stage.setScene(scene);
		} else {
			stage.getScene().setRoot(root);
		}
		stage.sizeToScene();
		stage.setTitle("Remote Commander");
		stage.setResizable(false);
		return root;
	}
	
	

	private void connectView() {
		try {
			replaceSceneContent("/client/ui/FXMLConnect.fxml");
		} catch (Exception ex) {
			Logger.getLogger(ConnectManager.class.getName()).log(Level.SEVERE, ex.toString(), ex);
		}
	}

	private void mainView() {
		try {
			replaceSceneContent("/client/ui/FXMLDocument.fxml");
		} catch (Exception ex) {
			Logger.getLogger(ConnectManager.class.getName()).log(Level.SEVERE, ex.toString(), ex);
		}
	}
	
	public void connect(String hostname) {
		clientSocket = new ClientSocket(hostname);
    	try {
			
    		clientSocket.connect();
    	   	mainView();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.severe(e1.toString());
		}
                      
	}
	

	public void disconnect() {
		try {
			clientSocket.disconnect();
        	connectView();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.log(Level.SEVERE, e1.toString(), e1);
		}
    	
    }
	
	public void sendCommand(String command) throws IOException {
	
		clientSocket.sendCommand(command);
	}
	
	public void sendNumber(String number) {
		clientSocket.sendNumber(number);
	}
}


