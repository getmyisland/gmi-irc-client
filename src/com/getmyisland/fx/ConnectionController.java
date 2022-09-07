package com.getmyisland.fx;

import com.getmyisland.irc.ConnectionHandler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void onSend(ActionEvent event) {
    	ConnectionHandler.sendMessage(userInputTextField.getText());
    	userInputTextField.setText("");
    }
    
    @FXML
    private void initialize() {
    	
    }
    
    public TextArea getChatTextArea() {
    	return chatTextArea;
    }
}