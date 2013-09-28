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
<title>钻石入库查询</title>
<!-- <link href="<c:url value="/resources/form.css" />" rel="stylesheet"
	type="text/css" /> -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery.dataTables.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery.dataTables.ext.css"/>"
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
	function totalCol() {
		var tableName='#materialInListTable';
		var source1 = 0, source2 = 0;
		$('tr:gt(0)', tableName).each(
				function(i) {
					if ($(this).attr('total') != $('tr:last', tableName).attr(
							'total')) {
						source1 = source1
								+ parseInt($('td:eq(5)', $(this)).html());
						source2 = source2
								+ parseFloat($('td:eq(6)', $(this)).html());
					}
				});
		var trElement = $('tr:last', tableName);
		$('td:eq(5)', $('tr:last', tableName)).html(source1);
		$('td:eq(6)', $(trElement)).html(source2);
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
		
		$(".editDetailLinkClass").click(function(event){			
			window.showModalDialog(this.href,"","dialogWidth=800px;dialogHeight=600px");
			window.location.reload(true);
			return false;			
		});

		totalCol();

	});
	//flag = true;
</script>
</head>
<body>
	<div id="formsFind">
		<form:form id="billHeadForm" method="post" action="findList"
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

				<form:label path="bizBeginDate">
		  			业务开始日期: <form:errors path="bizBeginDate" cssClass="error" />
				</form:label>
				<form:input id="bizBeginDate" path="bizBeginDate" />

				<form:label path="bizEndDate">
		  			业务结束日期: <form:errors path="bizEndDate" cssClass="error" />
				</form:label>
				<form:input id="bizEndDate" path="bizEndDate" />

				<p>
					<button id="queryButton" type="submit">查询</button>
				</p>
			</fieldset>
		</form:form>
	</div>
	<div id="formsContent">
		<table id="materialInListTable" class="listTable" border="1">
			<thead>
				<tr>
					<td>单据编码</td>
					<td>客户</td>
					<td>业务日期</td>
					<td>单据状态</td>
					<td>钻石编码</td>
					<td>数量</td>
					<td>重量</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="materialIn">
					<tr>
						<td>${materialIn.billNumber}</td>
						<td>${materialIn.clientName}</td>
						<td><fmt:formatDate value="${materialIn.bizDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${materialIn.billStatus.name}</td>
						<td>${materialIn.materialInDetail.materNum}</td>
						<td>${materialIn.materialInDetail.amount}</td>
						<td>${materialIn.materialInDetail.weight}</td>
						<td width="150"><c:if test="${materialIn.billStatus.number=='0'}">
								<a class="submitLinkClass"
									href="<c:url value="doDelBillDetail?id=${materialIn.materialInDetail.id}"/>">删除明细</a>								
								<a class="editDetailLinkClass"
									href="<c:url value="/materialInBillEdit/showPage?id=${materialIn.materialInDetail.id}"/>">编辑明细</a>
								<a class="submitLinkClass"
									href="<c:url value="doDelBill?id=${materialIn.id}"/>">删除单据</a>
								<a class="submitLinkClass"
									href="<c:url value="doSubmit?id=${materialIn.id}"/>">提交单据</a>								
							</c:if></td>
					</tr>
				</c:forEach>
				<tr total='aa'>
					<td>合计</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td>0</td>
					<td>0</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>
