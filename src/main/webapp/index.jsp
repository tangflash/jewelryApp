<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery-ui.custom.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui.js"/>"></script>

<style>
#div_tabs {
	margin-top: 0em;
}

#div_tabs li .ui-icon-close {
	float: left;
	margin: 0.4em 0.2em 0 0;
	cursor: pointer;
}

#add_tab {
	cursor: pointer;
}
iframe {
	width : 100%;
	height : 700px;
	border : 0;
}
</style>

<script type="text/javascript">
	function addTabFromFrame(title,url){
		var tabTemplate = "<li><a id='\\#{aLinkId}' href='\\#{href}'>\\#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>",			
		 tabCounter = 0;
		var tabs = $("#div_tabs").tabs();
		
		var label = title || "Tab " + tabCounter,
		 id = "tabs-" + tabCounter,
		 li = $( tabTemplate.replace( /#\{href\}/g, "#" + id )
				 .replace( /#\{label\}/g, label ).replace(/#\{aLinkId\}/g,"tab_link_" + id) ),
		 tabContentHtml = "<iframe src='"+ url +"'></iframe>";
		 tabs.find( ".ui-tabs-nav" ).append( li );
		 tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p></div>" );
		 tabs.tabs( "refresh" );				 				 
		 tabCounter++;	
		 $("#tab_link_" + id).click();
		 return false;
	}
	
	
	$(document).ready(function() {
		$("#date").datepicker();

		//$(function() {
			var icons = {
				header : "ui-icon-circle-arrow-e",
				activeHeader : "ui-icon-circle-arrow-s"
			};
			$("#div_menu").accordion({
				icons : icons
			});

			
			 var tabTemplate = "<li><a id='\\#{aLinkId}' href='\\#{href}'>\\#{label}</a> <span class='ui-icon ui-icon-close' role='presentation'>Remove Tab</span></li>",			
			 tabCounter = 0;
			var tabs = $("#div_tabs").tabs();
			
			// actual addTab function: adds new tab using the input from the form above
			function addTab(title,url) {				
				 var label = title || "Tab " + tabCounter,
				 id = "tabs-" + tabCounter,
				 li = $( tabTemplate.replace( /#\{href\}/g, "#" + id )
						 .replace( /#\{label\}/g, label ).replace(/#\{aLinkId\}/g,"tab_link_" + id) ),
				 tabContentHtml = "<iframe src='"+ url +"'></iframe>";
				 tabs.find( ".ui-tabs-nav" ).append( li );
				 tabs.append( "<div id='" + id + "' style='padding:0.5em 0.5em'><p>" + tabContentHtml + "</p></div>" );
				 tabs.tabs( "refresh" );				 				 
				 tabCounter++;	
				 $("#tab_link_" + id).click();
			}
			
			// addTab button: just opens the dialog
			$(".openLinkMenu").click(function(){
				addTab(this.innerHTML,this.href);
				return false;
			});	

			 // close icon: removing the tab on click
			tabs.delegate( "span.ui-icon-close", "click", function() {
			var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
			$( "#" + panelId ).remove();
			tabCounter--;
			tabs.tabs( "refresh" );
			});
			tabs.bind( "keyup", function( event ) {
			if ( event.altKey && event.keyCode === $.ui.keyCode.BACKSPACE ) {
			var panelId = tabs.find( ".ui-tabs-active" ).remove().attr( "aria-controls" );
			$( "#" + panelId ).remove();
			tabCounter--;
			tabs.tabs( "refresh" );
			}
			});		
			
			
		});

		
	//});
</script>

<style type="text/css">
#div_menu {
	float: left;
	width: 15%
}

#div_tabs {
	float: left;
	width: 80%
}

#div_menu div {	
	padding: 0.5em 0.5em;
}

.clear {
	clear: both;
}
</style>

</head>
<body>
	<!-- <input type="text" name="date" id="date" /> -->
	<div id="div_main">
		<div id="div_menu">
			<h3>基础数据录入</h3>
			<div>				
				<ul>
					<li><a class="openLinkMenu" href="<c:url value="/clientEdit/showAddPage"/>">客户信息录入</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/clientList/showListPage"/>">客户信息查询</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/materalInfInput/showAddPage"/>">钻石信息录入</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/materalInfInput/showListPage"/>">钻石信息查询</a></li>	
					<li><a class="openLinkMenu" href="<c:url value="/goldTypeEdit/showAddPage"/>">金成色信息录入</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/goldTypeList/showListPage"/>">金成色信息查询</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/productEdit/showAddPage"/>">品名信息录入</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/productList/showListPage"/>">品名信息查询</a></li>	
					<li><a class="openLinkMenu" href="<c:url value="/productStyleEdit/showAddPage"/>">款式信息录入</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/productStyleList/showListPage"/>">款式信息查询</a></li>							
				</ul>
				<p></p>
			</div>
			<h3>业务数据录入</h3>
			<div>
				<ul>
					<li><a class="openLinkMenu" href="<c:url value="/materialInBillEdit/showPage"/>">钻石入库</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/materialInBillList/showListPage"/>">钻石入库查询</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/orderBillEdit/showPage"/>">下单录入</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/orderBillList/showListPage"/>">下单查询</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/materialOutBillEdit/showPage"/>">出货单</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/materialOutBillList/showListPage"/>">出货单查询</a></li>
					<li><a class="openLinkMenu" href="<c:url value="/balanceBillEdit/showPage"/>">结算单生成</a></li>	
					<li><a class="openLinkMenu" href="<c:url value="/balanceBillList/showListPage"/>">结算单查询</a></li>				
				</ul>
				<p></p>
			</div>
			<h3>报表查询</h3>
			<div>
				<p></p>
				<ul>
					<li><a class="openLinkMenu" href="<c:url value="/materialInventory/showListPage"/>">库存查询</a></li>
				</ul>
			</div>
		</div>
		
		<div id="div_tabs">
			<ul>								
			</ul>					
		</div>
		<div id="div_clear" class="clear"></div>
	</div>
</body>
</html>
