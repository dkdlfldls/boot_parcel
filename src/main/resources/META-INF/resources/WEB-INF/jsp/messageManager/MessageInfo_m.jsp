<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/WEB-INF/jsp/include/mobile_include.jsp"/>
<link rel="stylesheet"  href="<spring:url value='/resources/css/bootstrap-3.3.7-dist/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">

<script type="text/javascript">
	var checkMessage = function(num) {
		$.ajax({
			url : "check",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				idx : num
			}),
			success : function(data, status){
				if(data == "success") {
					$("#show" + num).text("확인함");
					$("#check" + num).text("");
				} else {
					alert("확인 실패");
				}
			}
			
		});
		
	}
</script>

</head>
<body>
<jsp:include page="../include/mobile_main_menu.jsp"/>
			
<c:if test="${isEmpty eq true }">받은 메시지가 없습니다.</c:if>
<c:if test="${isEmpty eq false }">
	<table>
		<thead>
			<tr class="non-select">
				<th>번호</th>
				<th>메시지</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" varStatus="status" items="${messageList }">
				<tr>
					<td>${pageInfo.firstContent + status.count}. </td>
					<td>${i.message }</td>
					<td id="check${i.idx }">
						<c:if test="${i.show eq 0}"><a href="#" onclick="checkMessage(${i.idx})">확인</a></c:if>
						<c:if test="${i.show ne 0}"></c:if>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
		<!-- 페이징 부분 -->
		<div style="text-align: center;">
			<ul class="pagination">
				<c:if test="${pageInfo.firstPagination ne 1 }">
					<li><a href="/message/info?currentPage=${pageInfo.firstPagination - pageInfo.paginationSize}">prev</a></li>
				</c:if>
				<c:forEach begin="${pageInfo.firstPagination }" end="${pageInfo.lastPagination }" var="i">
					<c:if test="${i eq  pageInfo.currentPage}">
						<li class="active"><a href="/message/info?currentPage=${i }">${i }</a></li>
					</c:if>
					<c:if test="${i ne  pageInfo.currentPage}">
						<li><a href="/message/info?currentPage=${i }">${i }</a></li>
					</c:if>
				
				</c:forEach>
				<c:if test="${pageInfo.lastPagination ne pageInfo.maxPageination}">
					<li><a href="/message/info?currentPage=${pageInfo.lastPagination + 1}">next</a></li>
				</c:if>
			</ul>
		</div>
</c:if>

</body>
</html>