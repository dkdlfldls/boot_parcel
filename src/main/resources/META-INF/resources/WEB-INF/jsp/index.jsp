<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value="/resources/html/include.html"/>">

<script type="text/javascript">
	$(function(){
		$("#arduinoOn").click(function(){
			$.get("http://192.168.0.15:80/", {pin:1}); // execute get request
		});
		$("#arduinoOff").click(function(){
			$.get("http://192.168.0.15:80/", {pin:0}); // execute get request
		});
	});

</script>

</head>
<body>
	<!--  
	<a href="#" id="arduinoOn">on</a>
	<a href="#" id="arduinoOff">off</a>
	-->
	<div class="row">
		<div class="col-xs-2"></div>
		<div class="col-xs-8">
			<div style="width: 100px"></div>
				<h1>login page</h1>
				<div class="row">
					<div class="col-xs-2"></div>
					<div class="col-xs-8">
						<form action="login" method="post">
							<div class="form-group">
								<label>id</label>
								<input type="text" name="id" autocomplete="off" placeholder="id" class="form-control">
								<br/>		
							</div>
							<div class="form-group">
								<label>pw</label>
								<input type="password" name="pw" placeholder="pw" class="form-control">
								<br/>
								
								<input type="submit" class="btn btn-default" value="로그인">
								<a href="join" class="btn btn-default">가입</a>
								<a href="/intro/main" class="btn btn-default">사용 설명서</a>
							</div>
						</form>
					</div>
					<div class="col-xs-2"></div>
				
				</div>
			
		</div>
		<div class="col-xs-2"></div>
	
	
	</div>
	
</body>
</html>