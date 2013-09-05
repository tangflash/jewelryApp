<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>钻石信息录入</title>
	<link href="<c:url value="${contextpath}/resources/form.css" />" rel="stylesheet"  type="text/css" />
	
	<link rel="stylesheet" href="<c:url value="${contextpath}/resources/css/themename/jquery-ui.custom.css"/>" type="text/css" />
	<script type="text/javascript" src="<c:url value="${contextpath}/resources/jquery/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="${contextpath}/resources/jquery-ui/jquery-ui.js"/>"></script>
	<script type="text/javascript" src="<c:url value="${contextpath}/resources/jquery-ui/jquery-ui-dp.js"/>"></script>
		
	<script type="text/javascript">
		//var flag = false;
		$(document).ready(function() {
			 $( "input[type=submit], a, button" ).button();
			 $("#bizDate").datepicker();
			 //if (flag == false)			 
			 $("#bizDate").datepicker("option", "dateFormat", "yy-mm-dd");	
			 $("#newBill").click(function(event){
					$("#billId").val("0");	
					$("#billNumber").val("");
					return false;
				 });	 
		});
		//flag = true;
	</script>
</head>
<body>	
	<div id="formsContent">		
		<form:form id="billHeadForm" method="post" action="doSaveBillHead" modelAttribute="materialIn" cssClass="cleanform">
			<div class="header">		  		
		  		<c:if test="${not empty successMessage}">
					<div id="successMessage" class="success">${successMessage}</div>						
		  		</c:if>
		  		<s:bind path="*">
		  			<c:if test="${status.error}">
				  		<div id="sysErrorMessage" class="error">保存出错</div>
		  			</c:if>
		  			<c:if test="${not empty errorMessage}">
				  		<div id="errorMessage" class="error">保存出错:${errorMessage}</div>
		  			</c:if>
		  		</s:bind>
			</div>
			<fieldset>
				<legend>单据头信息</legend>
				<form:hidden id="billId" path="id"/>
		  		<form:label path="billNumber">
		  			单据编码: <form:errors path="billNumber" cssClass="error" />
		 		</form:label>
		  		<form:input id="billNumber" path="billNumber" />
				
				<form:label path="clientName">
		  			客户: <form:errors path="clientName" cssClass="error" />
				</form:label>
				<form:input id="clientName" path="clientName" />
							
		  		<form:label path="bizDate">
		  			业务日期: <form:errors path="bizDate" cssClass="error" />
		 		</form:label>
		  		<form:input id="bizDate" path="bizDate" />
		  		
		  		<form:label path="CreateTime">
		  			创建时间: <form:errors path="CreateTime" cssClass="error" />
		 		</form:label>
		  		<form:input path="CreateTime" type="text" />		  		
			</fieldset>
			<fieldset>
				<legend>单据明细信息</legend>
				<form:hidden path="materialInDetail.id"/>
		  		<form:label path="materialInDetail.materNum">
		  			钻石编码: <form:errors path="materialInDetail.materNum" cssClass="error" />
		 		</form:label>
		  		<form:input path="materialInDetail.materNum" />
	
		  		<form:label path="materialInDetail.amount">
		  			数量: <form:errors path="materialInDetail.amount" cssClass="error" />
		 		</form:label>
		  		<form:input path="materialInDetail.amount" />
		  		
		  		<form:label path="materialInDetail.weight">
		  			重量: <form:errors path="materialInDetail.weight" cssClass="error" />
		 		</form:label>
		  		<form:input path="materialInDetail.weight" />
		  		<p><button type="submit">保存</button>
		  		<button id="newBill" type="submit">新增单据</button></p>
			</fieldset>
		</form:form>		
	</div>	
	
	
		
</body>
</html>
