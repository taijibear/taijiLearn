

var invoice;
var invoiceDetaiList;

/* 页面加载完成事件 */
$(function(){

	initPage();

})

function initPage (){


	initInvoiceData ();

}


/* 初始化发票数据*/
function  initInvoiceData (){

	var invoice = {

	 "fphm" : "3700142140",
	 "fpdm" : "02185147",
	 "kprq" : "2016-04-20",

	 "gfmc" : "胜利油田石油开发中心有限公司胜海分公司",
	 "gfsh" : "370521785013433",
	 "gfdzdh" : "垦利县黄河路20号院内303房间",
	 "gfyhzh" : "垦利县城区农村信用合作社 90505010020100093042",

	 "xfmc" : "370503864731142",
	 "xfsh" : "中国石油化工股份有限公司胜利油田分公司孤东采油厂",
	 "xfdzdh" : "山东省东营市河口区仙河镇 0546-8581938",
	 "xfyhzh" : "中国建行东营分行仙河支行 37001655801050001756",

	 "kpr" : "石增菊",
	 "skr" : "石增菊",
	 "fhr" : "石增菊"
	


	}

	$(".invoice.fphm.lb").text(invoice.fphm);	
	$(".invoice.fpdm.lb").text(invoice.fpdm);	
	$(".invoice.kprq.lb").text(invoice.kprq);

	$(".invoice.kpr.lb").text(invoice.fphm);	
	$(".invoice.fhr.lb").text(invoice.fpdm);	
	$(".invoice.skr.lb").text(invoice.kprq);

	$(".invoice.gfmc.tb").val(invoice.gfmc);	
	$(".invoice.gfsh.tb").val(invoice.gfsh);	
	$(".invoice.gfdzdh.tb").val(invoice.gfdzdh);	
	$(".invoice.gfyhzh.tb").val(invoice.gfyhzh);	
	$(".invoice.xfmc.tb").val(invoice.xfmc);	
	$(".invoice.xfsh.tb").val(invoice.xfsh);	
	$(".invoice.xfdzdh.tb").val(invoice.xfdzdh);	
	$(".invoice.xfyhzh.tb").val(invoice.xfyhzh);


	$(".invoice.kpr.lb").text(invoice.kpr);	
	$(".invoice.fhr.lb").text(invoice.skr);	
	$(".invoice.skr.lb").text(invoice.fhr);
	





}


/* 初始化 顶部样式区域 */
function topButtonAreainit(){

	var buttonName ;

	if  ($(this).hasClass("save_button")) {
		buttonName = "save_button";
	}else if ($(this).hasClass("billing_button")) {
		buttonName = "billing_button";
	}else if ($(this).hasClass("void_button")) {
		buttonName = "void_button";
	}else if ($(this).hasClass("print_button")) {
		buttonName = "print_button";
	}

	
	$("..top_button_area .btn").mouseover(
		function (){

			changeTopBtnCss("mouseover",buttonName);
		}
	);

	$("..top_button_area .btn").mouseleave(
		function (){

			changeTopBtnCss("mouseleave",buttonName);
		}
	);



}

function changeTopBtnCss(option,btnName){

	var imgPath = "";



}