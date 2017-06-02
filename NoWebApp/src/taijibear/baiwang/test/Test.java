 package taijibear.baiwang.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.ClientProtocolException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import taijibear.baiwang.bean.InvoiceBean;
import taijibear.util.ActiveRecordUtil;
import taijibear.util.Base64Util;
import taijibear.util.HttpClientUtils;

public class Test {
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException, URISyntaxException{

		ActiveRecordUtil.initDb();
		
	  	Record inv = getInvByHm ( "03267013"  );
	    //	List<Record> invList = new ArrayList<Record> ();
	    //	invList.add(inv);

	  	//testDeductible ( invList ,"201705", false);
	  	testDkResult( inv , false );
	  	
	  	// System.out.println(   json.toString() );
	  	//	testGetAllQuery();
	    //	String taxNo =  "913701007317289784";
	  	
	  	String taxNo =  "916501000802448054";
	 // 	String enterprise  = "普联软件股份有限公司" 
	  	String enterprise  = "普联软件股份有限公司新疆分公司" ;
	  	
	  	testInit ( taxNo , enterprise , true);

	  	//testSynDdata (taxNo , enterprise ,false );
		//synBack (taxNo , enterprise  , "19215523702113921" ,false);
		
		// getAccessToken ();
		 //getOpenId();
	}
	
	
	public static void  synBack (String taxNo , String enterprise , String taskNo , boolean isTest ) throws ClientProtocolException, IOException, URISyntaxException{

		JsonObject  head =  createRequestHead ( taxNo,enterprise  );  
		JsonObject body =  new  JsonObject ();	
		body.addProperty("TaxNo",  taxNo );
		body.addProperty("TaskNo",  taskNo );
		
		String result = postTestSys("synBack", head, body , isTest); 
		System.out.println(result );
			
	}
	
	
	
	/* 根据发票号码获取发票  */
	public static Record getInvByHm ( String fphm ){
		
		String sql = "select * from  sw_bw_inv where f_fphm=? ";
		
		Record rd = Db.findFirst(sql, fphm) ;
		
		System.out.println( rd.toString() );
		
		return rd;
		
	}
	

	/*测试发票数据初始化  */
	public static void testInit ( String taxNo , String enterprise , boolean isTest) throws ClientProtocolException, IOException, URISyntaxException {
		
		JsonObject  head =  createRequestHead ( taxNo, enterprise );  
		JsonObject body =  new  JsonObject ();	
		body.addProperty("TaxNo", taxNo);
		
		String result = postTestSys("init", head, body , isTest);
		 
		System.out.println(result );
		
	}
	
	/* 测试 同步发票 列表 */
	public static void testSynDdata(String taxNo,  String enterprise , boolean isTest ) throws ClientProtocolException, IOException, URISyntaxException {
		
		JsonObject head =   createRequestHead ( taxNo,  enterprise)  ; 
		JsonObject body =  new JsonObject(); 
		
		body.addProperty("TaxNo",  taxNo);

		String result = postTestSys("synDdata", head, body , isTest);
	
		JsonObject resultJson = new  JsonParser().parse( result).getAsJsonObject();
		
		JsonObject rbody = resultJson.get("BODY").getAsJsonObject();
		
		String  taskNo = rbody.get( "TaskNo" ).getAsString();
		String   followTask = rbody.get( "FollowTask" ).getAsString();
		
			
		System.out.println(result );
	
		System.out.println("  TaskNo : " + taskNo  );
		System.out.println("  FollowTask : " + followTask  );
		
	}
		
	
	/* 测试 确认抵扣申请服务  */
	public static void testDeductible ( List<Record>  invList , String priod ,boolean isTest ) throws ClientProtocolException, IOException, URISyntaxException{
		
		
		Record firstInv = invList.get(0);	
		JsonObject head =   createRequestHead ( firstInv )  ; 
		JsonObject body =  createDeductibleBody( invList ,  priod );
		String result = postTestSys("deductible", head, body , isTest);
		System.out.println(result );
		
	}
	
	/* 测试获取 认证 结果  */
	public static void testDkResult ( Record  invRd , boolean isTest ) throws ClientProtocolException, IOException,
	URISyntaxException{
		

		JsonObject head =   createRequestHead ( invRd )  ; 
		JsonObject body =  createDkResultBody( invRd );
		String result = postTestSys("dkResult", head, body , isTest);
		System.out.println(result );
		
	}

	/* 创建 发票 认证的查询体 */
	public static JsonObject  createDeductibleBody ( List<Record>  invList , String priod ) {
		
		Record firstInv = invList.get(0);
		
		String gfsh = firstInv.getStr("F_GFSH" );

		String gfmc = firstInv.getStr("F_GFMC" );
		
		
		JsonObject body = new  JsonObject();
		

		JsonArray   InvoiceJsonArray  = new  JsonArray (); 
		
		JsonObject invJson =  new JsonObject(); 
		
		JsonObject jsonR = new  JsonObject ();
		
		body.addProperty("TaxNo",gfsh );
		body.add("JSONR", jsonR);
		
		jsonR.addProperty("TaxNo", gfsh);
		jsonR.addProperty("Period", priod);
		
		jsonR.add("InvoiceList", InvoiceJsonArray);
		
		for ( Record  invRd :  invList ) {
			
			invJson =  new JsonObject();  
				
			invJson.addProperty("InvoiceCode", invRd.getStr( "F_FPDM" )) ;
			invJson.addProperty("InvoiceNumber", invRd.getStr( "F_FPHM" )) ;
			invJson.addProperty("PurchaserTaxNo", invRd.getStr( "F_GFSH" )) ;
			
			InvoiceJsonArray.add(invJson);			
		}
		
		return body;	
	
	}
	
	/* 创建 查询认证结果的 查询体*/
	public static JsonObject  createDkResultBody ( Record invRd ) {
		
		  JsonObject body = new JsonObject ();
		 
		  body.addProperty( "TaxNo"  ,   invRd.getStr( "F_GFSH")  );

		  body.addProperty( "InvoiceCode"  ,   invRd.getStr( "F_FPDM")  );
		  body.addProperty( "InvoiceNumber"  ,   invRd.getStr( "F_FPHM")  );
		  body.addProperty( "TaskNo"  ,   invRd.getStr( "F_TASK_NO")  );
		
		  return body; 
    
		
	}
	
	
	
	/* 测试获取全票面信息 */
	public static void testGetAllQuery ( Record invRd , boolean isTest  ) throws ClientProtocolException, IOException, URISyntaxException{
		
		JsonObject head =   createRequestHead ( invRd )  ; 
		JsonObject body =  createInvEleBody ();  
		
		body.addProperty("TaxNo", "913701007317289784");
		

		String result = postTestSys("getAllQuery", head, body , isTest);
		 	  
		System.out.println(result );
	
		

	}
	
	
	
	
	
	/* 创建 基本请求体   */
	public static JsonObject  createRequestHead (  Record invRd  ){
	
		
		String  taxNo =  invRd.getStr("F_GFSH");
		String  enterprise = invRd.getStr("F_GFMC");

		return createRequestHead  ( taxNo, enterprise );
		
	}
	
	/* 创建 基本请求体   */
	public static JsonObject  createRequestHead ( String  gfsh , String gfmc   ){
	
		JsonObject head =  new JsonObject(); 
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
			
		head.addProperty("Client", gfmc);
		head.addProperty("Time", df.format(new Date()));
		head.addProperty("GTAXID", gfsh);
		
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
	public static String postTestSys (String sign , JsonObject head, JsonObject body 
			, boolean isTest ) throws ClientProtocolException, IOException, URISyntaxException{
	
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
		
		params.put("dataType", "0");
		params.put("busiType", "vat_income");
		
		
		
		params.put("sqm", "f2fc990265c712c49d51a18a32b39f0c");
		
		
		if  (!isTest) {
			
			url = "https://www.fapiao.com/Entoauth/thirdApi";
			
			params.put("client_id"  , "14955195201" );
			params.put("access_token"  , "24EF723817764CC9F56950EA5F92816F");
			params.put("openID"  , "2432704419863E1A9F59219D41FB0E64");
			
		}

		System.out.println( "requestJson :" + jsonStr  );
		System.out.println( "sign :" + sign  );
		System.out.println( "data :" + b64Data  );
		
		String result = HttpClientUtils.httpsPostInvoke(url, params);
		  
		return result;
	
	}
	
	
	
	/* 获取 openid */
	public static  String  getAccessToken (   ) throws ClientProtocolException, IOException, URISyntaxException{
		
		String url = "https://www.fapiao.com:53088/Entoauth/TokenAction";
		
		String sign="token";
		String client_id =  "14955195201";
		String client_secret = "757b505cfd34c64c85ca5b5690ee5293"; 
	
		Map<String, String> params = new  HashMap<String, String>();
		
		params.put("sign", sign);
		params.put("client_id", client_id);
		params.put("client_secret", client_secret);
		
		String result = HttpClientUtils.httpsPostInvoke(url, params);
		System.out.println( result );
		
		return  null;
		
	}
	
	
	
	/* 获取 openId  */
	public static String getOpenId  () throws ClientProtocolException, URISyntaxException, IOException{
		
		String url = "https://www.fapiao.com:53088/Entoauth/OpenAction";
		
		String sign="open";
		String client_id =  "14955195201";
		String access_token = "24EF723817764CC9F56950EA5F92816F"; 
	
	
		Map<String, String> params = new  HashMap<String, String>();
		
		params.put("sign", sign);
		params.put("client_id", client_id);
		params.put("access_token", access_token);
		
		String result = HttpClientUtils.httpsPostInvoke(url, params);
		System.out.println( result );
		
		return  null;

		
	}


}
