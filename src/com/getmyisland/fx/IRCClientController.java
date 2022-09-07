package com.getmyisland.fx;

import com.getmyisland.irc.Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IRCClientController {
	@FXML
    private ListView<?> channelListView;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextField userInputTextField;

    @FXML
    void onSend(ActionEvent event) {

    }
    
    @FXML
    private void initialize() {
    	Client.getStage().hide();
        Client.getStage().show();
    }
    
    public TextArea getChatTextArea() {
    	return chatTextArea;
    }
}