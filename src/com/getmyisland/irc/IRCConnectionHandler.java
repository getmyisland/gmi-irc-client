package com.getmyisland.irc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.getmyisland.fx.IRCClientController;

import javafx.scene.control.TextArea;

public class IRCConnectionHandler {
	private static Socket socket = null;
	private static BufferedWriter bw = null;
	private static BufferedReader br = null;

	public static void connect(final IRCClientController ircClientController, final String login, final String nickname, final String url, final int port) {
		try {
			// Places a socket on the IRC server to send bytes
			socket = new Socket(url, port);
			
			System.out.println("Connection to " + url + " established");

			// Connect the socket to the OutputStreamWriter to generate the bytes to
			// characters
			// then connect the OutputStreamWriter to the BufferedWriter to save the
			// characters
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			// Connect the socket to the InputStreamReader to generate the bytes and send to
			// characters
			// then connect the InputStreamReader to the BufferedReader
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			bw.write("NICK" + nickname + "\n\r");
			// bw.write ("USER test12 hpxn.net holmes.freenode.net :HPXN IRC TEST\n\r" );
			// bw.write ("JOIN #hpxntest\n\r");
			bw.flush();
			String currLine = null;
			TextArea chatTextArea = ircClientController.getChatTextArea();
			
			while ((currLine = br.readLine()) != null) {
				System.out.println(currLine);
				chatTextArea.appendText(currLine);
				chatTextArea.appendText("\n"); // Nextline
				// chatTextArea.setCaretPosition(chatTextArea.getText().length()); // Autoscroll down
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}