package com.getmyisland.irc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IRCServerHandler {
	private static File getIRCPropertyFile() {
		File root = new File("");
		File ircPropertyFile = new File(root.getAbsolutePath() + "/resources/ircServerProperties.csv");
		return ircPropertyFile;
	}
	
	public static List<IRCServer> getIRCServers() {
		File ircPropertyFile = getIRCPropertyFile();
		
		// Making sure the property file exists
		if (!ircPropertyFile.exists()) {
			System.err.println("ERROR: IRC Server Property file not found");
			return new ArrayList<IRCServer>();
		}

		List<List<String>> serverStringList = new ArrayList<>();
		// Reading the property file
		try (BufferedReader br = new BufferedReader(new FileReader(ircPropertyFile.getAbsolutePath()))) {
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
}