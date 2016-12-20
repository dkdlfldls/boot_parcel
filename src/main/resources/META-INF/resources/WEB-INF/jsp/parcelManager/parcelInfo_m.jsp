<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
<link rel="stylesheet" href="<spring:url value='/resources/css/chat_board.css'/>">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script type="text/javascript" src="/resources/javascript/parcel/parcelInfo.js"></script>

<script type="text/javascript" src="<spring:url value='/resources/javascript/sockjs/sockjs-1.1.1.min.js'/>"></script>
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript">
$(function(){
	$("#message").prop("disabled", "false");
	$("#sendMessage").prop("disabled", "false");
});

var group_delete_request = function() {
	$.ajax({
		url : "../group/deleteGroup",
		contentType : "application/json",
		type : "post",
		data : JSON.stringify({
			idx : "${group.idx}",
			pw : $("#check_pw_for_group_delete").val()
		}),
		success : function(data, status) {
			if (data == 2) {
				alert("그룹을 삭제하였습니다.");
				location.href= "";
				
			} else if (data == 1) {
				alert("에러가 발생하였습니다.");
			} else {
				alert("비밀번호를 잘못 입력하였습니다.");
			}
		}
	});
}

$(function(){
	var parcelInfo = {};
	parcelInfo.open = function(locker){
		$.ajax({
			url : "open",
			contentType : "application/json",
			type : "post",
			data : JSON.stringify({
				idx : "${userEntity.idx}",
				productIdx : "${product.idx}",
				hasGroup : "${hasGroup}",
				productName : "${product.public_name }"
			}),
			success : function(data, status) {
				if (data == true) {
					$("#locker").text("잠그기");
					$("#isOpen").text("사용가능");
				} else {
					alert("열기 실패");
				}
			}
		});
	};

	parcelInfo.close = function(locker){
		$.ajax({
			url : "lock",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				idx : "${userEntity.idx}",
				productIdx : "${product.idx}",
				hasGroup : "${hasGroup}",
				productName : "${product.public_name }"
			}),
			success: function(data, status){
				if (data == true) {
					$("#locker").text("열기");
					$("#isOpen").text("사용중");
				} else {
					alert("잠그기 실패");
				}
			}
		});
	}

		
		$("#locker").click(function(){
			if($("#locker").attr("data-lock") == 0) {//열려있고
				$("#locker").attr("data-lock", "1");
				parcelInfo.close($("#locker"));
			} else if($("#locker").attr("data-lock") == 1) { //닫혀있고
				$("#locker").attr("data-lock", "0");
				parcelInfo.open($("#locker"));
			}
		});

		
	});//ready 함수 끝
	
	
	
	//----- 그룹 채팅 -----
	$(function(){
		
		var makeMessage = function(message, group, type, name) {
			obj = {};
			obj.message = message;
			obj.group = group;
			obj.type = type;
			obj.name = name;
			obj.userIdx =  "${userEntity.idx }";
			return obj;
		}
		
		
		$("#group_chat_btn").click(function(){
			if ($("#group_chat_btn").attr("data") == 0) {
				$("#group_chat_btn").attr("data",1);
				$("#group_chat_btn").text("그룹 채팅 닫기");
				//채팅 창 열고 기타 등등 
				
				sock = new SockJS("/echo-ws");
				
				sock.onopen = function() {
					message = makeMessage("", "${group.idx }", "1", "${userEntity.name }") 
					sock.send(JSON.stringify(message)); 
				};
				sock.onmessage = function(evt) {
					var message = JSON.parse(evt.data);
					console.log(message);
					if (message.type == "1") {//connect
						var id = "access-check-" + message.userIdx;
						$("#" + id).removeClass("glyphicon-remove");
						$("#" + id).addClass("glyphicon-ok");
					} else if (message.type == "3") { //break connect
						var id = "access-check-" + message.userIdx;
						$("#" + id).removeClass("glyphicon-ok");
						$("#" + id).addClass("glyphicon-remove");
					} else if (message.type == "2") { //message
						$("#chatMessage").append(message.message + "<br/>");
					}
					
				};
				sock.onclose = function() {
					message = makeMessage("", "${group.idx }", "3", "${userEntity.name }");
					sock.send(JSON.stringify(message)); 
				}
				//chat-div 보이게 하기
				$("#message").removeProp("disabled");
				$("#sendMessage").removeProp("disabled");
				$("#chatMessage").append("-----채팅 시작-----" + "<br/>");
			} else {
				$("#group_chat_btn").attr("data",0);
				$("#group_chat_btn").text("그룹 채팅 열기");
				//채팅창 닫고 기타 등등
				sock.onclose();
				sock.close();
				//chat-div 안보이게 하기
				$("#access-check-${userEntity.idx }").removeClass("glyphicon-ok");
				$("#access-check-${userEntity.idx }").addClass("glyphicon-remove");			
				$("#message").prop("disabled", "false");
				$("#sendMessage").prop("disabled", "false");
				$("#chatMessage").append("-----채팅 종료-----" + "<br/>");
			}
			
		});
		
		$("#sendMessage").click(function(){
			if ($("#message").val() != "") {
				
				message = makeMessage($("#message").val(), "${group.idx }", "2", "${userEntity.name }");
				
				sock.send(JSON.stringify(message));
				$("#message").val("");
			}
		})
		
		
		
	});
	
	//그룹 생성
	$(function() {
		
		$("#take_group_code").click(function() {
			$.ajax({
				url : "/group/makeGroupCode",
				contentType : "application/json",
				type : "post",
				success : function(data, status) {
					if (data == "실패") {
						alert("코드를 받는데 실패하였습니다.")
					} else {
						$("#code").val(data);
					}
				}
			});
		});
		$("#group_add_button").click(function() {
			
			var group_name_pattern = /^[\sA-Za-z가-힣0-9]{1,30}/g;
			var pw_pattern = /^[a-z0-9]{1,30}/g;
			var code_pattern = /^[a-z]{45}$/;
			
			if (!group_name_pattern.test($("#group_name").val())) {
				alert("그룹 이름 입력이 잘못되었습니다.");
				return;
			} else if(!pw_pattern.test($("#pw").val())) {
				alert("비밀번호 입력이 잘못되었습니다.");
				return;
			} else if(!code_pattern.test($("#code").val())) {
				alert("코드 받기 버튼을 눌러주세요");
				return;
			}
			
			$.ajax({
				url : "/group/addGroup",
				contentType : "application/json",
				type : "post",
				data : JSON.stringify({
					group_name : $("#group_name").val(),
					pw : $("#pw").val(),
					code : $("#code").val(),
					product : "${product.idx}"
				}),
				success : function(data, status) {
					if (data == false) {
						alert("그룹을 만드는데 실패하였습니다.")
					} else {
						//user_group 데이터를 받긴하는데 시간상 일단은 페이지를 다시 불러오도록 한다.
						location.href="";
					}
				}
			});
		});
	})
	
</script>

</head>
<body>
<jsp:include page="../include/mobile_main_menu.jsp"/>

	<table data-role="table" id="my-table" data-mode="reflow">
		<thead>
			<tr>
				<th>이름</th>
				<th>개폐상태</th>
				<th>등록자</th>
				<th>등록일</th>
				<th>기종</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${product.public_name }</td>
				<td>
					<b id="isOpen">
					<c:if test="${product.is_open eq 0 }">사용가능</c:if> 
					<c:if test="${product.is_open ne 0 }">사용중</c:if>
					</b>
				</td>
				<td>${product.registrant_name }</td>
				<td><fmt:formatDate value="${product.registration_date }" pattern="yyyy년 MM월 dd일"/></td>
				<td>${product.machine_name }</td>
			</tr>
		</tbody>
	</table>
	<c:if test="${hasGroup eq false}">
		<div data-role="controlgroup" data-type="horizontal">
			<c:if test="${product.is_open eq 0 }"><button type="button" id="locker" data-lock="0" class="ui-btn ui-btn-inline">잠그기</button></c:if>
			<c:if test="${product.is_open ne 0 }"><button type="button" id="locker" data-lock="1" class="ui-btn ui-btn-inline">열기</button></c:if>
		</div>
			<br/>
			그룹 없음
			<br/>
			<div id="groupMakeForm">
				<!-- 그룹 만들기 form부분 -->
				<div id="groupMakeHTML" >
					<div>
						<div data-role="header">
							<h3>공유 그룹 만들기</h3>
						</div>
						<div class="ui-content" role="main">
					    	<form>
					    		<label for="email">그룹 이름:</label>
					    		<input type="text" class="form-control" id="group_name" placeholder="그룹 이름 영문/숫자/띄어쓰기/한글 1~30자" autocomplete="off">
					    		<label for="pwd">그룹 비밀번호:</label>
					    		<input type="password" class="form-control" id="pw" placeholder="비밀번호 영문-소문자/숫자 1~30자" autocomplete="off">
					    		<label for="pwd">그룹 코드:</label>
					    		<input type="text" disabled="disabled" class="form-control" id="code" placeholder="코드받기 를 눌러주세요">
					    		<div data-role="controlgroup" data-type="horizontal">
						    		<button type="button" class="ui-btn ui-btn-inline" id="take_group_code">코드 받기</button>
						    		<button type="button" class="ui-btn ui-btn-inline" id="group_add_button">그룹 만들기</button>
					    		</div>
					    	
					    	</form>
						</div>
					</div>
				</div>		
			</div>
	</c:if>
		<c:if test="${hasGroup eq true}">
		<c:if test="${product.registrant_name eq userEntity.name}">
			<div id="checkPwForm"">
			<!-- 그룹 삭제 비밀번호 삭제부분 -->
			<div id="checkPwHTML">
					<label for="search-control-group">비밀번호 확인 : </label>
					<input type="password" id="check_pw_for_group_delete" data-wrapper-class="controlgroup-textinput" data-mini="true">
					<button class="ui-btn" onclick="group_delete_request()">그룹 삭제</button>

			</div>
		</div>
		</c:if>
			<div data-role="controlgroup" data-type="horizontal">
				<c:if test="${product.is_open eq 0 }"><button type="button" id="locker" data-lock="0" class="ui-btn ui-btn-inline">잠그기</button></c:if>
				<c:if test="${product.is_open ne 0 }"><button type="button" id="locker" data-lock="1" class="ui-btn ui-btn-inline">열기</button></c:if>
			</div>

		
		<br/>
		그룹명 : ${group.group_name }<br/>
		pw : ${group.pw }<br/>
		code : ${group.code }<br/>
		idx : ${group.idx }<br/>
		
		<c:forEach var="user" varStatus="status" items="${group.groupUserList }">  
			<p>${status.count }.${user.name } : <span id="access-check-${user.idx }" class="glyphicon"></span></p>
		</c:forEach>
		
	
	</c:if>
	<br/>
	<!-- 채팅창 부분 시작 -->
	<button id="group_chat_btn" data="0" >그룹 채팅 열기</button>
	<div id="chat-div">
		<div class="chat_board" id="chatMessage"></div>
		<input type="text" id="message">
		<input type="button" value="입력" id="sendMessage">
	</div>
	<!-- 채팅창 부분 끝 -->
	





</body>


</html>