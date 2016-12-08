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
if ("${result}" == "true") {
	alert("취소 성공");
} else if("${result}" == "false") {
	alert("취소 실패");
}

$(function(){
	
	
	$("#invite_btn").click(function(){
		$.ajax({
			url : "/group/invite",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				sender : "${userEntity.idx }",
				receiver_id : $("#invitee").val(),
				group_idx : $("#groupInfo").val(),  
				type : $(":radio[name='type_radio']:checked").val()
				
			}),
			success: function(data, status){
				console.log(data);
				if (data == "success") {
					location.href="";
				} else {
					alert("초대에 실패하였습니다.");
				}
				
			}
		});
	})
	
})
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
					<label class="radio-inline">
						<input type="radio" name=type_radio checked="checked" value="id">ID로 초대하기
					</label>
					<label class="radio-inline">
						<input type="radio" name="type_radio" value="email">Email로 초대하기
					</label>
					<div class="form-group">
						<label for="usr" class="control-label">초대될 그룹 : 
							<select class="form-control" id="groupInfo">
								<c:forEach var="group" items="${groupList}">
									<option value="${group.idx }">${group.group_name }</option>									
								</c:forEach>
							</select>
						</label> 
					</div>
					<div class="form-group">
						<label for="usr" class="control-label">계정 : 
							<input type="text" class="form-control" id="invitee">
							<input type="button" class="btn btn-success" value="초대" id="invite_btn">
						</label> 
					</div>

					
					초대중인 리스트<br/>
					<table class="table table-condensed">
						<thead>
							<tr>
					   			<th style="width: 10%"></th>
					    		<th style="width: 20%">초대 방법</th>
					    		<th>계정</th>
					    		<th></th>
					  		</tr>
						</thead>
						<tbody id="invite_table">
							<c:forEach var="invite" items="${inviteList }" varStatus="status">
								<tr>
									<td>${status.count }.</td>
									<td>${invite.type }</td>
									<td>${invite.receiver_id }</td>
									<td>
										<form action="/group/cancle" method="post">
											<input type="hidden" name=idx value="${invite.idx }">
											<input type="submit" class="btn btn-danger" value="취소">
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