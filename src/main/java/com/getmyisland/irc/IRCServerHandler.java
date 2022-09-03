package com.getmyisland.irc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class IRCServerHandler {
	private static File getIRCPropertyFile() {
		File root = new File("");
		File ircPropertyFile = new File(root.getAbsolutePath() + "/src/main/resources/ircProperties.xml");
		return ircPropertyFile;
	}

	public static List<String> getIRCServerNames() {

		File ircPropertyFile = getIRCPropertyFile();
		if (!ircPropertyFile.exists()) {
			System.err.println("ERROR: IRC Property file not found");
			return new ArrayList<String>();
		}

		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try (InputStream is = new ByteArrayInputStream(
				Charset.forName("UTF-16").encode(ircPropertyFile.getAbsolutePath()).array());) {

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			// read from a project's resources folder
			Document doc = db.parse(is);

			System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
			System.out.println("------");

			List<String> serverNameList = new ArrayList<String>();
			
			// If there are any child nodes
			if (doc.hasChildNodes()) {
				NodeList childList = doc.getChildNodes();
				
				// Loop through all child nodes
				for(int i = 0; i < childList.getLength(); i++) {
					Node tempNode = childList.item(i);

			          // Making sure its an element node
			          if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

			              // Get node name and value
			              System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
			              System.out.println("Node Value =" + tempNode.getTextContent());

			              if (tempNode.hasAttributes()) {

			                  // Get attributes names and values
			                  NamedNodeMap nodeMap = tempNode.getAttributes();
			                  for (int j = 0; j < nodeMap.getLength(); j++) {
			                      Node node = nodeMap.item(j);
			                      System.out.println("attr name : " + node.getNodeName());
			                      System.out.println("attr value : " + node.getNodeValue());
			                  }

			              }

			              System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
			          }
				}
			}

			return serverNameList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<String>();
	}
}