/**
 *  P-SCAN 공지사항, 공지사항관리 메뉴 javascript 비즈니스 로직
 */
var notice = {};
notice.form = {};
notice.search = {};
notice.data = {};

// 개행문자치환(엔터 --> <br />)
notice.convert = function (str) {
	return str.replace(/(?:\r\n|\r|\n)/g, '<br />');
};

notice.deleteConfirm =  function(item) {
	 return '공지사항 (\"' + item.title + '\") 를 삭제하시겠습니까?';
	};

//사용자관리 그리드 필드를 정의한다.
notice.dataFields = [
 {
     title: '번호',
     name: 'notice_no',
     type: 'number',
     width: 50,
     readOnly:true
 },
 {
     title: '제목',
     name: 'title',
     type: 'text', 
     validate: "required",
     align: 'left',
     width: '120px'
 },
 {
 	title: '공지내용',
 	name: 'content',
     type: 'text', 
     align: 'left',
     width: '600px'
 }, 
 {
	 	title: '공지일자',
	 	name: 'regdt',
	     type: 'text', 
	     align: 'center',
	     sorting: true,
	     width: '100px',
         readOnly:true
	 }, 
 {
 	type : "control", 
	modeSwitchButton: false,
	editButton: true,
	headerTemplate: function() {
		return $("<button>").attr("type", "button").text("Add").on("click", function () {
			window.open("/bbs/noticeWrite","공지사항 등록", "width=1000, height=600, scrollbars=yes");
		});
	}
 }
];

//사용자 그리드를 생성한다.
notice.showGrid = function(gridData) {
	$("#gridWrapper").jsGrid({
		width: "95%",
		autowidth:true,
		height: "430px",
		editing: true,
		autoload: true,
		viewrecords:true,
		paging: true,
		pageSize: 10,
		pageButtonCount: 3,
		pagerContainer: "#gridPaging",
		pagerFormat: "현재페이지: {pageIndex} &nbsp;&nbsp; {first} {prev} {pages} {next} {last} &nbsp;&nbsp; 전체 페이지: {pageCount}",
		pagePrevText: "<",
		pageNextText: ">",
		pageFirstText: "<<",
		pageLastText: ">>",
		pageNavigatorNextText: "&#8230;",
		pageNavigatorPrevText: "&#8230;",
		deleteConfirm: notice.deleteConfirm,
		data:gridData,
		controller: {
			insertItem: notice.createNotice,
			updateItem: notice.updateNotice,
			deleteItem: notice.deleteNotice
		},
		fields: notice.dataFields
	});
};

notice.createNotice = function(item) {
	if (confirm("저장하시겠습니까?")) {
		$.ajax({
			url : "/api/createNotice",
			type: "POST",
			dataType : 'JSON',
			data : JSON.stringify(item),
			contentType : "application/json; charset=UTF-8",
			success : function(result) {
				if ("OK" == result.status) {
					$('.btn_search').click();
					alert('저장되었습니다.');
				} 
			},
			error : function(e) {
				alert('공지사항을 생성하는 도중 오류가 발생했습니다.');
			}
			
		});
	}
};

notice.updateNotice = function(item) {
	if (confirm("수정하시겠습니까?")) {
		$.ajax({
			url : "/api/updateNotice",
			type: "POST",
			dataType : 'JSON',
			data : JSON.stringify(item),
			contentType : "application/json; charset=UTF-8",
			success : function(result) {
				if ("OK" == result.status) {
					$('.btn_search').click();
					alert('수정되었습니다.');
				}
			},
			error : function(e) {
				alert('공지사항을 수정하는 도중 오류가 발생했습니다.');
			}
			
		});
	}
};

notice.deleteNotice = function(item) {
	
	$.ajax({
		url : "/api/deleteNotice",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(item),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				$('.btn_search').click();
				alert('삭제되었습니다.');
			}
		},
		error : function(e) {
			alert('공지사항을 삭제하는 도중 오류가 발생했습니다.');
		}
		
	});
};

// 검색
$(document).on('change', '#searchOption', function(e) {
	$('#searchText').val('');
	notice.search.type = $('#searchOption').val();
	notice.search.text = $('#searchText').val();
});



$(document).on('click', '.btn_search', function(e) {

	notice.search.type = $('#searchOption').val();
	notice.search.text = $('#searchText').val();

	$.ajax({
		url : "/api/searchNotice",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(notice.search),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				notice.data = result.data;
				notice.showGrid(notice.data);
			} else if ("NONE" == result.status) {
				alert('검색 조건을 확인해 주세요.');
				$('#searchText').focus();
				
			}
		},
		error : function(e) {
			alert('공지사항을 검색하는 도중 오류가 발생했습니다.');
		}
		
	});
});

//검색어 입력 후 엔터키 이벤트 처리
$(document).on('keypress', '#searchText', function( event ) {
  if ( event.which == 13 ) {
     event.preventDefault();
     $('.btn_search').click();
  }
});

//초기 페이지 로딩 시 검색 
$(document).ready(function () {

	$.ajax({
		url : "/api/searchNotice",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify({type:'', text:''}),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				console.log(result.data);
				notice.showGrid(result.data);
			} else if ("NONE" == result.status) {
				alert('검색 조건을 확인해 주세요.');
				$('#searchText').focus();
				
			}
		},
		error : function(e) {
			alert('공지사항을 검색하는 도중 오류가 발생했습니다.');
		}
		
	});
	$('#searchText').focus();
});

