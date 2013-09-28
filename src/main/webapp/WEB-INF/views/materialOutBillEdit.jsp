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
		
		//计算含耗重
		$("#input_loss").change(function(event){
			calConsumeWeight();	
		});
		$("#input_goldWeight").change(function(event){
			calConsumeWeight();	
		});
		
		//计算金料额
		$("#input_consumeWeight").change(function(event){
			calGoldMoney();	
		});
		$("#input_goldPrice").change(function(event){
			calGoldMoney();	
		});
		
		calGoldMoney();
		
		//计算副石金额
		$("#input_secWeight").change(function(event){
			calSecMaterMoney();	
		});
		$("#input_secPrice").change(function(event){
			calSecMaterMoney();	
		});
		calSecMaterMoney();	
		
		//计算金额小计
		$("#input_goldMoney").change(function(event){
			calTotalMoney();	
		});
		$("#input_processCost").change(function(event){
			calTotalMoney();	
			calTotalProcessCost();
		});	
		$("#input_productAmount").change(function(event){			
			calTotalProcessCost();
		});		
		$("#input_addProcessCost").change(function(event){
			calTotalMoney();	
		});
		$("#input_superSetCost").change(function(event){
			calTotalMoney();	
		});
		$("#input_secMaterMoney").change(function(event){
			calTotalMoney();	
		});
		calTotalMoney();
		calTotalProcessCost();
		
		$("[tabindex]").tabEnter();
		
	});
	
	
	jQuery.fn.tabEnter = function() {
		this.keypress(function(e) {
			// get key pressed (charCode from Mozilla/Firefox and Opera / keyCode in IE)
			var key = e.charCode ? e.charCode : e.keyCode ? e.keyCode : 0;
			var isie = (document.all) ? true : false;
			var srcObj = null;
			if (isie)
				srcObj = event.srcElement;
			else
				srcObj = e.target;

			if (key==13 && srcObj.type != "button" && srcObj.type != "submit" && srcObj.type != "reset"
				 && srcObj.type != "textarea" && srcObj.type != "") {
				// get tabindex from which element keypressed
				var ntabindex = parseInt($(this).attr("tabindex")) + 1;
				$("[tabindex=" + ntabindex + "]").focus();
				return false;
			}
		});
	}

	//计算净金重
	function calGoldWeight() {
		var productWeight = $("#input_productWeight").val().trim();
		if (productWeight == "")
			return;
		var weight = $("#input_weight").val().trim();
		var seWeight = $("#input_secWeight").val().trim();

		if (weight == "")
			weight = "0";
		if (seWeight == "")
			seWeight = "0";

		var goldWeight = parseFloat(productWeight) - parseFloat(weight) * 0.2
				- parseFloat(seWeight) * 0.2;
		$("#input_goldWeight").val("" + goldWeight.toFixed(2))
				.trigger("change");
	}

	//计算含耗重
	function calConsumeWeight() {
		var loss = $("#input_loss").val().trim();
		var goldWeight = $("#input_goldWeight").val().trim();
		if (loss == "" || goldWeight == "")
			return;

		var consumeWeight = parseFloat(goldWeight)
				* (1 + parseFloat(loss) / 100);
		$("#input_consumeWeight").val("" + consumeWeight.toFixed(2)).trigger(
				"change");
	}

	//计算金料额
	function calGoldMoney() {
		var consumeWeight = $("#input_consumeWeight").val().trim();
		var goldPrice = $("#input_goldPrice").val().trim();
		if (consumeWeight == "" || goldPrice == "")
			return;

		var goldMoney = parseFloat(consumeWeight) * parseFloat(goldPrice);
		$("#input_goldMoney").val("" + goldMoney.toFixed(0)).trigger("change");
	}

	//计算副石金额
	function calSecMaterMoney() {
		var secWeight = $("#input_secWeight").val().trim();
		var secPrice = $("#input_secPrice").val().trim();
		if (secWeight == "" || secPrice == "")
			return;

		var secMaterMoney = parseFloat(secWeight) * parseFloat(secPrice);
		$("#input_secMaterMoney").val("" + secMaterMoney.toFixed(0)).trigger(
				"change");
	}

	//计算金额小计
	function calTotalMoney() {
		var goldMoney = $("#input_goldMoney").val().trim();
		goldMoney = goldMoney == "" ? "0" : goldMoney;

		var processCost = $("#input_processCost").val().trim();
		processCost = processCost == "" ? "0" : processCost;
		
		var productAmount = $("#input_productAmount").val().trim();
		productAmount = productAmount == "" ? "0" : productAmount;

		var addProcessCost = $("#input_addProcessCost").val().trim();
		addProcessCost = addProcessCost == "" ? "0" : addProcessCost;

		var superSetCost = $("#input_superSetCost").val().trim();
		superSetCost = superSetCost == "" ? "0" : superSetCost;

		var secMaterMoney = $("#input_secMaterMoney").val().trim();
		secMaterMoney = secMaterMoney == "" ? "0" : secMaterMoney;

		var totalMoney = parseFloat(goldMoney) + parseFloat(processCost) * parseFloat(productAmount)
				+ parseFloat(addProcessCost) + parseFloat(superSetCost)
				+ parseFloat(secMaterMoney);
		$("#input_totalMoney").val("" + totalMoney.toFixed(0))
				.trigger("change");
	}
	
	//计算工费
	function calTotalProcessCost() {
		var processCost = $("#input_processCost").val().trim();
		processCost = processCost == "" ? "0" : processCost;
		
		var productAmount = $("#input_productAmount").val().trim();
		productAmount = productAmount == "" ? "0" : productAmount;		

		var totalProcessCost = parseFloat(processCost) * parseFloat(productAmount);
		$("#input_totalProcessCost").val("" + totalProcessCost.toFixed(0)).trigger("change");
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
							<form:input id="billNumber" path="billNumber" tabindex="1"/>
						</td>
						<td>
							<form:label path="bizDate">
					  			业务日期: <form:errors path="bizDate" cssClass="error" />
							</form:label>
							<form:input id="bizDate" path="bizDate" tabindex="2"/>	
						</td>
						<td>
							<form:label path="clientName">
					  			客户: <form:errors path="clientName" cssClass="error" />
							</form:label>
							<form:input id="clientName" path="clientName" tabindex="3"/>	
						</td>
						<td>
							<form:label path="goldTypeName">
					  			金成色: <form:errors path="goldTypeName" cssClass="error" />
							</form:label>
							<form:input id="goldTypeName" path="goldTypeName" tabindex="4"/>
						</td>												
					</tr>
					<tr>
						<td>
							<form:label path="goldPrice">
					  			金单价: <form:errors path="goldPrice" cssClass="error" />
							</form:label>
							<form:input id="input_goldPrice" path="goldPrice" tabindex="5"/>
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
							<form:label path="materialOutDetail.number">
		  						序号: 
		  						<form:errors path="materialOutDetail.number"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.number" required="true" tabindex="6"/>
						</td>					
						<td>							
							<form:label path="materialOutDetail.styleName">
		  						款号: 
		  						<form:errors path="materialOutDetail.styleName"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.styleName" required="true" tabindex="7"/>
						</td>
						<td>
							<form:label path="materialOutDetail.productName">
					  			品名: <form:errors path="materialOutDetail.productName" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.productName" readonly="true"/>	
						</td>
						
					</tr>
					<tr>	
						<td>
							<form:label path="materialOutDetail.handSize">
					  			手寸: <form:errors path="materialOutDetail.handSize" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.handSize" tabindex="8"/>	
						</td>					
						<td>
							<form:label path="materialOutDetail.productAmount">
		  						件数: 
		  						<form:errors path="materialOutDetail.productAmount"	cssClass="error" />
							</form:label>
							<form:input id="input_productAmount" path="materialOutDetail.productAmount" tabindex="9"/>
						</td>
						<td>
							<form:label path="materialOutDetail.productWeight">
					  			货重: <form:errors path="materialOutDetail.productWeight" cssClass="error" />
							</form:label>
							<form:input id="input_productWeight" path="materialOutDetail.productWeight" tabindex="10"/>	
						</td>						
					</tr>
					<tr>	
						<td>
							<form:label path="materialOutDetail.goldWeight">
					  			净金重: <form:errors path="materialOutDetail.goldWeight" cssClass="error" />
							</form:label>
							<form:input id="input_goldWeight" path="materialOutDetail.goldWeight" readonly="true"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.loss">
					  			损耗(%): <form:errors path="materialOutDetail.loss" cssClass="error" />
							</form:label>
							<form:input id="input_loss" path="materialOutDetail.loss" tabindex="11"/>	
						</td>					
						<td>
							<form:label path="materialOutDetail.consumeWeight">
		  						含耗重: 
		  						<form:errors path="materialOutDetail.consumeWeight"	cssClass="error" />
							</form:label>
							<form:input id="input_consumeWeight" path="materialOutDetail.consumeWeight" readonly="true"/>
						</td>
												
					</tr>
					<tr>	
						<td>
							<form:label path="">
					  			金料额: <form:errors path="materialOutDetail.goldMoney" cssClass="error" />
							</form:label>
							<form:input id="input_goldMoney" path="materialOutDetail.goldMoney" readonly="true"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.processCost">
					  			工费单价: <form:errors path="materialOutDetail.processCost" cssClass="error" />
							</form:label>
							<form:input id="input_processCost" path="materialOutDetail.processCost" tabindex="12"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.totalProcessCost">
					  			工费: <form:errors path="materialOutDetail.totalProcessCost" cssClass="error" />
							</form:label>
							<form:input id="input_totalProcessCost" path="materialOutDetail.totalProcessCost" readonly="true"/>	
						</td>		
																	
					</tr>
					<tr>	
						<td>
							<form:label path="materialOutDetail.addProcessCost">
		  						附加工费: 
		  						<form:errors path="materialOutDetail.addProcessCost"	cssClass="error" />
							</form:label>
							<form:input id="input_addProcessCost" path="materialOutDetail.addProcessCost" tabindex="13"/>
						</td>	
						<td>
							<form:label path="materialOutDetail.superSetCost">
					  			超镶工费: <form:errors path="materialOutDetail.superSetCost" cssClass="error" />
							</form:label>
							<form:input id="input_superSetCost" path="materialOutDetail.superSetCost" tabindex="14"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.materName">
					  			主石号: <form:errors path="materialOutDetail.materName" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.materName" tabindex="15"/>	
						</td>					
																	
					</tr>
					<tr>	
						<td>
							<form:label path="materialOutDetail.amount">
		  						主石粒数: 
		  						<form:errors path="materialOutDetail.amount"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.amount" tabindex="16"/>
						</td>
						<td>
							<form:label path="materialOutDetail.weight">
					  			主石重量: <form:errors path="materialOutDetail.weight" cssClass="error" />
							</form:label>
							<form:input id="input_weight" path="materialOutDetail.weight" tabindex="17"/>	
						</td>	
						<td>
							<form:label path="materialOutDetail.factoryAddMoney">
					  			厂配主额: <form:errors path="materialOutDetail.factoryAddMoney" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.factoryAddMoney" tabindex="18"/>	
						</td>					
																		
					</tr>
					<tr>
						<td>
							<form:label path="materialOutDetail.secMaterName">
		  						副石号: 
		  						<form:errors path="materialOutDetail.secMaterName"	cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.secMaterName" tabindex="19"/>
						</td>	
						<td>
							<form:label path="materialOutDetail.secAmount">
					  			副石粒数: <form:errors path="materialOutDetail.secAmount" cssClass="error" />
							</form:label>
							<form:input path="materialOutDetail.secAmount" tabindex="20"/>	
						</td>
						<td>
							<form:label path="materialOutDetail.secWeight">
					  			副石重量: <form:errors path="materialOutDetail.secWeight" cssClass="error" />
							</form:label>
							<form:input id="input_secWeight" path="materialOutDetail.secWeight" tabindex="21"/>	
						</td>					
																		
					</tr>
					<tr>	
						<td>
							<form:label path="materialOutDetail.secPrice">
		  						副石单价/元: 
		  						<form:errors path="materialOutDetail.secPrice"	cssClass="error" />
							</form:label>
							<form:input id="input_secPrice" path="materialOutDetail.secPrice" tabindex="22"/>
						</td>
						<td>
							<form:label path="">
					  			副石额: <form:errors path="materialOutDetail.secMaterMoney" cssClass="error" />
							</form:label>
							<form:input id="input_secMaterMoney" path="materialOutDetail.secMaterMoney" readonly="true"/>	
						</td>					
						<td>
							<form:label path="materialOutDetail.totalMoney">
					  			金额小计: <form:errors path="materialOutDetail.totalMoney" cssClass="error" />
							</form:label>
							<form:input id="input_totalMoney" path="materialOutDetail.totalMoney" readonly="true"/>	
						</td>	
					</tr>
				</table>				

				
				<p>
					<button type="submit" tabindex="22">保存</button>
					<button id="newBill" type="submit">新增单据</button>
				</p>
			</fieldset>
		</form:form>
	</div>



</body>
</html>
