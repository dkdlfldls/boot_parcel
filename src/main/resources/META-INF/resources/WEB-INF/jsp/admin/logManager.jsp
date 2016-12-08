<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript" src="/resources/javascript/admin/admin_main.js"></script>
<script type="text/javascript">
$(function(){
	//처음에 불러오도록
	admin.getLogByPage("log-table-body", "pagination", 1, 20, $("#category").val());	
	
	//검색버튼
	$("#search").click(function(){
		admin.getLogByPageAndSearch("log-table-body", "pagination", 1, 20, $("#searchText").val(), $("#category").val());
	})
	
	//카테고리 변경시
	$("#category").change(function(){
		admin.getLogByPage("log-table-body", "pagination", 1, 20, $("#category").val());
	})
})
</script>
<body>



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<br/>
			<H2>관리자 페이지</H2>
			<ul class="nav nav-tabs">
				<li class="active"><a href="/admin/log">로그보기</a></li>
				<li><a href="/admin/machine">기종관리</a></li>
			  	<li><a href="">제품관리</a></li>
			  	<li><a href="#">메뉴 3</a></li>
			</ul>
		</div>
		<div class="row">
			<div class="col-sm-9"><!-- 메인정보 --><br/>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-6">
							<input type="text" class="form-control col-sm-2" style="width: 70%" id="searchText">
							<input type="button" class="btn btn-success" value="검색" id="search">
						</div>
						<div class="col-sm-6 right-align">
							<select class="form-control" style="width: 150px;" id="category">
								<option value="0">--전체 목록--</option>
								<option value="120">--로그인--</option>
								<option value="110">--회원가입--</option>
								<option value="410">--그룹 생성--</option>
								<option value="420">--그룹 가입--</option>
								<option value="440">--그룹 탈퇴--</option>
								<option value="430">--그룹 삭제--</option>
								<option value="320">--택배함 열기--</option>
								<option value="330">--택배함 닫기--</option>
							</select>
						
						</div>
					</div>
					
				<br/>
				</div>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th style="width: 10%">No</th>
								<th>내용</th>
								<th style="width: 30%">시간</th>
							</tr>
						</thead>
						<tbody id="log-table-body">
							<tr>
								<td>John</td>
								<td>Doe</td>
								<td>john@example.com</td>
							</tr>
						</tbody>
					</table>
					
					<!-- 페이징 부분 -->
					<div style="text-align: center;">
						<ul class="pagination" id="pagination">
						
						</ul>
					</div>
					<!--  페이지 끝 -->
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

</html>