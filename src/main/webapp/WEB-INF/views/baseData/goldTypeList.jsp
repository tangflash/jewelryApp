<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>金成色信息列表</title>

<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery-ui.custom.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery.dataTables.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/themename/jquery.dataTables_themeroller.css"/>"
	type="text/css" />
	
	
<script type="text/javascript"
	src="<c:url value="/resources/jquery/jquery.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-ui.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/jquery-dataTables.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/jquery-ui/dataTables-ini.js"/>"></script>

<script type="text/javascript">
	$(document).ready(function() {		
		iniDataTable();
	});
</script>
</head>
<body>
	<div id="formsContent">
		<table id="materialTable" class="listTable" border="1">
			<thead>
				<tr>
					<td>编码</td>
					<td>名称</td>
					<td>备注</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="goldType">
					<tr>
						<td>${goldType.number}</td>
						<td>${goldType.name}</td>
						<td>${goldType.remark}</td>
						<td>
							<a href="<c:url value="showAddPage"/>">新增</a>
							<a href="<c:url value="doDelete?id=${goldType.id}"/>">删除</a>
							<a href="<c:url value="doEdit?id=${goldType.id}"/>">修改</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>



</body>
</html>
