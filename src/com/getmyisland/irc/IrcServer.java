package com.getmyisland.irc;

public class IrcServer {
	/** Name of the IRC Server */
	private final String name;
	
	/** URL of the IRC server */
	private final String url;
	
	/** Port the IRC server uses */
	private final int port;
	
	/**
	 * Creates new IRC Server object
	 * 
	 * @param name
	 * @param url
	 * @param port
	 */
	public IrcServer(String name, String url, int port) {
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