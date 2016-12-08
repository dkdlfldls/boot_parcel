<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div style="overflow: hidden;" data-role="header">
<h1>무인 관리 페이지</h1>
    <a class="ui-btn-right" href="#myPanel" data-icon="user">회원정보</a>
    <div data-role="navbar">
        <ul>
			<li><a href="/parcel/main">Home</a></li>
			<li><a href="/group/groupInfo">그룹 관리</a></li>
			<li><a href="/message/info?currentPage=1">메시지 관리</a></li>
			<!--  <li><a href="/intro/main">사용 설명서</a></li>-->
        </ul>
    </div><!-- /navbar -->
</div><!-- /header -->

<div data-role="panel" id="myPanel" data-position="right" data-display="overlay"> 
	<h2>사용자 정보</h2>
	<p>이름 : ${userEntity.name }</p>
	<p>메시지 : ${userEntity.countm }건</p>
	<a href="#pageone" data-rel="close" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-delete ui-btn-icon-left">닫기</a>
	<a href="/user/infoModify" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-action ui-btn-icon-edit">개인정보 수정</a>
	<a href="/logout" class="ui-btn ui-btn-inline ui-shadow ui-corner-all ui-btn-a ui-icon-action ui-btn-icon-left">로그아웃</a>
</div> 
