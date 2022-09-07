package com.getmyisland.irc;

import java.io.IOException;

import com.getmyisland.fx.ConnectionController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Client extends Application {
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		Client.stage = stage;

		// Load the server selection document
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/getmyisland/fx/ServerSelectionWindow.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		// stage.getIcons().add(new
		// Image("/de/morihofi/tafelquiz/images/AppTafelQuizUpdate.png"));
		stage.setTitle("gmi-irc-client");
		stage.setResizable(true);
		stage.setScene(scene);
		stage.show();
	}

	public static void loadServerSelectionWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(
					Client.class.getClassLoader().getResource("com/getmyisland/fx/ServerSelectionWindow.fxml"));
			Pane root = loader.load();
			Client.stage.getScene().setRoot(root);
			Client.stage.setHeight(root.getPrefHeight());
			Client.stage.setWidth(root.getPrefWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionController loadConnectionWindow() {
		ConnectionController ircClientController = null;

		try {
			FXMLLoader loader = new FXMLLoader(
					Client.class.getClassLoader().getResource("com/getmyisland/fx/ConnectionWindow.fxml"));
			Pane root = loader.load();
			ircClientController = (ConnectionController) loader.getController();
			Client.stage.getScene().setRoot(root);
			Client.stage.setHeight(root.getPrefHeight());
			Client.stage.setWidth(root.getPrefWidth());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ircClientController;
	}

	public static void main(String[] args) {
		launch(args);
	}
}