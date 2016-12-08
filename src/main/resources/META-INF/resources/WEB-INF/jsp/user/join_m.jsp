<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>

<script type="text/javascript">

var validate = function(){
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

$(function(){
	$("#backPageBtn").click(function(){
		location.href="../";
	})
})

</script>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>join page</h1>
	<form action="userJoin" method="post" onsubmit="return validate()">

		<label>아이디</label><input type="text" name="id" id="id" autocomplete="off" placeholder="id 영문/숫자 1~20글자" ><br/>
	
		<label>비밀번호</label><input type="password" name="pw" id="pw" placeholder="pw 영문/숫자 1~20글자" ><br/>

		<label>이름</label><input type="text" name="name" id="name" autocomplete="off" placeholder="name 영문/한글 1~30글자 이하" ><br/>
	
		<label>전화번호</label><input type="tel" name="phone" id="phone" autocomplete="off" placeholder="phone ex)02-1234-5678" ><br/>
	
		<label>이메일</label><input type="email" name="email" id="email" autocomplete="off" placeholder="email ex a@a.a" ><br/>
		
		<input type="submit" value="가입하기">
		<input type="button" id="backPageBtn" type="button" value="뒤로가기">
	</form>
		
</body>
</html>