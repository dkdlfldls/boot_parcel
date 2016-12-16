<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript" src="/resources/javascript/admin/admin_product.js"></script>
<script type="text/javascript">
$(function(){
	//처음에 불러오도록
	admin.getLogByPageAndSearch("product-table-body", "pagination", 1, 20, $("#searchText").val(), $("#category").val(), false);
	
	//검색버튼
	$("#search").click(function(){
		admin.getLogByPageAndSearch("product-table-body", "pagination", 1, 20, $("#searchText").val(), $("#category").val(), true);
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
				<li ><a href="/admin/log">로그보기</a></li>
				<li><a href="/admin/machine">기종관리</a></li>
			  	<li class="active"><a href="/admin/productPage">제품관리</a></li>
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
								<option value="machine">--기종--</option>
								<option value="machine_code">--제품 코드--</option>
								<option value="registrant">--등록자 idx--</option>
							</select>
						</div>
					</div>
					
				<br/>
				</div>
					<table class="table table-condensed">
						<thead>
							<tr>
								<th style="width: 10%">No</th>
								<th>기종</th>
								<th>제품 코드</th>
								<th>주인ID</th>
							</tr>
						</thead>
						<tbody id="product-table-body">
							<tr>
								<td>1</td>
								<td>큰거</td>
								<td>123712498</td>
								<td>main</td>
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