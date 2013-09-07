<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>钻石信息录入</title>
<link href="<c:url value="/resources/form.css" />" rel="stylesheet"
	type="text/css" />

<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery-ui.custom.css"/>"
	type="text/css" />
<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui-dp.js"/>"></script>

<script type="text/javascript">
	//var flag = false;
	$(document).ready(function() {
		$("input[type=submit], a, button").button();
		$("#bizDate").datepicker();
		//if (flag == false)			 
		$("#bizDate").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#newBill").click(function(event) {
			$("#billId").val("0");
			$("#billNumber").val("");
			return false;
		});
		
		//净金重计算公式
		$("#input_productWeight").change(function(event){
			calGoldWeight();	
		});
		$("#input_weight").change(function(event){
			calGoldWeight();	
		});
		$("#input_secWeight").change(function(event){
			calGoldWeight();	
		});
	});
	
	//计算净金重
	function calGoldWeight(){
		var productWeight = $("#input_productWeight").val().trim();
		if (productWeight == "") return;
		var weight = $("#input_weight").val().trim();
		var seWeight = $("#input_secWeight").val().trim();
		
		if (weight == "") weight = "0"; 
		if (seWeight == "") seWeight = "0"; 
		
		var goldWeight = parseFloat(productWeight) - parseFloat(weight) * 0.2 - parseFloat(seWeight) * 0.2;
		$("#input_goldWeight").val("" + goldWeight.toFixed(3));
	}
	//flag = true;
</script>
</head>
<body>
	<div id="formsContent">
		<form:form id="billHeadForm" method="post" action="doSaveBillHead"
			modelAttribute="materialOut" cssClass="cleanform">
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
				<form:hidden id="billId" path="id" />
				<table class="formTable">
					<tr>
						<td>
							<form:label path="billNumber">
					  			单据编码: <form:errors path="billNumber" cssClass="error" />
							</form:label>
							<form:input id="billNumber" path="billNumber" />
						</td>
						<td>
							<form:label path="bizDate">
					  			业务日期: <form:errors path="bizDate" cssClass="error" />
							</form:label>
							<form:input id="bizDate" path="bizDate" />	
						</td>
						<td>
							<form:label path="clientName">
					  			客户: <form:errors path="clientName" cssClass="error" />
							</form:label>
							<form:input id="clientName" path="clientName" />	
						</td>
						<td>
							<form:label path="goldTypeName">
					  			金成色: <form:errors path="goldTypeName" cssClass="error" />
							</form:label>
							<form:input id="goldTypeName" path="goldTypeName" />
						</td>												
					</tr>
					<tr>
						<td>
							<form:label path="goldPrice">
					  			金单价: <form:errors path="goldPrice" cssClass="error" />
							</form:label>
							<form:input id="goldPrice" path="goldPrice" />
						</td>
						<td>
							<form:label path="createTime">
					  			创建时间: <form:errors path="createTime" cssClass="error" />
							</form:label>
							<form:input path="createTime" type="text" disabled="true"/>	
						</td>
						<td>
							<form:hidden path="billStatus.number" />
							<form:label path="billStatus.name">
					  			单据状态: <form:errors path="billStatus.name" cssClass="error" />
							</form:label>
							<form:input path="billStatus.name" type="text" disabled="true"/>	
						</td>
					</tr>
				</table>				
			</fieldset>
			<fieldset>
				<legend>单据明细信息</legend>
				<form:hidden path="materialOutDetail.id" />
				<table class="formTable">
					<tr>						
						<td>
							<form:label path="materialOutDetail.styleName">
		  						款号: 
		  						<form:errors path="materialOutDetail.styleName"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.styleName" />
						</td>
						<td>
							<form:label path="materialOutDetail.productName">
					  			品名: <form:errors path="materialOutDetail.productName" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.productName" disabled="true"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.handSize">
					  			手寸: <form:errors path="materialOutDetail.handSize" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.handSize" />	
						</td>
					</tr>
					<tr>						
						<td>
							<form:label path="materialOutDetail.productAmount">
		  						件数: 
		  						<form:errors path="materialOutDetail.productAmount"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.productAmount" />
						</td>
						<td>
							<form:label path="materialOutDetail.productWeight">
					  			货重: <form:errors path="materialOutDetail.productWeight" cssClass="error" />
							</form:label>
							<form:input id="input_productWeight" path="materialOutDetail.productWeight" />	
						</td>
						<td>
							<form:label path="materialOutDetail.goldWeight">
					  			净金重: <form:errors path="materialOutDetail.goldWeight" cssClass="error" />
							</form:label>
							<form:input id="input_goldWeight" path="materialOutDetail.goldWeight" disabled="true"/>	
						</td>
					</tr>
					<tr>						
						<td>
							<form:label path="materialOutDetail.consumeWeight">
		  						含耗重: 
		  						<form:errors path="materialOutDetail.consumeWeight"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.consumeWeight" disabled="true"/>
						</td>
						<td>
							<form:label path="">
					  			金料额: <form:errors path="" cssClass="error" />
							</form:label>
							<form:input path="" disabled="true"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.processCost">
					  			工费: <form:errors path="materialOutDetail.processCost" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.processCost" />	
						</td>
					</tr>
					<tr>						
						<td>
							<form:label path="materialOutDetail.addProcessCost">
		  						附加工价: 
		  						<form:errors path="materialOutDetail.addProcessCost"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.addProcessCost" />
						</td>
						<td>
							<form:label path="materialOutDetail.superSetCost">
					  			超镶工费: <form:errors path="materialOutDetail.superSetCost" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.superSetCost" />	
						</td>
						<td>
							<form:label path="materialOutDetail.materName">
					  			主石号: <form:errors path="materialOutDetail.materName" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.materName" />	
						</td>
					</tr>
					<tr>						
						<td>
							<form:label path="materialOutDetail.amount">
		  						主石粒数: 
		  						<form:errors path="materialOutDetail.amount"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.amount" />
						</td>
						<td>
							<form:label path="materialOutDetail.weight">
					  			主石重量: <form:errors path="materialOutDetail.weight" cssClass="error" />
							</form:label>
							<form:input id="input_weight" path="materialOutDetail.weight" />	
						</td>
						<td>
							<form:label path="materialOutDetail.factoryAddMoney">
					  			厂配主额: <form:errors path="materialOutDetail.factoryAddMoney" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.factoryAddMoney" />	
						</td>
					</tr>
					<tr>						
						<td>
							<form:label path="materialOutDetail.secMaterName">
		  						副石号: 
		  						<form:errors path="materialOutDetail.secMaterName"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.secMaterName" />
						</td>
						<td>
							<form:label path="materialOutDetail.secAmount">
					  			副石粒数: <form:errors path="materialOutDetail.secAmount" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.secAmount" />	
						</td>
						<td>
							<form:label path="materialOutDetail.secWeight">
					  			副石重量: <form:errors path="materialOutDetail.secWeight" cssClass="error" />
							</form:label>
							<form:input id="input_secWeight" path="materialOutDetail.secWeight" />	
						</td>
					</tr>
					<tr>						
						<td>
							<form:label path="materialOutDetail.secPrice">
		  						石单价/元: 
		  						<form:errors path="materialOutDetail.secPrice"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.secPrice" />
						</td>
						<td>
							<form:label path="">
					  			副石额: <form:errors path="" cssClass="error" />
							</form:label>
							<form:input path="" disabled="true"/>	
						</td>
						<td>
							<form:label path="">
					  			金额小计: <form:errors path="" cssClass="error" />
							</form:label>
							<form:input path="" disabled="true"/>	
						</td>
					</tr>
				</table>				

				
				<p>
					<button type="submit">保存</button>
					<button id="newBill" type="submit">新增单据</button>
				</p>
			</fieldset>
		</form:form>
	</div>



</body>
</html>
