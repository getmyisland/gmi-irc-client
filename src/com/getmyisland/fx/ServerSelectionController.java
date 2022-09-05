package com.getmyisland.fx;

import com.getmyisland.irc.IRCServer;
import com.getmyisland.irc.IRCServerHandler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ServerSelectionController {
	@FXML
	private Button connectButton;

	@FXML
	private TextField portTextField;

	@FXML
	private ListView<String> serverListView;

	@FXML
	private TextField serverURLTextField;

	@FXML
	void onConnectButtonClicked(ActionEvent event) {
		// Connect using the content of the url text field and the content of the port
	}

	@FXML
	private void initialize() {
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