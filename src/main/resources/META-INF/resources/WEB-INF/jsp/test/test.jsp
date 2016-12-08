<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">

<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/parcelInfo.css'/>">

<script type="text/javascript" src="<spring:url value='/resources/js/sockjs/sockjs-1.1.1.min.js'/>"></script>
<script type="text/javascript">

	sock = new SockJS("/echo-ws");
	sock.onmessage = function(evt) {
		$("#chatMessage").append(evt.data + "<br/>");
	}
	
	$(function(){
		

		
		$("#sendMessage").click(function(){
			if ($("#message").val() != "") {
				
				message = {};
				message.message = $("#message").val();
				message.group = "aaa";
				
				sock.send(JSON.stringify(message));
				$("#chatMessage").append("나 -> : " + $("#message").val() + "<br/>");
				$("#message").val("");
			}
		})
		
	});

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
		<br/>
			<div class="col-sm-9"><!-- 메인정보 -->
				<input type="text" id="message"/>
				<input type="button" id="sendMessage" value="메시지 보내기"/>
				<div id="chatMessage" style="overFlow:auto; max-height:500px;"></div>
				
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