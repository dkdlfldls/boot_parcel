
var parcelInfo = {};
parcelInfo.open = function(locker){
	$.ajax({
		url : "open",
		contentType : "application/json",
		type : "post",
		data : JSON.stringify({
			idx : "${userEntity.idx}",
			productIdx : "${product.idx}",
			hasGroup : "${hasGroup}",
			productName : "${product.public_name }"
		}),
		success : function(data, status) {
			if (data == true) {
				$("#locker").val("잠그기");
			} else {
				alert("열기 실패");
			}
		}
	});
};

parcelInfo.close = function(locker){
	$.ajax({
		url : "lock",
		contentType: "application/json",
		type : "post",
		data : JSON.stringify({
			idx : "${userEntity.idx}",
			productIdx : "${product.idx}",
			hasGroup : "${hasGroup}",
			productName : "${product.public_name }"
		}),
		success: function(data, status){
			if (data == true) {
				$("#locker").val("열기");
			} else {
				alert("잠그기 실패");
			}
		}
	});
}