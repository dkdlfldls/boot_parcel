<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/groupInfo.css'/>">

<script type="text/javascript">

var invitationAccept = function(acceptance){
	$("#invitationForm").attr("action", "/group/permit");
	$("#acceptanceValue").val(acceptance);
	$("#invitationForm").submit();
	
}

</script>


</head>
<body>
<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/>
		</div>
		<div class="row">
				<div class="col-sm-9"><!-- 메인정보 -->
					초대받은 리스트<br/>
					<table class="table table-condensed">
						<thead>
							<tr>
					   			<th style="width: 10%"></th>
					    		<th style="width: 20%">초청자</th>
					    		<th>그룹이름</th>
					    		<th></th>
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
											<input type="button" class="btn btn-info" value="수락" onclick="invitationAccept(true)">
											<input type="button" class="btn btn-danger" value="거절" onclick="invitationAccept(false)">
										</form>
									</td>
								</tr>
							
							</c:forEach>
						</tbody>
					</table>
					
				</div>
				<div class="col-sm-3">
				<div><!-- 사용자 메뉴 -->
						<jsp:include page="../include/simpleUserInfo.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-2"></div>
</div>
</body>
</html>