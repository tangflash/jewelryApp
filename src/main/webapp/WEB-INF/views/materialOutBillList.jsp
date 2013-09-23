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
		
		$(".editDetailLinkClass").click(function(event){			
			window.showModalDialog(this.href,"","dialogWidth=800px;dialogHeight=600px");
			window.location.reload(true);
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
		  			业务开始日期: <form:errors path="bizBeginDate" cssClass="error" />
				</form:label>
				<form:input id="bizBeginDate" path="bizBeginDate" />

				<form:label path="bizEndDate">
		  			业务结束日期: <form:errors path="bizEndDate" cssClass="error" />
				</form:label>
				<form:input id="bizEndDate" path="bizEndDate" />

				<p>
					<form:button value="queryButton" name="queryButton" type="submit">查询</form:button>
					<form:button value="exportButton" name="exportButton" type="submit">导出</form:button>
					<form:select path="exportFormat">
						<form:option value="xls">Excel</form:option>
						<form:option value="pdf">Pdf</form:option>						
						<form:option value="html">Html</form:option>
						<form:option value="csv">Csv</form:option>
					</form:select>
				</p>
			</fieldset>
		</form:form>
	</div>
	<div id="formsContent">
		<table id="materialInListTable" class="listTable" border="1">
			<thead>
				<tr>
					<td>单据编码</td>
					<td style= "word-break:keep-all;width:350px">业务日期</td>
					<td>单据状态</td>
					<td>客户</td>
					<td>金价</td>
					<td>金成色</td>	
					<td>创建时间</td>
									
					<td>款式</td>
					<td>品名</td>
					<td>手寸</td>
					<td>件数</td>
					<td>货重</td>
					
					<td>净金重</td>
					<td>损耗(%)</td>
					<td>含耗重</td>
					<td>金料额</td>
					<td>工费</td>
					<td>附加工费</td>
					<td>超镶工费</td>
					
					<td>主石编码</td>
					<td>主石粒数</td>
					<td>主石重量</td>
					<td>厂配主额</td>
					<td>副石编码</td>
					
					<td>副石粒数</td>
					<td>副石重量</td>
					<td>副石单价/元</td>	
					<td>副石额</td>	
					<td>金额小计</td>			
					<td style= "word-break:keep-all">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="detail">
					<tr>
						<td>${detail.materialOut.billNumber}</td>						
						<td style= "word-break:keep-all;width:350px"><fmt:formatDate value="${detail.materialOut.bizDate}" pattern="yyyy-MM-dd"/></td>							
						<td>${detail.materialOut.billStatus.name}</td>
						<td>${detail.materialOut.clientName}</td>
						<td align="right"><fmt:formatNumber value="${detail.materialOut.goldPrice}" pattern="#,##0.00"/></td>						
						<td>${detail.materialOut.goldTypeName}</td>
						<td><fmt:formatDate value="${detail.materialOut.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						
						
						<td>${detail.styleName}</td>
						<td>${detail.productName}</td>						
						<td align="right"><fmt:formatNumber value="${detail.handSize}" pattern="#,##0.##"/></td>
						<td>${detail.productAmount}</td>						
						<td align="right"><fmt:formatNumber value="${detail.productWeight}" pattern="#,##0.00"/></td>
												
						<td align="right"><fmt:formatNumber value="${detail.goldWeight}" pattern="#,##0.00"/></td>	
						<td align="right"><fmt:formatNumber value="${detail.loss}" pattern="#,##0"/></td>					
						<td align="right"><fmt:formatNumber value="${detail.consumeWeight}" pattern="#,##0.00"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.goldMoney}" pattern="#,##0"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.totalProcessCost}" pattern="#,##0"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.addProcessCost}" pattern="#,##0"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.superSetCost}" pattern="#,##0"/></td>
						
						<td>${detail.materName}</td>
						<td>${detail.amount}</td>						
						<td align="right"><fmt:formatNumber value="${detail.weight}" pattern="#,##0.000"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.factoryAddMoney}" pattern="#,##0"/></td>
						<td>${detail.secMaterName}</td>
						
						<td>${detail.secAmount}</td>						
						<td align="right"><fmt:formatNumber value="${detail.secWeight}" pattern="#,##0.000"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.secPrice}" pattern="#,##0"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.secMaterMoney}" pattern="#,##0"/></td>						
						<td align="right"><fmt:formatNumber value="${detail.totalMoney}" pattern="#,##0"/></td>
						<td style= "word-break:keep-all;width:200px">					
							<c:if test="${detail.materialOut.billStatus.number=='0'}">								
								<a class="submitLinkClass"
									href="<c:url value="doDelBillDetail?id=${detail.id}"/>">删除明细</a>
								<a class="editDetailLinkClass"
									href="<c:url value="/materialOutBillEdit/showPage?id=${detail.id}"/>">编辑明细</a>
								<a class="submitLinkClass"
									href="<c:url value="doDelBill?id=${detail.materialOut.id}"/>">删除单据</a>
								<a class="submitLinkClass"
									href="<c:url value="doSubmit?id=${detail.materialOut.id}"/>">提交单据</a>
							</c:if>	
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
