<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
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
<jsp:include page="../include/mobile_main_menu.jsp"/>
<br/>
<fieldset data-role="controlgroup">
        <input type="radio" name=type_radio id="radio-choice-1" checked="checked" value="id">
        <label for="radio-choice-1">ID로 초대하기</label>
        <input type="radio" name="type_radio" id="radio-choice-2" value="email">
        <label for="radio-choice-2">Email로 초대하기</label>
</fieldset>

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
<table>
	<thead>
		<tr>
   			<th style="width: 10%"></th>
    		<th style="width: 20%">초대 방법</th>
    		<th style="width: 50%">계정</th>
    		<th style="width: 20%"></th>
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
					

</body>
</html>