package com.alexokoli.xmlmusic;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.ProcessingInstruction;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

class Artist {
  public String name, genre, album;

  public Artist(String name, String genre, String album) {
    this.name = name;
    this.genre = genre;
    this.album = album;
  }
}

public class ArtistEditor extends Frame implements ActionListener {
	// Set album delimiter
	char delimeter = '*';
	
  // declare the user interface
  Button ok = new Button("ok");
  Button delete = new Button("delete");
  Button clear = new Button("clear");
  Button save = new Button("save");
  Button quit = new Button("quit");
  Button browserOpen = new Button("browserOpen");
  Button addArtist = new Button("addArtist");
  
  TextField name = new TextField(20);
  TextField genre = new TextField(20);
//  TextArea album = new TextArea(0, 0);
  TextField album = new TextField(40);
  Panel artistpanel = new Panel(new GridLayout(0, 1));

  static String xmlFile = "artists.xml";
  static String xsdFile = "artists.xsd";
  static String xslFile = "artists.xsl";
  static String outputFileName = "alexdiscography.html";

  Vector<Artist> artistsVector;
  int current = -1;
  Namespace b = Namespace.getNamespace("http://alexdiscography.org");


//  public static void main(String[] args) { new ArtistEditor(args[0]); }
  public static void main(String[] args) { new ArtistEditor(xmlFile); }

  Vector<Artist> doc2vector(Document d) {
    Vector<Artist> v = new Vector<Artist>();
    Iterator i = d.getRootElement().getChildren("artist", b).iterator();
    while (i.hasNext()) {
      Element e = (Element)i.next();
      List<Element> albumsRaw = e.getChildren("album", b);
      
      String albumsStr = "";
      
      for (Element album : albumsRaw){
    	  albumsStr += album.getText() + " " + delimeter + " ";
      }
      
      // Remove the delimeter that trails the albumsStr sequence, as it adds 
      // an extra li element whenever the save button is clicked
      try {
	      if (albumsStr.charAt(albumsStr.length() - 2) == delimeter){
	    	 albumsStr = (albumsStr.subSequence(0, albumsStr.length() - 3)).toString();
	      }
      } catch (Exception err) {
    	  System.out.println(albumsStr);
      }
      
      
      
      
      Element logo = e.getChild("logo", b);
      String uri;
      if (logo==null) 
        uri = ""; 
      else 
        uri = logo.getAttributeValue("uri");
      Artist c = new Artist(e.getChildText("name", b),
                        e.getChildText("genre", b),
                        // album,
                        albumsStr);
      v.add(c);
    }
    return v;
  }

  Document vector2doc() {
	  Document doc = new Document();
	  
    Element artists = new Element("artists", b);
    for (int i=0; i < artistsVector.size(); i++) {
      Artist a = artistsVector.elementAt(i);
      if (a!=null) {
        Element artist = new Element("artist", b);
        Element name = new Element("name", b);
        name.addContent(a.name);
        artist.addContent(name);
        Element genre = new Element("genre", b);
        genre.addContent(a.genre);
        artist.addContent(genre);
        if (!a.album.equals("")) {
          String[] albums = a.album.split("[" + delimeter + "]");
          
          for (int j = 0; j < albums.length; j++) {
        	//  System.out.println(albums[j].trim());
        	  
        	  Element album = new Element("album", b);
        	  
        	  album.addContent(albums[j].trim());
        	  artist.addContent(album);
          }
        }
        artists.addContent(artist);
      }
    }

    doc.addContent(artists);
    
    HashMap<String, String> piMap = new HashMap<String, String>( 2 );
	  piMap.put( "type", "text/xsl" );
	  piMap.put( "href", "artists.xsl" );
	  ProcessingInstruction pi = new ProcessingInstruction("xml-stylesheet",
	piMap );

    doc.getContent().add(0, pi );
    
    return doc;
  }

  void addArtists() {
    artistpanel.removeAll();
    for (int i=0; i < artistsVector.size(); i++) {
      Artist a = artistsVector.elementAt(i);
      if (a!=null) {
        Button b = new Button(a.name);
        b.setActionCommand(String.valueOf(i));
        b.addActionListener(this);
        artistpanel.add(b);
      }
    }
    pack();
  }

  public ArtistEditor(String xmlFile) {
    super("ArtistEditor");
    this.xmlFile = xmlFile;
    try {
      SAXBuilder b = new SAXBuilder();
      b.setValidation(true);
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
        "http://www.w3.org/2001/XMLSchema");
      b.setProperty(
        "http://java.sun.com/xml/jaxp/properties/schemaSource",
        xsdFile );
      Document d = b.build(new File(xmlFile));
      if (!d.getRootElement().getNamespaceURI().equals("http://alexdiscography.org"))
        throw new JDOMException("Wrong namespace of root element!");
    //  d.getRootElement()
    //  .addContent(newContent)
      artistsVector = doc2vector(d);
    } catch (Exception e) { 
      System.err.println(e); 
      System.exit(-1);
    }
    // initialize the user interface
    setLayout(new BorderLayout());
    ScrollPane s = new ScrollPane();
    s.setSize(200, 0);
    s.add(artistpanel);
    add(s,BorderLayout.WEST); 
    
    Panel l = new Panel(new GridLayout(12, 1));  
    l.add(new Label("Name"));                  
    l.add(new Label("Genre(s)"));                  
    l.add(new Label("Album(s)"));                  
    add(l,BorderLayout.CENTER);
    
    Panel f = new Panel(new GridLayout(12, 1));
    f.add(name);    
    f.add(genre);    
    f.add(album);    
    //add(s,BorderLayout.WEST);
    
    /*  // Scrollable Forms
    ScrollPane fScroll = new ScrollPane();  // Added
    fScroll.setSize(200, 0);
    fScroll.add(f);
    add(fScroll,BorderLayout.EAST); 
    */
    
    add(f,BorderLayout.EAST);
    Panel p = new Panel();
   ///* 
    browserOpen.addActionListener(this);
    p.add(browserOpen);
    addArtist.addActionListener(this);
    p.add(addArtist);
    //*/
    ok.addActionListener(this);
    p.add(ok);
    delete.addActionListener(this);
    p.add(delete);
    clear.addActionListener(this);
    p.add(clear);
    save.addActionListener(this);
    p.add(save);
    quit.addActionListener(this);
    p.add(quit);
    add(p,BorderLayout.SOUTH);
    addArtists();
    show();
  }

  public void actionPerformed(ActionEvent event) {
     Artist a;
     String command = event.getActionCommand();
     
     if (command.equals("browserOpen")) {
    	 if (Desktop.isDesktopSupported()) {
             try {
				Desktop.getDesktop().browse(new URI(outputFileName)); // << Run from generated HTML
			//	Desktop.getDesktop().browse(new URI(xmlFile)); // << Run directly from XML. XML seems to open only in IE
			} catch (Exception e) { System.out.println("Browser Open Error"); }
         }
     }
     
     else if (command.equals("addArtist")){
    	 a = new Artist("", "", "");
    	 
    	 artistsVector.add(a);
    	 addArtists();
     }
     
     else if (command.equals("ok")) {
       a = new Artist(name.getText(),
                    genre.getText(),
                    album.getText());
       if (current==-1) 
         artistsVector.add(a);
       else 
         artistsVector.setElementAt(a, current);
       addArtists();
     } else if (command.equals("delete")) {
        if (current!=-1) {
          artistsVector.setElementAt(null, current);
          current = -1;
          addArtists();
        }
     } else if (command.equals("clear")) {
        current = -1;
        name.setText("");
        genre.setText("");
        album.setText("");
     } else if (command.equals("save")) {
        try {
        	
        	// Save the XML file
     //     new XMLOutputter().output(vector2doc(),
     //                               new FileOutputStream(xmlFile));
          XMLOutputter prettyPrint = new XMLOutputter();
          prettyPrint.setFormat(Format.getPrettyFormat());
          prettyPrint.output(vector2doc(),
                                    new FileOutputStream(xmlFile));
          
          
          // Using the XML file saved above, generate an HTML file 
          new XmlToHtmlDriver(xslFile, xmlFile, outputFileName).generateHTML();
          
          
        } catch (Exception e) { 
          System.err.println(e); 
        }
     } else if (command.equals("quit")) {
        System.exit(0);
     } else {
        current = Integer.parseInt(command);
        a = artistsVector.elementAt(current);
        name.setText(a.name);
        genre.setText(a.genre);
        album.setText(a.album);
     }
  }
}