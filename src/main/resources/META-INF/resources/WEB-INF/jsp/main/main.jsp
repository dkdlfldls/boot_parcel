<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

</head>
<body>


<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/> <!-- 메뉴 추가 -->
		</div>
		<div class="row">
		<br/>
			<div class="col-sm-9"><!-- 메인정보 -->
					<table class="table table-hover">
						<thead>
							<tr>
								<th></th>
								<th>택배함 이름</th>
								<th>공동 사용자 수</th>
								<th>택배함 주인</th>
								<th>현재 개폐여부</th>
							</tr>
						</thead>
						<tbody class="middle-align">
							<c:forEach var="i" varStatus="status" items="${listEntity}">
								<tr>
									<td>${status.count }.</td>
									<td class="main-name">
										<a href="/product/getProductInfo?pidx=${i.pidx }">${i.pname }</a>
									</td>
									<td>
										<c:if test="${i.countg eq 0 }">그룹 없음</c:if>
										<c:if test="${i.countg ne 0 }">${i.countg }</c:if>
									</td>
									<td>
										${i.name }
									</td>
									<td>
										<c:if test="${i.isopen eq 0}">사용가능</c:if>
										<c:if test="${i.isopen eq 1}">사용중</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			<div class="col-sm-3">
				<div><!-- 사용자 메뉴 -->
					<jsp:include page="../include/simpleUserInfo.jsp"></jsp:include>
				</div>
				<div><!-- 빠른메뉴 -->
					<div class="panel panel-success">
							<div class="panel-heading">관리 메뉴</div>
							<div class="panel-body">
								<a href="/product/addPage">택배함 등록하기</a><br/>
								<a href="/group/joinGroup">다른 그룹에 가입</a><br/>
								<a href="/group/invite">다른 사용자 초대하기</a><br/>
								선택한 택배함 그룹 닫기
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-2"></div>
</div>
</body>
</html>