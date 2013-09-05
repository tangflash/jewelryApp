<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>出货单查询</title>
<!-- <link href="<c:url value="/resources/form.css" />" rel="stylesheet"
	type="text/css" /> -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery.dataTables.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery.dataTables_themeroller.css"/>"
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
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-dataTables.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/dataTables-ini.js"/>"></script>

<script type="text/javascript">
	//var flag = false;
	function totalCol() {
		var tableName='#materialInListTable';
		var source1 = 0, source2 = 0;
		$('tr:gt(0)', tableName).each(
				function(i) {
					if ($(this).attr('total') != $('tr:last', tableName).attr(
							'total')) {
						source1 = source1
								+ parseInt($('td:eq(4)', $(this)).html());
						source2 = source2
								+ parseFloat($('td:eq(5)', $(this)).html());
					}
				});
		var trElement = $('tr:last', tableName);
		$('td:eq(4)', $('tr:last', tableName)).html(source1);
		$('td:eq(5)', $(trElement)).html(source2);
	}
	
	$(document).ready(function() {
		$("input[type=submit],button").button();
		$("#bizBeginDate").datepicker();
		$("#bizEndDate").datepicker();

		$("#bizBeginDate").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#bizEndDate").datepicker("option", "dateFormat", "yy-mm-dd");
		
		iniDataTable();

		$(".submitLinkClass").click(function(){
			var link = $(this);
			$.ajax({ url: link.attr("href"), 
				dataType: "text", 
				success: function(text) { $("#queryButton").click(); }, 
				error: function(xhr) { },
				complete: function( xhr ) {
				 	$("#queryButton").click();
				 }
				});			 	
			return false;
		});

		//totalCol();
	});
	//flag = true;
</script>
</head>
<body>
	<div id="formsFind">
		<form:form id="billHeadForm" method="post" action="totalBillList"
			modelAttribute="queryParam" cssClass="cleanform">
			<div class="header">
				<s:bind path="*">
					<c:if test="${status.error}">
						<div id="sysErrorMessage" class="error">查询出错</div>
					</c:if>
					<c:if test="${not empty errorMessage}">
						<div id="errorMessage" class="error">查询出错:${errorMessage}</div>
					</c:if>
				</s:bind>
			</div>
			<fieldset>
				<legend>查询条件</legend>						
				<form:label path="billNumber">
		  			单据编码: <form:errors path="billNumber" cssClass="error" />
				</form:label>
				<form:input id="billNumber" path="billNumber" />				

				<p>
					<form:button value="queryButton" name="queryButton" type="submit">查询</form:button>
					<form:button value="exportButton" name="exportButton" type="submit">导出</form:button>
					<form:select path="exportFormat">
						<form:option value="pdf">Pdf</form:option>
						<form:option value="xls">Excel</form:option>
						<form:option value="html">Html</form:option>
						<form:option value="csv">Csv</form:option>
					</form:select>
				</p>
			</fieldset>
		</form:form>
	</div>
	<div id="formsContent">
		<table id="produectTotalListTable" class="listTable" border="1">
			<thead>
				<tr>					
					<td>品名</td>					
					<td>件数</td>
					<td>货重</td>
					
					<td>净金重</td>
					<td>含耗重</td>
					<td>金料额</td>
					<td>工费</td>
					<td>附加工价</td>
					<td>超镶工费</td>
					
					<td>工费总额</td>
					
					<td>金额小计</td>					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produectTotalList}" var="detail">
					<tr>						
						<td>${detail.productName}</td>						
						<td>${detail.productAmount}</td>						
						<td><fmt:formatNumber value="${detail.productWeight}" pattern="#,#00.00#"/></td>
												
						<td><fmt:formatNumber value="${detail.goldWeight}" pattern="#,#00.00#"/></td>						
						<td><fmt:formatNumber value="${detail.consumeWeight}" pattern="#,#00.00#"/></td>						
						<td><fmt:formatNumber value="${detail.goldMoney}" pattern="#,#00.00#"/></td>						
						<td><fmt:formatNumber value="${detail.processCost}" pattern="#,#00.00#"/></td>						
						<td><fmt:formatNumber value="${detail.addProcessCost}" pattern="#,#00.00#"/></td>						
						<td><fmt:formatNumber value="${detail.superSetCost}" pattern="#,#00.00#"/></td>						
						
						<td><fmt:formatNumber value="${detail.totalProcessCost}" pattern="#,#00.00#"/></td>
												
						<td><fmt:formatNumber value="${detail.totalMoney}" pattern="#,#00.00#"/></td>
								
					</tr>
				</c:forEach>
				<!-- <tr total='aa'>
					<td>合计</td>
					<td></td>
					<td></td>
					<td></td>
					<td>0</td>
					<td>0</td>
					<td></td>
				</tr> -->
			</tbody>
		</table>
		<table id="mainMaterialTotalList" class="listTable" border="1">
			<thead>
				<tr>					
					<td>主石号</td>	
					<td>粒数</td>
					<td>重量</td>					
					<td>领用粒数</td>					
					<td>领用重量/ct</td>
					<td>退回粒数</td>					
					<td>退回重量/ct</td>						
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${mainMaterialTotalList}" var="detail">
					<tr>						
						<td>${detail.materName}</td>	
						<td>${detail.amount + detail.backAmount}</td>						
						<td><fmt:formatNumber value="${detail.weight + detail.backWeight}" pattern="#,#00.000#"/></td>					
						<td>${detail.amount}</td>						
						<td><fmt:formatNumber value="${detail.weight}" pattern="#,#00.000#"/></td>
						<td>${detail.backAmount}</td>						
						<td><fmt:formatNumber value="${detail.backWeight}" pattern="#,#00.000#"/></td>								
					</tr>
				</c:forEach>
				<!-- <tr total='aa'>
					<td>合计</td>
					<td></td>
					<td></td>
					<td></td>
					<td>0</td>
					<td>0</td>
					<td></td>
				</tr> -->
			</tbody>
		</table>
		<table id="secMaterialTotalList" class="listTable" border="1">
			<thead>
				<tr>					
					<td>副石单价</td>					
					<td>粒数</td>					
					<td>重量/ct</td>	
					<td>副石额</td>						
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${secMaterialTotalList}" var="detail">
					<tr>			
						<td>${detail.secPrice}</td>			
						<td>${detail.secAmount}</td>						
						<td><fmt:formatNumber value="${detail.secWeight}" pattern="#,#00.000#"/></td>					
						<td><fmt:formatNumber value="${detail.secMaterMoney}" pattern="#,#00.00#"/></td>								
					</tr>
				</c:forEach>
				<!-- <tr total='aa'>
					<td>合计</td>
					<td></td>
					<td></td>
					<td></td>
					<td>0</td>
					<td>0</td>
					<td></td>
				</tr> -->
			</tbody>
		</table>
		<table id="feeTotalList" class="listTable" border="1">
			<thead>
				<tr>					
					<td>附加工费</td>					
					<td>工费</td>					
					<td>超镶工费</td>	
					<td>金料额</td>
					<td>副石额</td>						
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${feeTotalList}" var="detail">
					<tr>						
						<td><fmt:formatNumber value="${detail.addProcessCost}" pattern="#,#00.00#"/></td>					
						<td><fmt:formatNumber value="${detail.totalProcessCost}" pattern="#,#00.00#"/></td>
						<td><fmt:formatNumber value="${detail.superSetCost}" pattern="#,#00.00#"/></td>
						<td><fmt:formatNumber value="${detail.goldMoney}" pattern="#,#00.00#"/></td>						
						<td><fmt:formatNumber value="${detail.secMaterMoney}" pattern="#,#00.00#"/></td>								
					</tr>
				</c:forEach>
				<!-- <tr total='aa'>
					<td>合计</td>
					<td></td>
					<td></td>
					<td></td>
					<td>0</td>
					<td>0</td>
					<td></td>
				</tr> -->
			</tbody>
		</table>
	</div>

</body>
</html>
