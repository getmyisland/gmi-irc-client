package com.getmyisland.irc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		Client.stage = stage;

		// Load the server selection document
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/getmyisland/fx/ServerSelection.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);

		// stage.getIcons().add(new
		// Image("/de/morihofi/tafelquiz/images/AppTafelQuizUpdate.png"));
		stage.setTitle("gmi-irc-client");
		stage.setResizable(true);
		stage.setScene(scene);
		stage.show();
	}

	public static Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}