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
	
	function deleteBalanceBill(billNumber){
		var answerResult = window.confirm("确认要删除结算单号为:" + billNumber + "的结算单吗?");
		 if (answerResult) return true;
		 else return false;		 
	}
	
	$(document).ready(function() {
		$("input[type=submit],button").button();
		$("#bizBeginDate").datepicker();
		$("#bizEndDate").datepicker();

		$("#bizBeginDate").datepicker("option", "dateFormat", "yy-mm-dd");
		$("#bizEndDate").datepicker("option", "dateFormat", "yy-mm-dd");
		
		iniDataTable();

		$(".deleteBillLinkClass").click(function(){			
			var link = $(this);
			var answerResult = window.confirm("确认要删除结算单号为:" + link.attr("name") + "的结算单吗?");
			 if (!answerResult) return false;				 
			
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
		  			结算开始日期: <form:errors path="bizBeginDate" cssClass="error" />
				</form:label>
				<form:input id="bizBeginDate" path="bizBeginDate" />

				<form:label path="bizEndDate">
		  			结算结束日期: <form:errors path="bizEndDate" cssClass="error" />
				</form:label>
				<form:input id="bizEndDate" path="bizEndDate" />				

				<p>
					<form:button value="queryButton" name="queryButton" type="submit">查询</form:button>					
				</p>
			</fieldset>
		</form:form>
	</div>
	<div id="formsContent">
		<table id="materialInListTable" class="listTable" border="1">
			<thead>
				<tr>
					<td>单据编码</td>
					<td>业务日期</td>
					<td>单据状态</td>
					<td>客户</td>					
					<td>创建时间</td>									
							
					<td style= "word-break:keep-all">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="detail">
					<tr>
						<td>${detail.billNumber}</td>						
						<td><fmt:formatDate value="${detail.bizDate}" pattern="yyyy-MM-dd"/></td>							
						<td>${detail.billStatus.name}</td>
						<td>${detail.clientName}</td>						
						<td><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>						
						<td style= "word-break:keep-all;width:200px">		
							<a class="editDetailLinkClass"
									href="<c:url value="/balanceBillList/showPage?id=${detail.id}"/>">查看单据</a>			
							<c:if test="${detail.billStatus.number=='0'}">								
								<!-- <a class="submitLinkClass"
									href="<c:url value="/balanceBillList/doSubmit?id=${detail.id}"/>">提交单据</a> -->
							</c:if>	
							<a id="deleteBillLink" name="${detail.billNumber}" class="deleteBillLinkClass" href="<c:url value="/balanceBillList/doDelBill?id=${detail.id}"/>">删除单据</a>
						</td>					
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
