package com.alexokoli.xmlmusic;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class XmlToHtmlDriver {
	
	String xslDocStr;
	String xmlDocStr;
	String outputFileName;
	
	public XmlToHtmlDriver(String xslDocStr, String xmlDocStr, String outputFileName){
		this.xslDocStr = xslDocStr;
		this.xmlDocStr = xmlDocStr;
		this.outputFileName = outputFileName;
	}
	
	
	public void generateHTML() {
		
		 try
	        {
	            TransformerFactory tFactory = TransformerFactory.newInstance();

	            Source xslDoc = new StreamSource(xslDocStr);
	            Source xmlDoc = new StreamSource(xmlDocStr);

	            OutputStream htmlFile = new FileOutputStream(outputFileName);

	            Transformer transformer = tFactory.newTransformer(xslDoc);
	            transformer.transform(xmlDoc, new StreamResult(htmlFile));
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	} 

}