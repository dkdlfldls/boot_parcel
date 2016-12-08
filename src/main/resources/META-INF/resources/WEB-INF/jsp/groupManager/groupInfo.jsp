<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value='/resources/html/include.html'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/nsh_public.css'/>">
<link rel="stylesheet" href="<spring:url value='/resources/css/groupInfo.css'/>">

</head>
<body>



<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div><!-- 메뉴공간 -->
			<jsp:include page="../include/main_menu_nev.jsp"/>
		</div>
		<div class="row">
				<div class="col-sm-9">
					<!-- 메인정보 -->
					<div class="right-align">
						<a class="btn btn-success" href="/group/invitedList">초대받은 목록 보기</a>					
						<a class="btn btn-success" href="/group/invite">다른 사용자 초대하기</a>					
					</div>
					<div class="panel-default">
						<div class="panel-heading">
							<ul class="list-group">
								<li class="list-group-item group-info-list">
									<table class="table table-hover group-info-table">
										<thead>
											<tr >
												<th class="middle-align"></th>
												<th class="middle-align">그룹 이름</th>
												<th class="middle-align">택배함 이름</th>
												<th class="middle-align">택배함 주인</th>
												<th class="middle-align">그룹 인원 수</th>
												<th class="middle-align"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="i" varStatus="status" items="${groupList}">
												<tr class="middle-align">
													<td>${status.count }</td>
													<td>
														<a href="/product/getProductInfo?pidx=${i.product }">${i.group_name }</a>
													</td>
													<td>${i.productName }</td>
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
								</li>
							</ul>
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
</body>
</html>