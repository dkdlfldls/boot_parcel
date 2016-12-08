<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/groupInfo.css'/>">

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>

<script type="text/javascript">

var invitationAccept = function(acceptance){
	$("#invitationForm").attr("action", "/group/permit");
	$("#acceptanceValue").val(acceptance);
	$("#invitationForm").submit();
}
</script>


</head>
<body>
<jsp:include page="../include/mobile_main_menu.jsp"/> <!-- 메뉴 추가 -->
<br/>
<table style="width: 100%">
	<thead>
		<tr>
   			<th style="width: 15%"></th>
    		<th>초청자</th>
    		<th>그룹이름</th>
    		<th style="width: 40%; margin: 10px;"></th>
  		</tr>
	</thead>
	<tbody>
		<c:forEach var="invite" varStatus="status" items="${inviteList }">
			<tr>
				<td>${status.count }.</td>
				<td>${invite.sender_id }</td>
				<td>${invite.group_name }</td>
				<td>
					<form action="#" method="post" id="invitationForm">
						<input type="hidden" name="acceptanceValue" value="" id="acceptanceValue">
						<input type="hidden" name="idx" value="${invite.idx}">
						<input type="hidden" name="group_idx" value="${invite.group_idx}">
						<div data-role="controlgroup" data-type="horizontal">
							<button type="button" class="ui-btn ui-btn-inline" onclick="invitationAccept(true)">수락</button>
							<button type="button" class="ui-btn ui-btn-inline" onclick="invitationAccept(false)">거절</button>
						</div>
					</form>
				</td>
			</tr>
		
		</c:forEach>
	</tbody>
</table>

</body>
</html>