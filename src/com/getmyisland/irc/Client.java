package com.getmyisland.irc;

import java.io.IOException;

import com.getmyisland.fx.ConnectionController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {
	private static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Client.stage = primaryStage;

		// Set the stage values
		primaryStage.setTitle("gmi-irc");
		primaryStage.setResizable(true);

		loadServerSelectionWindow();

		primaryStage.show();
	}

	public static void loadServerSelectionWindow() {
		try {
			System.out.println("Loading server selection window...");

			// Load a scene with fxml loader
			FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("/ServerSelectionWindow.fxml"));
			Scene scene = new Scene((Parent) fxmlLoader.load());

			// Set the loaded scene
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadConnectionWindow() {
		try {
			System.out.println("Loading connection window...");

			// Load a scene with fxml loader
			FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("/ConnectionWindow.fxml"));
			Scene scene = new Scene((Parent) fxmlLoader.load());

			// Set the loaded scene
			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}