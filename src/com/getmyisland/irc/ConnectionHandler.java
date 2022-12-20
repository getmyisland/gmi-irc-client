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

	private static boolean connectionActive = false;

	private static Thread connectionThread = null;

	private static Socket socket = null;
	private static BufferedWriter writer = null;
	private static BufferedReader reader = null;

	public static void connect(final ConnectionController controller) {
		if (connectionActive) {
			return;
		}

		connectionThread = new Thread() {
			public void run() {
				try {
					// Places a socket on the IRC server to send bytes
					socket = new Socket(ConnectionProperties.URL, ConnectionProperties.PORT);

					System.out.println("Connection to " + ConnectionProperties.URL + " established");

					// Connect the socket to the OutputStreamWriter to generate the bytes to
					// characters
					// then connect the OutputStreamWriter to the BufferedWriter to save the
					// characters
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

					// Connect the socket to the InputStreamReader to generate the bytes and send to
					// characters
					// then connect the InputStreamReader to the BufferedReader
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

					writer.write("NICK " + ConnectionProperties.NICKNAME + "\n\r");
					writer.write("USER " + ConnectionProperties.LOGIN + " 8 * : gmi-irc-client\r\n");

					connectionActive = true;

					processMessages(controller);
				} catch (Exception ex) {
					ex.printStackTrace();
					disconnect();
				}
			}
		};

		connectionThread.start();
	}

	private static void processMessages(ConnectionController controller) {
		if (!connectionActive) {
			return;
		}

		try {
			String currentLine = null;
			TextArea chatTextArea = controller.getChatTextArea();
			while (connectionActive) {
				currentLine = reader.readLine();

				if (currentLine == null) {
					return;
				}

				// System.out.println(currLine);
				chatTextArea.appendText(currentLine);
				chatTextArea.appendText("\n"); // Nextline
				// chatTextArea.setCaretPosition(chatTextArea.getText().length()); // Autoscroll
				// down
			}
		} catch (IOException e) {
			disconnect();
			e.printStackTrace();
		}
	}

	public static void sendMessage(final String messageContent) {
		if (!connectionActive) {
			return;
		}

		try {
			writer.write(messageContent); // + "\n\r");
			writer.flush();
		} catch (IOException e) {
			disconnect();
			e.printStackTrace();
		}
	}

	public static void disconnect() {
		if (!connectionActive) {
			return;
		}
		
		connectionActive = false;

		try {
			if (socket != null) {
				socket.close();
			}
			
			if(connectionThread != null) {
				connectionThread.interrupt();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		socket = null;
		writer = null;
		reader = null;
		connectionThread = null;
	}
}