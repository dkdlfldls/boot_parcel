<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<style type="text/css">
.numClass {
	padding-top: 7px;
}
</style>
<script type="text/javascript" src="/resources/js/admin/admin_machine.js"></script>


<body>



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<br/>
			<H2>관리자 페이지</H2>
			<ul class="nav nav-tabs">
				<li><a href="/admin/log">로그보기</a></li>
				<li class="active"><a href="/admin/machine">기종관리</a></li>
			  	<li><a href="#">제품관리</a></li>
			  	<li><a href="#">메뉴 3</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-sm-9"><!-- 메인정보 --><br/>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th>번호</th>
								<th>기종 이름</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="machineTable">
							<c:forEach var="machine" items="${machineList }" varStatus="status">
								<tr>
									<td>
										<label class="numClass">${status.count }.</label>	
									</td>
									<td>
										<input type="text" class="form-control" id="machine-name-${machine.idx }" disabled="disabled" value="${machine.machine_name }">
									</td>
									<td>
										<input type="button" class="btn btn-default" id="machine-modify-btn-${machine.idx }" data="${machine.idx }" value="수정" toggle="off" onclick="clickModifyBtn(this)">
										<input type="button" class="btn btn-danger" id="machine-delete-btn-${machine.idx }" data="${machine.idx }" value="삭제" onclick="clickDeleteBtn(this)">
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<div class="input-group middle-align" style="width: 450px;">
					<label class="input-group-addon" for="usr">기종 이름 : </label>
						<input type="text" class="form-control" id="add-machine-name">
						<div class="input-group-btn">
							<input type="button" class="btn btn-primary" value="추가" id="add-machine-btn">					
						</div>
				</div>
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

<script type="text/javascript">


$(function(){
	$("#add-machine-btn").click(function(){
		$.ajax({
			url : "/admin/addMachine",
			contentType : "application/json",
			type : "post",
			data : JSON.stringify({
				machine_name : $("#add-machine-name").val()
			}),
			success : function(data, status){
				console.log(data);
				if (data) {
					location.href="";
					alert("입력 성공");
					
				} else {
					alert("입력 실패");
				}
				
			}
		})
	})
	
	var addMachineDOM = function(){
		var tr = document.createElement("tr");
	}
})
//삭제 버튼 클릭
var clickDeleteBtn = function(btn) {
	$.ajax({
		url : "/admin/deleteMachine",
		contentType : "application/json",
		type : "post",
		data : JSON.stringify({
			idx : $(btn).attr("data")
		}),
		success : function(data, status){
			console.log(data);
			if (data) {
				alert("삭제 성공");
				location.href="";
			} else {
				alert("삭제 실패");
			}
			
		}
	})
}

//수정 버튼 클릭
var clickModifyBtn = function(btn){
	if ($(btn).attr("toggle") == 'off') {
		//input 활성화
		$("#machine-name-" + $(btn).attr("data")).prop("disabled", false);
		$(btn).val("저장");
		$(btn).attr("toggle" , 'on');
	} else{
		//저장 처리 하기
		//ajax로 저장처리 하기
		console.log($(btn).attr("data"));
		console.log($("#machine-name-" + $(btn).attr("data")).val());
		$.ajax({
			url : "/admin/modifyMachine",
			contentType : "application/json",
			type : "post",
			data : JSON.stringify({
				idx : $(btn).attr("data"),
				machine_name : $("#machine-name-" + $(btn).attr("data")).val()
			}),
			success : function(data, status){
				console.log(data);
				if (data) {
					alert("수정 성공");
				} else {
					alert("수정 실패");
				}
				
			}
		})
		//저장 결과 alert로 나타내기
		//input 비활성화
		$("#machine-name-" + $(btn).attr("data")).prop("disabled", true);
		$(btn).val("수정");
		$(btn).attr("toggle" , 'off');
	}
	//console.log($(btn).attr("data"));
}

</script>

</html>

