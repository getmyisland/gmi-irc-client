package com.getmyisland.fx;

import java.io.IOException;
import java.util.regex.Pattern;

import com.getmyisland.irc.Client;
import com.getmyisland.irc.ConnectionHandler;
import com.getmyisland.irc.IRCServer;
import com.getmyisland.irc.IRCServerHandler;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
			Integer.parseInt(portTextField.getText());
		} catch (NumberFormatException e) {
			statusContentLabel.setText("Port must be a positive non-decimal number.");
			return;
		}

		final ConnectionController[] classArr = new ConnectionController[1];
		ConnectionController ircClientController = null;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/getmyisland/fx/ConnectionWindow.fxml"));
			Pane root = loader.load();
			ircClientController = (ConnectionController) loader.getController();
			classArr[0] = ircClientController; 
			Client.getStage().getScene().setRoot(root);
			Client.getStage().setHeight(root.getPrefHeight());
			Client.getStage().setWidth(root.getPrefWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}

		Thread connectionThread = new Thread() {
			public void run() {
				// Call connection function
				ConnectionHandler.connect(classArr[0], loginTextField.getText(),
						nicknameTextField.getText(), serverURLTextField.getText(),
						Integer.parseInt(portTextField.getText()));
			}
		};

		connectionThread.start();
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

		Client.getStage().hide();
		Client.getStage().show();
	}
}