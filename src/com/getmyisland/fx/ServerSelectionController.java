package com.getmyisland.fx;

import com.getmyisland.irc.IRCConnectionHandler;
import com.getmyisland.irc.IRCServer;
import com.getmyisland.irc.IRCServerHandler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServerSelectionController {
	@FXML
	private Button connectButton;

	@FXML
	private TextField loginTextField;

	@FXML
	private TextField nicknameTextField;

	@FXML
	private TextField serverURLTextField;

	@FXML
	private TextField portTextField;

	@FXML
	private ListView<String> serverListView;

	@FXML
	private Label statusContentLabel;

	@FXML
	void onConnectButtonClicked(ActionEvent event) {
		// Filter wrong configurations
		if(loginTextField.getText().equals("")) {
			statusContentLabel.setText("Login field can't be empty.");
			return;
		}
		
		if(nicknameTextField.getText().equals("")) {
			statusContentLabel.setText("Nickname field can't be empty.");
			return;
		}
		
		// TODO with Regex
		if(!serverURLTextField.getText().contains(".")) {
			statusContentLabel.setText("Server URL is not a valid URL.");
			return;
		}
		
		try {
			Integer.parseInt(portTextField.getText());
		} catch (NumberFormatException e) {
			statusContentLabel.setText("Port must be a positive non-decimal number.");
			return;
		}

		// Call connection function
		IRCConnectionHandler.connect(loginTextField.getText(), nicknameTextField.getText(),
				serverURLTextField.getText(), portTextField.getText());
	}

	@FXML
	private void initialize() {
		// Clear status text
		statusContentLabel.setText("");
		
		// Populate the server list view
		for (IRCServer server : IRCServerHandler.getIRCServers()) {
			serverListView.getItems().add(server.getName());
		}

		// Add a listener to the server list view to detect when a value is selected
		serverListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				IRCServer ircServer = IRCServerHandler.getIRCServerByName(newValue);
				
				if (ircServer != null) {
					// Set content of url text field
					serverURLTextField.setText(ircServer.getUrl());

					// Set content of port text field
					portTextField.setText(Integer.toString(ircServer.getPort()));
				}
			}
		});
	}
}