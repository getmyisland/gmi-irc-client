package com.getmyisland.fx;

import java.util.regex.Pattern;

import com.getmyisland.irc.Client;
import com.getmyisland.irc.ConnectionHandler;
import com.getmyisland.irc.ConnectionProperties;
import com.getmyisland.irc.IrcServer;
import com.getmyisland.irc.IrcServerHandler;

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
		if (loginTextField.getText().equals("")) {
			statusContentLabel.setText("Login field can't be empty.");
			return;
		}

		if (nicknameTextField.getText().equals("")) {
			statusContentLabel.setText("Nickname field can't be empty.");
			return;
		}

		if (!Pattern.matches("^((?!-))(xn--)?[a-z0-9][a-z0-9-_]{0,61}[a-z0-9]{0,1}\\.(xn--)?([a-z0-9\\-]{1,61}|[a-z0-9-]{1,30}\\.[a-z]{2,})$", serverURLTextField.getText())) {
			statusContentLabel.setText("Server URL is not a valid URL.");
			return;
		}

		try {
			// Try parsing the port
			Integer.parseInt(portTextField.getText());
		} catch (NumberFormatException e) {
			statusContentLabel.setText("Port must be a positive non-decimal number.");
			return;
		}

		// Set the properties
		ConnectionProperties.LOGIN = loginTextField.getText();
		ConnectionProperties.NICKNAME = nicknameTextField.getText();
		ConnectionProperties.URL = serverURLTextField.getText();
		ConnectionProperties.PORT = Integer.parseInt(portTextField.getText());
		
		// Create the window
		Client.loadConnectionWindow();
	}

	@FXML
	private void initialize() {
		// Clear status text
		statusContentLabel.setText("");

		// Populate the server list view
		for (IrcServer server : IrcServerHandler.getIRCServers()) {
			serverListView.getItems().add(server.getName());
		}

		// Add a listener to the server list view to detect when a value is selected
		serverListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				IrcServer ircServer = IrcServerHandler.getIRCServerByName(newValue);

				if (ircServer != null) {
					// Set content of url text field
					serverURLTextField.setText(ircServer.getUrl());

					// Set content of port text field
					portTextField.setText(Integer.toString(ircServer.getPort()));
				}
			}
		});
		
		connectButton.setDefaultButton(true);
	}
}