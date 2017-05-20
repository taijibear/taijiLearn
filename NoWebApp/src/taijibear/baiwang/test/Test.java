 package taijibear.baiwang.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.JsonObject;

import taijibear.baiwang.bean.InvoiceBean;
import taijibear.util.Base64Util;
import taijibear.util.HttpClientUtils;

public class Test {
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException{
		
		
		//testQueryAll ();
		
		//getAllQueryTest();

		//testInit();
		
		//testSynDdata ();
		
		testGetAllQuery();
		
		
	
	}
	
	
	/*测试发票数据初始化  */
	public static void testInit ( ) throws ClientProtocolException, IOException, URISyntaxException {
		
		JsonObject  head =  createRequestHead ();  

		JsonObject body =  new  JsonObject ();	
		body.addProperty("TaxNo", "913701007317289784");
		
		String result = postTestSys("init", head, body);
		 
		System.out.println(result );
		
	}
	
	/* 测试 同步发票 列表 */
	public static void testSynDdata( ) throws ClientProtocolException, IOException, URISyntaxException {
		
		
		JsonObject head =   createRequestHead ()  ; 
		JsonObject body =  new JsonObject(); 
		
		body.addProperty("TaxNo", "913701007317289784");

		String result = postTestSys("synDdata", head, body);
		 	  
		System.out.println(result );
	
	}
		
	
	/*  确认抵扣申请服务  */
	public static void testDeductible (){
		
	}
	
	
	/* 测试获取全票面信息 */
	public static void testGetAllQuery () throws ClientProtocolException, IOException, URISyntaxException{
		
		
		
		JsonObject head =   createRequestHead ()  ; 
		JsonObject body =  createInvEleBody ();  
		
		body.addProperty("TaxNo", "913701007317289784");
		

		String result = postTestSys("getAllQuery", head, body);
		 	  
		System.out.println(result );
	
		

	}
	
	
	
	
	
	/* 创建 基本请求体   */
	public static JsonObject  createRequestHead (){
	
		
		String  taxNo = "913701007317289786";
		String  enterprise = "普联软件股份有限公司";

		JsonObject head =  new JsonObject(); 
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			
		head.addProperty("Client", enterprise);
		head.addProperty("Time", df.format(new Date()));
		head.addProperty("GTAXID", taxNo);
		
		return  head;

		
	}
	
	/* 创建普通发票的 查询  */
	public static JsonObject createInvCommBody( ){
		
	
		JsonObject body =  new JsonObject(); 
		
		//body.addProperty( "GTAXID" , "913701007317289784" );
		//body.addProperty( "TaxNo" , "913701007317289784"  );
		body.addProperty( "InvoiceCode" , "3700161320"  );
		body.addProperty( "InvoiceNumber" , "10666826"  );
		body.addProperty( "BillingDate" , "2016-08-17" );

		body.addProperty( "TotalAmount" , "" );	
		body.addProperty( "CheckCode_6" , "522094"  );
		
		return body;
				
	}
	
	/* 创建专用发票的 查询  */
	public static JsonObject createInvSpeBody( ){
		
		JsonObject body =  new JsonObject(); 
		
		body.addProperty( "InvoiceCode" , "3700154130"  );
		body.addProperty( "InvoiceNumber" , "06791756"  );
		body.addProperty( "BillingDate" , "2016-08-11" );

		body.addProperty( "TotalAmount" , "3052.13" );	
		body.addProperty( "CheckCode_6" , ""  );
		
		return body;	
	}

	
	/* 创建 电子 发票的 查询  */
	public static JsonObject createInvEleBody( ){
		
		JsonObject body =  new JsonObject(); 

		body.addProperty( "InvoiceCode" , "044001505111"  );
		body.addProperty( "InvoiceNumber" , "10646852"  );
		body.addProperty( "BillingDate" , "2016-10-25" );

		body.addProperty( "TotalAmount" , "" );	
		body.addProperty( "CheckCode_6" , "741108"  );
		
		return body;	
	}
	
	
	
	
	
	/* 创建发票初始化的 body */
	public static JsonObject createInitBody( InvoiceBean inv ,String invType ){
		
	
		String  taxNo = "913701007317289784";
		String  enterprise = "普联软件股份有限公司";
		
		JsonObject body =  new JsonObject(); 
		
		body.addProperty( "GTAXID" , "913701007317289784" );
		body.addProperty( "TaxNo" , "913701007317289784"  );
		body.addProperty( "InvoiceCode" , "3700163320"  );
		body.addProperty( "InvoiceNumber" , "09069450"  );
		body.addProperty( "BillingDate" , "2017-01-30" );

		
		if  (invType.equals( "s") ) {
			body.addProperty( "TotalAmount" , "7923.08" );	
		} else {
			body.addProperty( "CheckCode_6" , "997849"  );
		}
		
		return body;
				
	}
	
	/* 访问百旺测试系统 */
	public static String postTestSys (String sign , JsonObject head, JsonObject body ) throws ClientProtocolException, IOException, URISyntaxException{
	
		String url = "http://218.94.72.202:19090/api/v1/th/transfer.do";
		
		Map<String, String> params = new  HashMap<String, String>();
		
		JsonObject dataJson = new  JsonObject ();
		
		JsonObject request = new  JsonObject ();	
			
		request.add("HEAD", head);
		request.add("BODY", body);
		dataJson.add("REQUEST", request);
		
		String jsonStr = dataJson.toString(); 
		
		String b64Data = Base64Util.getBase64(jsonStr);
				
		params.put("sign", sign);
		params.put("data", b64Data);

		
		System.out.println( "requestJson :" + jsonStr  );
		
		
		System.out.println( "sign :" + sign  );
		
		System.out.println( "data :" + b64Data  );
		
		
		String result = HttpClientUtils.simpleGetInvoke(url, params);
		  

		return result;
	
	}


}
