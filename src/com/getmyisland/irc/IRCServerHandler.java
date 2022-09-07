package com.getmyisland.irc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IRCServerHandler {
	private static InputStream getIRCPropertyFileInputStream() {
		return IRCServerHandler.class.getClassLoader().getResourceAsStream("ircServerProperties.csv");
	}
	
	public static List<IRCServer> getIRCServers() {
		List<List<String>> serverStringList = new ArrayList<>();
		// Reading the property file
		try (BufferedReader br = new BufferedReader(new InputStreamReader(getIRCPropertyFileInputStream(), "UTF-8"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(",");
		        // Adding all values to a list of strings
		        serverStringList.add(Arrays.asList(values));
		    }
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<IRCServer>();
		}

		List<IRCServer> serverList = new ArrayList<>();
		// Creating the irc server list
		for(List<String> server : serverStringList) {
			if(server.size() < 3) {
				System.out.println(server);
				continue;
			}
			
			// First value is name, second is url and third is port
			serverList.add(new IRCServer(server.get(0), server.get(1), Integer.parseInt(server.get(2))));
		}
		return serverList;
	}
	
	public static IRCServer getIRCServerByName(final String name) {
		List<IRCServer> serverList = getIRCServers();
		
		// Go through each server and check the name
		for(IRCServer server : serverList) {
			if(server.getName().equals(name)) {
				return server;
			}
		}
		
		return null;
	}
}