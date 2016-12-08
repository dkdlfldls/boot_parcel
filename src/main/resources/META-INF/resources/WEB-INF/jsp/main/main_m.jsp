<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
</head>
<body>
<jsp:include page="../include/mobile_main_menu.jsp"/> <!-- 메뉴 추가 -->

	<br/>
	<table data-role="table" id="my-table" data-mode="column">
		<thead>
			<tr>
				<th>No</th>
				<th>택배함 이름</th>
				<th>개폐여부</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="status" items="${listEntity}">
				<tr>
					<td>${status.count }.</td>
					<td><a href="/product/getProductInfo?pidx=${i.pidx }">${i.pname }</a></td>
					<td>
						<c:if test="${i.isopen eq 0}">사용가능</c:if> 
						<c:if test="${i.isopen eq 1}">사용중</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<br/><br/>
	<div style="overflow: hidden; " data-role="footer" data-position="fixed">
	    <h4 style="text-align: center;">관리 메뉴</h4>
	    <div data-role="navbar">
	        <ul>
	            <li><a href="/product/addPage">택배함 등록</a></li>
	            <li><a href="/group/joinGroup">다른 그룹 가입</a></li>
	            <li><a href="/group/invite">그룹에 초대</a></li>
	        </ul>
	    </div><!-- /navbar -->
	</div><!-- /footer -->
	
	
</html>