<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript">

var validate = function(){
	return true;
	var idPattern = /^[A-Za-z0-9]{1,20}/g; 
	var pwPattern = /^[A-Za-z0-9]{1,20}/g;
	var namePattern = /^[A-Za-z가-힣]{1,30}/g;
	var emailPattern = /^[a-z][a-z0-9_-]{0,15}@([a-z\d\.-]{1,10})\.([a-z\.]{1,6})$/;
	var phonePattern = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/g;
	
	if(!idPattern.test($("#id").val())) {
		alert("id 입력이 잘못되었습니다.");
		return false;
	} else if (!pwPattern.test($("#pw").val())) {
		alert("pw 입력이 잘못되었습니다.");
		return false;
	} else if (!namePattern.test($("#name").val())) {
		alert("name 입력이 잘못되었습니다.");
		return false;
	} else if (!emailPattern.test($("#email").val())) {
		alert("email 입력이 잘못되었습니다.");
		return false;
	} else if (!phonePattern.test($("#phone").val())) {
		alert("phone 입력이 잘못되었습니다.");
		return false;
	} else {
		return true;
	}
}

</script>

<body>
<jsp:include page="../include/mobile_main_menu.jsp"/>

<form:form action="infoModify" method="post" onsubmit="return validate()" modelAttribute="user">
	<div class="form-group">
		<label>아이디</label>
		<form:input type="text" name="id" id="id" path="id" readonly="true"
			autocomplete="off" placeholder="id 영문/숫자 1~20글자"
			class="form-control"/>
		<form:errors path="id"/>
	</div>
	<div class="form-group">
		<label>비밀번호</label>
		<form:input type="password" name="pw" id="pw" path="pw"
			placeholder="pw 영문/숫자 1~20글자" class="form-control"/>
			<form:errors path="pw"/>
	</div>
	<div class="form-group">
		<label>이름</label>
		<form:input type="text" name="name" id="name" path="name"
			autocomplete="off" placeholder="name 영문/한글 1~30글자 이하"
			class="form-control"/>
	</div>
	<div class="form-group">
		<label>전화번호</label>
		<form:input type="tel" name="phone" id="phone" path="phone"
			autocomplete="off" placeholder="phone ex)02-1234-5678"
			class="form-control"/>
	</div>
	<div class="form-group">
		<label>이메일</label>
		<form:input type="email" name="email" id="email" path="email"
			autocomplete="off" placeholder="email ex a@a.a"
			class="form-control"/>
	</div>
	<div data-role="controlgroup" data-type="horizontal">
		<input type="submit" class="ui-btn ui-btn-inline" value="제출"> 
		<a href="main"type="button" class="ui-btn ui-btn-inline">뒤로가기</a>	
	</div>
</form:form>

</html>