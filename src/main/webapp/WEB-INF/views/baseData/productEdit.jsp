<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>品名息录入</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />
	
	<link rel="stylesheet" href="<c:url value="/resources/css/themename/jquery-ui.custom.css"/>" type="text/css" />
	<script type="text/javascript" src="<c:url value="/resources/jquery/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/jquery-ui/jquery-ui.js"/>"></script>
		
	<script type="text/javascript">
		$(document).ready(function() {
			 $( "input[type=submit], a, button" ).button();
		});
	</script>
</head>
<body>	
	<div id="formsContent">		
		<form:form id="form" method="post" action="doSave" modelAttribute="product" cssClass="cleanform">
			<div class="header">
		  		<h2>品名信息录入</h2>
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
				<legend>品名信息</legend>
				<form:hidden path="id"/>
		  		<form:label path="number">
		  			编码: <form:errors path="number" cssClass="error" />
		 		</form:label>
		  		<form:input path="number" />
	
		  		<form:label path="name">
		  			名称: <form:errors path="name" cssClass="error" />
		 		</form:label>
		  		<form:input path="name" />		  		
		  		
		  		<form:label path="remark">
		  			备注: <form:errors path="remark" cssClass="error" />
		 		</form:label>
		  		<form:input path="remark" />
		  		<p><button type="submit">保存</button></p>
			</fieldset>
		</form:form>	
	</div>	
	
	
		
</body>
</html>
