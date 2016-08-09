package com.pansoft.Aisino;

import org.dom4j.Document;
import org.dom4j.DocumentException;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

public class TestAisinoController  extends Controller{
	
	public void index() {
		this.render("/commonInvoice.html");
		//renderText("此方法是一个action");
	}
	
	public void invoice() {
		this.render("/testPage.html");
		//renderText("此方法是一个action");
	}
	
	
	public void getXmlMessage () throws DocumentException {
		
		String fileName = this.getPara("fileName");
		
		fileName =  "OpenCard.xml";
		
		Document doc = TaxCardService.ReadXmlFile (fileName);
		
		String xmlString = doc.asXML();
		
		
		Record rd = new Record();
		
		rd.set("xml", xmlString);
		rd.set("base64", xmlString);
		
		
		this.renderJson(rd);
		
	}
	
	public void  encodeBase64 (){
		
		String content = this.getPara("content");
		String encoding = this.getPara("encoding");
		
		String base64 =AisionUtil.encodeBase64(content, encoding);
		

		Record rd = new Record();
		
		this.renderJson("base64",base64);
		
		
	}
	
	public void  decodeBase64 (){
		
		String content = this.getPara("content");
		String encoding = this.getPara("encoding");
		
		String xml =AisionUtil.decodeBase64(content, encoding);
		

		Record rd = new Record();
		
		this.renderJson("xml",xml);
		
		
	}


	

}
