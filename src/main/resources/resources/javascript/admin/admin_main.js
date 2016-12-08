var admin = {};

admin.getLogByPageAndSearch = function(tableBodyId, ulId, page, listSize, word, category) {
	if (word == "") {
		alert("검색어를 입력하세요");
	} else {
		$.ajax({
			url : "/admin/searchLog",
			contentType: "application/json",
			type : "post",
			data : JSON.stringify({
				currentPage : page,
				shownContentListSize : listSize,
				keyword : word,
				category : category
			}),
			success : function(data, status){
				console.log(data);
				admin.makeHtmlForLogTable(tableBodyId, data);
				admin.makeHtmlForPagination(tableBodyId, ulId, listSize, data.page);
			}
		
		});
	}
}

admin.getLogByPage = function(tableBodyId, ulId, page, listSize, category) {
	$.ajax({
		url : "/admin/log",
		contentType: "application/json",
		type : "post",
		data : JSON.stringify({
			currentPage : page,
			shownContentListSize : listSize,
			category : category
		}),
		success : function(data, status){
			console.log(data);
			admin.makeHtmlForLogTable(tableBodyId, data);
			admin.makeHtmlForPagination(tableBodyId, ulId, listSize, data.page);
		}
		
	});
}

admin.makeHtmlForLogTable = function(tableBodyId, data) { //data = {list[{},{},{}], page={}}
	var tr;
	var td_no;
	var td_content;
	var td_time;
	
	var time;
	$("#" + tableBodyId).html("");
	for (var i = 0; i < data.list.length; i++) {
		tr = document.createElement("tr");
		td_no = document.createElement("td");
		td_content = document.createElement("td");
		td_time = document.createElement("td");
		
		time = new Date(data.list[i].time);
		$(td_no).text(data.page.firstContent + i + 1);
		$(td_content).text(data.list[i].log);
		$(td_time).text(time.toLocaleDateString() + " " + time.toLocaleTimeString());
		$(tr).append(td_no).append(td_content).append(td_time);
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
			admin.getLogByPage(tableBodyId, ulId, pageData.firstPagination - pageData.paginationSize, listSize);
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
			admin.getLogByPage(tableBodyId, ulId, $(this).text(), listSize);
		});
		$(li).append(a);
		$("#" + ulId).append(li);
	}
	
	//next maxPageination
	if (pageData.lastPagination != pageData.maxPageination) {
		li = document.createElement("li");
		a = document.createElement("a");
		$(a).attr("href", "#").text('next').click(function(){
			admin.getLogByPage(tableBodyId, ulId, pageData.lastPagination + 1, listSize);
		});
		$(li).append(a);
		$("#" + ulId).append(li);
		
	}
}

