package com.getmyisland.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.getmyisland.fx.ConnectionController;

import javafx.scene.control.TextArea;

public class ConnectionHandler {
	private static Socket socket = null;
	private static BufferedWriter writer = null;
	private static BufferedReader reader = null;

	public static void connect(final ConnectionController ircClientController, final String login,
			final String nickname, final String url, final int port) {
		try {
			// Places a socket on the IRC server to send bytes
			socket = new Socket(url, port);

			System.out.println("Connection to " + url + " established");

			// Connect the socket to the OutputStreamWriter to generate the bytes to
			// characters
			// then connect the OutputStreamWriter to the BufferedWriter to save the
			// characters
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Connect the socket to the InputStreamReader to generate the bytes and send to
			// characters
			// then connect the InputStreamReader to the BufferedReader
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			writer.write("NICK " + nickname + "\n\r");
			writer.write("USER " + login + " 8 * : gmi-irc-client\r\n");
			
			String currLine = null;
			TextArea chatTextArea = ircClientController.getChatTextArea();
			while ((currLine = reader.readLine()) != null) {
				// System.out.println(currLine);
				chatTextArea.appendText(currLine);
				chatTextArea.appendText("\n"); // Nextline
				// chatTextArea.setCaretPosition(chatTextArea.getText().length()); // Autoscroll
				// down
			}
		} catch (Exception ex) {
			socket = null;
			writer = null;
			reader = null;
			ex.printStackTrace();
		}
		
		// In case connection terminates load the server selection
		Client.loadServerSelectionWindow();
	}

	public static void sendMessage(final String messageContent) {
		if (socket == null || writer == null) {
			System.err.println("No connection currently established");
			return;
		}

		try {
			writer.write(messageContent + "\n\r");
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}