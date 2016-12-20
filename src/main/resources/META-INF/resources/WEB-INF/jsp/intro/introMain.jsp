<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="import" href="<spring:url value="/resources/html/include.html"/>">

<script type="text/javascript">

var clickTab = function(tabNum){
	var id = "m" + tabNum;
	$("#" + id).trigger("click");
}

</script>

<title>Insert title here</title>
</head>
<body>

	<ul class="nav nav-tabs">
		<li><a href="/">첫 페이지로</a></li>
		<li class="active"><a data-toggle="tab" href="#menu0" id="m0">Intro</a></li>
		<li><a data-toggle="tab" href="#menu1" id="m1">1. 로그인/회원가입</a></li>
		<li><a data-toggle="tab" href="#menu2" id="m2">2. 제품 등록/사용</a></li>
		<li><a data-toggle="tab" href="#menu3" id="m3">3. 그룹 생성/관리</a></li>
		<li><a data-toggle="tab" href="#menu4" id="m4">4. 그룹에 초대/다른 그룹 가입</a></li>
		<li><a data-toggle="tab" href="#menu5" id="m5">5. 기타 기능</a></li>
	</ul>

	<div class="tab-content">
		<div id="menu0" class="tab-pane fade in active">
		<h2>원격 조정 택배함</h2>
		<p>택배를 받을 사람이 없을 때 사용하는 택배함을</p>
		<p>이제는 원격으로 조정하여 사용하세요</p>
		
		<h2>제품 특징</h2>
			<p>인터넷에 연결할 수 있다면 지구 어디에서든 택배함을 열었다 닫었다 할 수 있습니다.</p>
			<p>가족들과 혹은 친한 이웃들과 택배함을 함께 사용하세요</p>
			<p></p>
			
			<br/>
			<br/>
			<h2>간단한 사용 동영상</h2>
			<video src="/resources/video/parcel_intro.mp4" width="640" height="480" controls="controls"></video>
			<p>1. 제품을 구매합니다.</p> 
			<p>2. 아이디가 있는경우 로그인을 하고 없는경우 회원가입 이후 로그인을 합니다. <a onclick="clickTab(1)">==로그인/회원가입 방법==</a></p>
			<p>3. 구매하신 제품을 등록합니다. <a onclick="clickTab(2)">==제품 등록/사용 방법==</a></p>
			<p>4. 제품관리 메뉴에 등록된 제품을 사용할 수 있으며 필요에 따라 다른 사람들과 함께 사용할 수 있습니다.</p>
			<p><a onclick="clickTab(3)">==그룹 생성/관리 방법==</a></p>
			<p>이 외 정보를 알고싶으시다면 해당페이지의 다른 메뉴들도 읽어주세요. </p>
			<p>감사합니다.</p>
		</div>
		<div id="menu1" class="tab-pane fade">
			<h3>1-1. 로그인</h3>
			<img src="/resources/image/intro/1/loginIntro.png" class="img-rounded" width="708" height="532">
			<h3>1-2. 회원가입</h3>
			<img src="/resources/image/intro/1/joinIntro.png" class="img-rounded" width="708" height="532">
		</div>
		<div id="menu2" class="tab-pane fade">
			<h3>2-1. 제품 등록</h3>
			<p>구입한 제품에 있는 택배함 종류, 고유 코드를 입력하고 택배함의 이름을 입력하면 등록 완료</p>
			<p>메인 메뉴의 택배함 관리에서 등록된 택배함을 확인할 수 있다.</p>
			<img src="/resources/image/intro/2/1.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/2/2.png" class="img-rounded" width="708" height="532">
			<h3>2-2. 제품 사용</h3>
			<p>등록된 택배함의 이름을 클릭하여 택배함 세부정보를 확인할 수 있다.</p>
			<p>택배함 세부정보 페이지에서 열기/닫기 버튼으로 택배함을 열고 닫을 수 있다.</p>
			<img src="/resources/image/intro/2/3.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/2/4.png" class="img-rounded" width="708" height="532">
		</div>
		<div id="menu3" class="tab-pane fade">
			<h3>3-1. 그룹 생성</h3>
			<p>택배함 관리에서 그룹을 만들 택배함을 선택한다.</p>
			<p>그룹 만들기 버튼을 통해 그룹을 생성할 수 있다.</p>
			<p>그룹 이름 및 다른 사람이 가입할 떄 사용될 비밀번호 입력후 코드받기 버튼을 통해 그룹 코드를 받은뒤 추가한다.</p>
			<img src="/resources/image/intro/3/1.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/3/2.png" class="img-rounded" width="708" height="532">
			<h3>3-2. 그룹 삭제</h3>
			<p>그룹을 삭제할땐 그룹 없애기 버튼을 누르고 입력했던 비밀번호 입력하여 없앤다.</p>
			<p>메인 메뉴의 그룹관리 에서 자신이 속한 그룹을 확인할 수 있다.</p>
			<p>다른 사람의 그룹에서 나갈 수 있다.</p>
			<img src="/resources/image/intro/3/3.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/3/4.png" class="img-rounded" width="708" height="532">
		</div>
		<div id="menu4" class="tab-pane fade">
			<h3>4. 그룹에 초대/다른 그룹 가입</h3>
			<p>4-1 초대하기</p> 
			<p>메인메뉴의 그룹관리 에서 다른 사용자 초대하기 버튼이나</p>
			<p>택배함관리 메뉴에서 관리메뉴의 다른사용자 초대하기 로 다른사용자 초대 페이지로 갈 수 있다.</p>
			<p>초대할 방법을 선택하고 (ID, Email) 초대할 그룹을 선택한 후 id혹은 email을 계정 부분에 입력한뒤 초대버튼을 누른다.</p>
			<p>id로 초대한 경우 초대중인 리스트에 나와 취소할 수 있다.</p>
			<p>email로 초대한경우 해당 email로 그룹코드, 비밀번호를 전송하여 직접 가입하게 할 수있으나 취소할 수 없다.</p>
			<img src="/resources/image/intro/4/1.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/4/2.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/4/3.png" class="img-rounded" width="708" height="532">
			<h3>4-2 다른 그룹에 가입</h3>
			<p>메인 메뉴의 그룹관리에서 초대받은 목록 보기로 들어간다.</p>
			<p>초대받은 그룹이 있으면 해당 초청자와 그룹이름을 보고 그룹에 가입 혹은 거절 할 수 있다.</p>
			<img src="/resources/image/intro/4/4.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/4/5.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/4/6.png" class="img-rounded" width="708" height="532">
		</div>
		<div id="menu5" class="tab-pane fade">
			<h3>5. 기타 기능</h3>
			<p>1.그룹간 실시간 채팅</p>
			<p>택배함 세부 정보 안에서 그룹채팅 열기를 통해 채팅창을 열고 접속해있는 그룹원들과 채팅할 수 있다.</p>
			<p>채팅 내용은 저장되지 않으며 접속해 있는 인원들만 볼 수 있다.</p>
			<img src="/resources/image/intro/5/1.png" class="img-rounded" width="708" height="532">
			<img src="/resources/image/intro/5/2.png" class="img-rounded" width="708" height="532">			
			<p>2.메시지기능(기록보기)</p>
			<p>메인메뉴의 메시지관리 메뉴에서 택배함이 열렸거나 닫혔던 이력을 확인할 수 있다.</p>
			<p>미확인 메시지가 우선적으로 나온다.</p>
			<img src="/resources/image/intro/5/3.png" class="img-rounded" width="708" height="532">
		</div>
	</div>


</body>
</html>