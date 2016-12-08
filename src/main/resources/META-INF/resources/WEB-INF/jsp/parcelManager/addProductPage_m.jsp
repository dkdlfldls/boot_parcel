<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript">

$(function(){
	
	$("#addProduct").click(function(){
		//1. 예외처리? validation
		// 일단 machine이 0이면 안된다.
		// public_name을 한영숫자 이정도로 글자수제한 먹여주도록 하자.
		
		var pattern = /^[\sA-Za-z가-힣0-9]{2,30}/g; //한글, 영문자, 숫자, 띄어쓰기 2~30글자
		var codePattern = /^[a-z0-9]{1,45}/g; //영소문자, 숫자 1~45글자
		console.log(pattern.test($("#public_name").val()));
		if ($("#machine").val() == 0) {
			alert("택배함 종류를 항목에서 선택하여 주세요");
			return;
		}else if( pattern.test(  $("#public_name").val() == false  )  ) {
			alert("한글, 영문자, 숫자, 띄어쓰기를 사용하며 2에서 30글자 사이로 작성하여 주세요");
			return;
		}else if (!codePattern.test($("#machine_code").val())) {
			alert("택배함 고유코드를 입력하세요");
			return;
		}
		
		//2.ajax처리
		//성공시 화면 이동 실패시 실패원인 alert
		$.ajax({
					url: "addProduct",
					contentType: "application/json",
					type : "post",
					data : JSON.stringify({
						machine : $("#machine").val(),
						machine_code : $("#machine_code").val(),
						public_name : $("#public_name").val()
					}),
					success: function(data, status){
						if (data == "등록 완료") {
							alert("등록이 완료되었습니다.");
							location.href="/parcel/main";
						} else {
							alert(data);
							return;
						}
					}
        	}
		);
	})	
});
</script>

</head>
<body>

<jsp:include page="../include/mobile_main_menu.jsp"/>
	<h3>택배함 추가하기</h3>
		<form>
			<label for="machine">택배함 종류:</label> 
			<select class="form-control" id="machine">
				<option value="0">택배함 종류를 선택하세요</option>
				<c:forEach var="e" items="${machineList}">
					<option value="${e.idx }">${e.machine_name}</option>
				</c:forEach>
				
			</select>

			<label for="machine_code">택배함 고유 코드:</label> 
			<input type="text" class="" id="machine_code" placeholder="영소문자, 숫자 1~45글자">

			<label for="public_name">사용할 택배함 이름:</label> 
			<input type="text" class="" id="public_name" placeholder="한글, 영문자, 숫자, 띄어쓰기 2~30글자">
			<div data-role="controlgroup" data-type="horizontal">
				<button type="button" class="ui-btn ui-btn-inline" id="addProduct">추가하기</button>
				<a href="/parcel/main" type="button" class="ui-btn ui-btn-inline">돌아가기</a>
			</div>
		</form>
</body>
</html>