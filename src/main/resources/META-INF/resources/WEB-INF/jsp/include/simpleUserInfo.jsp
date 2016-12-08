<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="panel panel-info">
	<div class="panel-heading">로그인 정보</div>
	<div class="panel-body">
		이름 : ${userEntity.name }<br /> 메시지 : ${userEntity.countm }건<br />
		<a href="/logout"><span class="glyphicon glyphicon-log-out"></span> 로그아웃</a><br/>
		<a href="/user/infoModify"><span class="glyphicon glyphicon-pencil"></span> 개인정보 수정</a>
	</div>
</div>