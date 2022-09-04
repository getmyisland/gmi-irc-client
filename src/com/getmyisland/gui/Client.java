package com.getmyisland.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.getmyisland.irc.IRCServer;
import com.getmyisland.irc.IRCServerHandler;

public class Client {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		
		for(IRCServer server : IRCServerHandler.getIRCServers()) {
			System.out.println(server.getName());
		}
		
		frame.setPreferredSize(new Dimension(1280, 720));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("gmi-irc-client");
		frame.pack();
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
}
