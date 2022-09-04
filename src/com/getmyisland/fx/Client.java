package com.getmyisland.fx;

import com.getmyisland.irc.IRCServer;
import com.getmyisland.irc.IRCServerHandler;

public class Client {
	public static void main(String[] args) {

		for(IRCServer server : IRCServerHandler.getIRCServers()) {
			System.out.println(server.getName());
		}
		
	}
}
