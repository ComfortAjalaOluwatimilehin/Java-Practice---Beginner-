package com.learnjava.beginner;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;


public class RssFeedGenerator  {
	
	String validuri;
	JFrame frame;
	 JButton button;
	
	 class RssItem implements ActionListener{
			String title;
			String link;
			String description;
			RssItem(String title, String link, String description){
				this.title = title;
				this.link = link;
				this.description = description;
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().browse(new URI(this.link));
						
					} catch (IOException | URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally{
						System.exit(0);
					}
				}
			}
			
			
		}
	RssFeedGenerator(){
		String uri = this.getUserInput();
		boolean isvalid = this.isValidUrl(uri);
		if(isvalid == false) {
			System.out.println("Invalid URL");
			System.exit(0);
		}
		this.validuri = this.cleanUrl(uri);
	
		String defaultRssXmlFeedUri = this.validuri + "/feed";

		Document doc = this.parseXMLContent(defaultRssXmlFeedUri);
		if(doc != null ) {
			this.setupGUI();
			System.out.println("content  "+ doc.getDocumentElement().getNodeName());
			NodeList nodes = this.getItemsInRssDocument(doc);
			if(nodes != null) {
				
				ArrayList<RssItem> rssitems = this.getRssItemsFromDoc(nodes);
				if(rssitems.size() > 0) {
					System.out.println("GUI");
					this.generateRssFeedUI(rssitems);
				}else {
					System.out.println("No RssItem found");
					System.exit(0);
				}
				
			}else {
				System.out.println("Elements with Name 'item' do not exist in document");	
				System.exit(0);
			}
		}else {
			// if default xml feed does not exists, return 
			System.out.println("Default XML file does not exist");
			System.exit(0);
		}
		
	}
	
	private static String cleanUrl(String url) {
		String cleanedurl = url;
		if(url.charAt(url.length() - 1) == '/') {
			cleanedurl = url.substring(0, url.length() - 1);
		}
		return cleanedurl;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new RssFeedGenerator();
	}
	
	private  String getUserInput() {
		// gets user input and returns the value
		System.out.print("Type in the website url and press Enter:   ");
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		return input;
	}
	
	private static boolean isValidUrl(String url) {
		System.out.println("Validating url");
		try {
			new URL(url);
			return true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	private static String fetch(String url) throws MalformedURLException {
		
		URL u = new URL(url);
		try {
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("GET");
			InputStream inputstream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputstream);
			BufferedReader bufferedreader = new BufferedReader(reader);
			String lines = bufferedreader.lines().collect(Collectors.joining(System.lineSeparator()));
			return lines;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		
	}
	private static Document parseXMLContent(String url) {
		System.out.println("Parsing XML @ " + url);
		DocumentBuilderFactory  dbBuilderfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbBuilder;
		try {
			dbBuilder = dbBuilderfactory.newDocumentBuilder();
			Document doc;
			try {
				doc = dbBuilder.parse(url);
				doc.getDocumentElement().normalize();
				return doc;
			} catch (SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
		
	}
	private static NodeList getItemsInRssDocument(Document doc) {
		NodeList nodes = doc.getElementsByTagName("item");
		return nodes.getLength() > 0 ? nodes: null;
	}
	
	
	private  ArrayList<RssItem> getRssItemsFromDoc(NodeList nodes){
		ArrayList<RssItem> rssitems = new ArrayList();
		for(int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				String title = element.getElementsByTagName("title").item(0).getTextContent();
				String link = element.getElementsByTagName("link").item(0).getTextContent();
				String description = element.getElementsByTagName("description").item(0).getTextContent();
				RssItem r = new RssFeedGenerator.RssItem(title, link ,description);
				rssitems.add(r);
			}
		}
		return rssitems;
	}
	
	private void setupGUI() {
		 this.frame = new JFrame("My First GUI");
	       this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       this.frame.setSize(300,300);
	       FlowLayout fl = new FlowLayout();
			 this.frame.setLayout(fl);
	}
	
	private  void generateRssFeedUI(ArrayList<RssItem> rssitems) {
		 
	      for(int i=0; i < rssitems.size(); i++) {
	    	  Panel pn = new Panel();
	    	  JLabel l = new JLabel(rssitems.get(i).title);
	    	  JButton b = new JButton("Read More");
	    	  pn.add(l);
	    	  pn.add(b);
	    	  this.frame.add(pn);
	    	  b.addActionListener(rssitems.get(i));
	    	 
	      }
	       this.frame.setVisible(true);
	}
	
}
