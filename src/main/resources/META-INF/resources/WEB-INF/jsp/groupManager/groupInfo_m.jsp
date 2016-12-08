<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

</head>
<body>
	<jsp:include page="../include/mobile_main_menu.jsp"/> <!-- 메뉴 추가 -->
	<br/>
	<div data-role="controlgroup" data-type="horizontal">
		<a class="ui-btn ui-btn-inline" href="/group/invitedList">초대받은 목록 보기</a>					
		<a class="ui-btn ui-btn-inline" href="/group/invite">다른 사용자 초대하기</a>		
	</div>
	<br/>
	<table data-role="table" id="my-table" data-mode="column">
		<thead>
			<tr>
				<th>그룹이름</th>
				<th>택배함 주인</th>
				<th>그룹 인원 수</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="status" items="${groupList}">
				<tr>
					<td>
						<a href="/product/getProductInfo?pidx=${i.product }">${i.group_name }</a>
					</td>
					<td>${i.managerName }</td>
					<td>${i.groupMemberCnt }명</td>
					<td>
						<c:if test="${i.manager eq userEntity.idx}">내 그룹</c:if>
						<c:if test="${i.manager ne userEntity.idx}"><a href="/group/drop/${i.idx }">그룹 나가기</a></c:if>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>


</body>
</html>