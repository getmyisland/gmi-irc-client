package com.getmyisland.fx;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.getmyisland.irc.Client;
import com.getmyisland.irc.ConnectionHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ConnectionController {
	@FXML
    private ListView<?> channelListView;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextField userInputTextField;

    @FXML
    private Button sendButton;
    
    @FXML
    void onDisconnectMenuClicked(ActionEvent event) {
    	ConnectionHandler.disconnect();
    	Client.loadServerSelectionWindow();
    }
    
    @FXML
    void onAboutMenuClicked(ActionEvent event) {
    	if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
    	    try {
				Desktop.getDesktop().browse(new URI("http://www.getmyisland.com"));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void onGitHubMenuClicked(ActionEvent event) {
    	if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
    	    try {
				Desktop.getDesktop().browse(new URI("http://www.github.com/getmyisland/gmi-irc-client"));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
    	}
    }
    
    @FXML
    void onSendButtonClicked(ActionEvent event) {
    	ConnectionHandler.sendMessage(userInputTextField.getText());
    	userInputTextField.setText("");
    }
    
    @FXML
    void initialize() {
    	sendButton.setDefaultButton(true);
    }
    
    public TextArea getChatTextArea() {
    	return chatTextArea;
    }
}