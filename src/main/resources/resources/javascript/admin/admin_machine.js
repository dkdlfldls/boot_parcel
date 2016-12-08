var adminMachine = {};

adminMachine.modifyButtonValue = function(btn, targetVal) {
	$(btn).val(targetVal);
}
adminMachine.modifyButtonAttr = function(btn, targetAttr, val) {
	$(btn).attr(targetAttr, val);
}
//삭제버튼 클릭



