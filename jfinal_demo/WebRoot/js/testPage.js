
var obAISINO;

$(function(){ 
	obAISINO = new ActiveXObject("InvSecCtrl.TaxCardEx"); //创建调用安全控件对象
}

); 



function run(){
	
	var base64_xml_str = $("#request_text").val();
	
	
	//var strKEY =obAISINO.KEYMsg; //获取KEY信息
	
	obAISINO.SafeInvoke(base64_xml_str);//调用安全控件发送BASE64请求
	var base64_result =obAISINO.SIDMsg; //获取REQ请求返回的信息
	
	$("#reuslt_text").val( base64_result );
	
	
	
}

// 编码 base64
function encodeBase64 (content, encoding) {
	

	var url='/jfinal_demo/test/encodeBase64';
	encoding ="GBK";
	content= $("#request_text").val();
	
	
	var senddata = {
	    	"encoding" :encoding,
	    	"content" :content
	 };
	$.ajax({
		url:url,
		dataType: 'JSON',
		type:'post',
		async :  false,
		secureuri:false,
		data:senddata,
		success: function (data){
			alert(data.base64);
			$("#request_text").val(data.base64);
		},
		cache:  false,
		timeout:60000
	});

}

// 解码 BASE 64 

function decodeBase64 (content, encoding) {
	

	var url='/jfinal_demo/test/decodeBase64';
	
	var encoding ="GBK";
	
	var content = $("#reuslt_text").val();
	

	var senddata = {
	    	"encoding" :encoding,
	    	"content" :content
	 };
	$.ajax({
		url:url,
		dataType: 'JSON',
		type:'post',
		async :  false,
		secureuri:false,
		data:senddata,
		success: function (data){
			
			$("#reuslt_text").val(data.xml);
			
			
		},
		cache:  false,
		timeout:60000
	});

}

// 取得报文
function getXmlMessage( fileName ){
	
	var url= '/jfinal_demo/test/getXmlMessage';
	
	var base64_xml_str;
	
	var senddata = {
	    	fileName :fileName
	 };
	$.ajax({
		url:url,
		dataType: 'JSON',
		type:'post',
		async :  false,
		secureuri:false,
		data:senddata,
		success: function (data){
			
			alert (data.xml);
			alert (data.base64);

			
		},
		cache:  false,
		timeout:60000
	});
	
	
	
} 

//
function OpenCard ( certPassWord ) {
	

	var url=sys_ctx+'/TaxCardService/OpenCard.do';
	

	var base64_xml_str;
	
	var senddata = {
	    	"certPassWord" : '88888888'
	 };
	$.ajax({
		url:url,
		dataType: 'JSON',
		type:'post',
		async :  false,
		secureuri:false,
		data:senddata,
		success: function (data){
			$("#showMessageArea").val(data.xmlStr);
			alert (data.xmlStr);
			$("#showMessageArea").val(data.resultData);
			
			base64_xml_str = data.resultData;
		},
		cache:  false,
		timeout:60000
	});
	

	
	var obAISINO = new ActiveXObject("InvSecCtrl.TaxCardEx"); //创建调用安全控件对象
	//var strKEY =obAISINO.KEYMsg; //获取KEY信息
	
	obAISINO.SafeInvoke(base64_xml_str);//调用安全控件发送BASE64请求
	var base64_result =obAISINO.SIDMsg; //获取REQ请求返回的信息
	
	alert(base64_result);
	
	decodeBase64(base64_result,'GBK');
	

}

