<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div id="group_add_modal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
         <h4 class="modal-title">${product.public_name } 공유 그룹 만들기</h4>
      </div>
      <div class="modal-body">
      	<!-- 그룹 이름이랑 pw만 넣으면 된다. -->
				<form>
					<div class="form-group">
						<label for="email">그룹 이름:</label>
						<div class="row">
							<div class="col-sm-9"><input type="text" class="form-control" id="group_name" placeholder="그룹 이름 영문/숫자/띄어쓰기/한글 1~30자" autocomplete="off"></div>
							<div class="col-sm-3"></div>
						</div>
						
					</div>
					<div class="form-group">
						<label for="pwd">그룹 비밀번호:</label>
						<div class="row">
							<div class="col-sm-9"><input type="password" class="form-control" id="pw" placeholder="비밀번호 영문-소문자/숫자 1~30자" autocomplete="off"></div>
							<div class="col-sm-3"></div>
						</div> 
						
					</div>
					<div class="form-group">
						<label for="pwd">그룹 코드:</label>
						<div class="row">
							<div class="col-sm-9"><input type="text" disabled="disabled" class="form-control" id="code" placeholder="코드받기 를 눌러주세요"></div>
							<div class="col-sm-3"><button type="button" class="btn btn-default" id="take_group_code">코드 받기</button></div>
						</div>  
						
					</div>
					
				</form>

			</div>
      <div class="modal-footer">
      <button type="button" class="btn btn-default" id="group_add_button">추가하기</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">나가기</button>
      </div>
    </div>

  </div>
</div>
<script type="text/javascript">

	$(function() {
		$("#take_group_code").click(function() {
			$.ajax({
				url : "../group/makeGroupCode",
				contentType : "application/json",
				type : "post",
				success : function(data, status) {
					if (data == "실패") {
						alert("코드를 받는데 실패하였습니다.")
					} else {
						$("#code").val(data);
					}
				}
			});
		});
		$("#group_add_button").click(function() {
			
			var group_name_pattern = /^[\sA-Za-z가-힣0-9]{1,30}/g;
			var pw_pattern = /^[a-z0-9]{1,30}/g;
			var code_pattern = /^[a-z]{45}$/;
			
			if (!group_name_pattern.test($("#group_name").val())) {
				alert("그룹 이름 입력이 잘못되었습니다.");
				return;
			} else if(!pw_pattern.test($("#pw").val())) {
				alert("비밀번호 입력이 잘못되었습니다.");
				return;
			} else if(!code_pattern.test($("#code").val())) {
				alert("코드 받기 버튼을 눌러주세요");
				return;
			}
			
			$.ajax({
				url : "../group/addGroup",
				contentType : "application/json",
				type : "post",
				data : JSON.stringify({
					group_name : $("#group_name").val(),
					pw : $("#pw").val(),
					code : $("#code").val(),
					product : "${product.idx}"
				}),
				success : function(data, status) {
					if (data == false) {
						alert("그룹을 만드는데 실패하였습니다.")
					} else {
						//user_group 데이터를 받긴하는데 시간상 일단은 페이지를 다시 불러오도록 한다.
						location.href="";
						
					}
				}
			});
		});
	})
</script>
