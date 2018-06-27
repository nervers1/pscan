/**
 *  P-SCAN 공지사항, 공지사항관리 메뉴 javascript 비즈니스 로직
 */
var notice = {};
notice.form = {};
notice.search = {};
notice.data = {};

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
	 }
];

//사용자 그리드를 생성한다.
notice.showGrid = function(gridData) {
	$("#gridWrapper").jsGrid({
		width: "95%",
		autowidth:true,
		height: "500px",
		editing: false,
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
		fields: notice.dataFields
	});
	
   
};

notice.showDetailsDialog = function (notice){
	
	window.open("/bbs/notice","공지사항 등록", "width=1000, height=600, scrollbars=yes");
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

