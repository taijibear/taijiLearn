package com.pansoft.Aisino;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

public class TaxCardService  {
	
	
	public  static  Document  CreateOpenCardXml (){
		Document doc = DocumentHelper.createDocument();  
		return null;
			
	} 
	
	
	public static Document CreateCloseCardXml (){
		Document doc = DocumentHelper.createDocument();  
		return null;
		
	}
	
	
	public static Document ReadXmlFile (String fileName) throws DocumentException{
		
		SAXReader sr = new SAXReader();
		Document doc = sr.read("/testXml"+"/"+fileName);
		
		return doc;
		
	}
	
	
	

}
