<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
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



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/>
		</div>
		<div class="row">
			<div class="col-sm-9"><!-- 메인정보 -->
					<br/>
					<form action="joinGroup" method="post" onsubmit="return validate()">
						<div class="form-group">
							<label for="code">그룹 코드: </label>&nbsp;&nbsp;<label id="wordCnt">0자</label>
							<input type="text" class="form-control" id="code" name="code" autocomplete="off" placeholder="그룹 코드 영소문자 45글자">
						</div>
						<div class="form-group">
							<label for="pw">그룹 비밀번호:</label> 
							<input type="password" class="form-control" id="pw" name="pw" placeholder="비밀번호 영소문자/숫자 1~30자">
						</div>
						<div class="form-group right-align">
							<button type="submit" class="btn btn-default">가입하기</button>
							<a href="/parcel/main" class="btn btn-default">돌아가기</a>
						</div>
					</form>
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