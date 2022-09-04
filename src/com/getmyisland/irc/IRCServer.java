package com.getmyisland.irc;

public class IRCServer {
	/** Name of the IRC Server */
	private String name;
	
	/** URL of the server */
	private String url;
	
	/** Port the server uses */
	private int port;
	
	/**
	 * Creates new IRC Server object
	 * 
	 * @param name
	 * @param url
	 * @param port
	 */
	public IRCServer(String name, String url, int port) {
		this.name = name;
		this.url = url;
		this.port = port;
	}

	/**
	 * Returns {@link #name}
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns {@link #url}
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * Returns {@link #port}
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}
}