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
<title>库存查询</title>
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
		var tableName='#materInventory';
		var source1 = 0, source2 = 0,source3 = 0, source4 = 0,source5 = 0, source6 = 0;
		$('tr:gt(0)', tableName).each(
				function(i) {
					if ($(this).attr('total') != $('tr:last', tableName).attr(
							'total')) {
						source1 = source1
								+ parseInt($('td:eq(2)', $(this)).html());
						source2 = source2
								+ parseFloat($('td:eq(3)', $(this)).html());
						source3 = source3
								+ parseInt($('td:eq(4)', $(this)).html());
						source4 = source4
								+ parseFloat($('td:eq(5)', $(this)).html());
						source5 = source5
								+ parseInt($('td:eq(6)', $(this)).html());
						source6 = source6
								+ parseFloat($('td:eq(7)', $(this)).html());
					}
				});
		//var trElement = $('tr:last', tableName);
		$('td:eq(2)', $('tr:last', tableName)).html(source1);
		$('td:eq(3)', $('tr:last', tableName)).html(source2.toFixed(3));
		$('td:eq(4)', $('tr:last', tableName)).html(source3);
		$('td:eq(5)', $('tr:last', tableName)).html(source4.toFixed(3));
		$('td:eq(6)', $('tr:last', tableName)).html(source5);
		$('td:eq(7)', $('tr:last', tableName)).html(source6.toFixed(3));
	}
	
	$(document).ready(function() {
		$("input[type=submit], a, button").button();
		$("#bizBeginDate").datepicker();
		$("#bizEndDate").datepicker();

		$("#bizBeginDate").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#bizEndDate").datepicker("option", "dateFormat", "yy-mm-dd");
		
		iniDataTable();
		totalCol();
	});
	//flag = true;
</script>
</head>
<body>
	<div id="formsFind">
		<form:form id="billHeadForm" method="post" action="findList"
			modelAttribute="comQueryParam" cssClass="cleanform">
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
				<form:label path="materNum">
		  			钻石编码: <form:errors path="materNum" cssClass="error" />
				</form:label>
				<form:input id="materNum" path="materNum" />
			

				<p>
					<button type="submit">查询</button>
				</p>
			</fieldset>
		</form:form>
	</div>
	<div id="formsContent">
		<table id="materInventory" class="listTable" border="1">
			<thead>
				<tr>
					<td>客户</td>
					<td>钻石编码</td>
					<td>入库数量</td>					
					<td>入库重量</td>
					<td>出库数量</td>
					<td>出库重量</td>
					<td>结存数量</td>
					<td>结存重量</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="materialInventory">
					<tr>
						<td>${materialInventory.clientName}</td>
						<td>${materialInventory.materNum}</td>						
						<td>${materialInventory.inAmount}</td>
						<td><fmt:formatNumber value="${materialInventory.inWeight}" pattern="#,##0.000"/></td>						
						<td>${materialInventory.outAmount}</td>						
						<td><fmt:formatNumber value="${materialInventory.outWeight}" pattern="#,##0.000"/></td>
						<td>${materialInventory.balanceAmount}</td>						
						<td><fmt:formatNumber value="${materialInventory.balanceWeight}" pattern="#,##0.000"/></td>				
					</tr>
				</c:forEach>
				<tr total='aa'>
					<td>合计</td>
					<td></td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
					<td>0</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
