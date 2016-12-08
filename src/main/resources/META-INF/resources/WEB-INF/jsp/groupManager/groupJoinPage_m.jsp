<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript">

$(function(){
	if ("${msg}" != "") {
		alert("${msg}");
	}
	
	$("#code").change(function(){
		$("#wordCnt").text($("#code").val().length + "자");
		
	});
	
});

var validate = function(){
	
	var pw_pattern = /^[a-z0-9]{1,30}/g;
	var code_pattern = /^[a-z]{45}$/;
	
	if(!code_pattern.test($("#code").val())) {
		alert("45자의 영소문자 코드를 입력해야합니다.");
		return false;	
	} else if(!pw_pattern.test($("#pw").val())) {
		alert("비밀번호 입력이 잘못되었습니다.");
		return false;
	} else {
		return true;
	}
}

</script>

</head>
<body>
<jsp:include page="../include/mobile_main_menu.jsp"/>
<form action="joinGroup" method="post" onsubmit="return validate()">
		<label for="code">그룹 코드: &nbsp;&nbsp;<sub id="wordCnt">0자</sub></label>
		<input type="text" id="code" name="code" autocomplete="off" placeholder="그룹 코드 영소문자 45글자">

		<label for="pw">그룹 비밀번호:</label> 
		<input type="password" id="pw" name="pw" placeholder="비밀번호 영소문자/숫자 1~30자">
		<div data-role="controlgroup" data-type="horizontal">
			<button type="submit" class="ui-btn ui-btn-inline">가입하기</button>
			<a href="/parcel/main" class="ui-btn ui-btn-inline">돌아가기</a>
		</div>
</form>

</body>
</html>