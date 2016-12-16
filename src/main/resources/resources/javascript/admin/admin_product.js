var admin = {};

admin.getLogByPageAndSearch = function(tableBodyId, ulId, page, listSize, word, category, isSearch) {
	if (isSearch == true) { //검색인 경우
		if (word == "") {
			word = null;
		}
	
		$.ajax({
			url : "/admin/productPage",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				currentPage : page,
				shownContentListSize : listSize,
				keyword : word,
				strCategory : category
			}),
			success : function(data, status){
				console.log(data);
				admin.makeHtmlForProductTable(tableBodyId, data);
				admin.makeHtmlForPagination(tableBodyId, ulId, listSize, data.page);
			}
		
		});
		
	} else if (isSearch == false){ //검색이 아닌경우
		$.ajax({
			url : "/admin/productPage",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				currentPage : page,
				shownContentListSize : listSize,
				strCategory : category
			}),
			success : function(data, status){
				console.log(data);
				admin.makeHtmlForProductTable(tableBodyId, data);
				admin.makeHtmlForPagination(tableBodyId, ulId, listSize, data.page);
			}
			
		});
	}
}


admin.makeHtmlForProductTable = function(tableBodyId, data) { //data = {list[{},{},{}], page={}}
	var tr;
	var td_no;
	var td_machine;
	var td_machine_code;
	var td_registrant;
	
	$("#" + tableBodyId).html("");
	for (var i = 0; i < data.list.length; i++) {
		tr = document.createElement("tr");
		td_no = document.createElement("td");
		td_machine = document.createElement("td");
		td_machine_code = document.createElement("td");
		td_registrant = document.createElement("td");
		
		$(td_no).text(data.page.firstContent + i + 1);
		$(td_machine).text(data.list[i].machine);
		$(td_machine_code).text(data.list[i].machine_code);
		$(td_registrant).text(data.list[i].registrant);
		$(tr).append(td_no).append(td_machine).append(td_machine_code).append(td_registrant);
		$("#" + tableBodyId).append(tr);
	}
}

admin.makeHtmlForPagination = function(tableBodyId, ulId, listSize, pageData) {
	var li;
	var a;
	$("#" + ulId).html("");
	
	//prev
	if (pageData.firstPagination != 1) {
		li = document.createElement("li");
		a = document.createElement("a");
		$(a).attr("href", "#").text('prev').click(function(){
			admin.getLogByPageAndSearch(tableBodyId, ulId, pageData.firstPagination - pageData.paginationSize, listSize, null, false);
		});
		$(li).append(a);
		$("#" + ulId).append(li);
	}
	
	for (var i = pageData.firstPagination; i <= pageData.lastPagination; i++) {
		li = document.createElement("li");
		a = document.createElement("a");
		
		if (i == pageData.currentPage) {
			$(li).addClass("active");
		}
		$(a).attr("href", "#").text(i).click(function(){
			admin.getLogByPageAndSearch(tableBodyId, ulId, $(this).text(), listSize, null, false);
		});
		$(li).append(a);
		$("#" + ulId).append(li);
	}
	//next maxPageination
	if (pageData.lastPagination != pageData.maxPageination) {
		li = document.createElement("li");
		a = document.createElement("a");
		$(a).attr("href", "#").text('next').click(function(){
			admin.getLogByPageAndSearch(tableBodyId, ulId, pageData.lastPagination + 1, listSize, null, false);
		});
		$(li).append(a);
		$("#" + ulId).append(li);
		
	}
}

