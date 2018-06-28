/**
 *  P-SCAN 대쉬보드 비즈니스 로직
 */
var log = {};
log.form = {};
log.data = {};

//사용자관리 그리드 필드를 정의한다.
log.dataFields = [
 {
     title: '로그번호',
     name: 'check_file_id',
     type: 'number', 
     align: 'right',
     width: '100px'
 },
 {
     title: '수행일자',
     name: 'regdt',
     type: 'text', 
     align: 'center',
     width: '100px'
 },
 {
     title: '문서명',
     name: 'org_name',
     type: 'text', 
     align: 'left',
     width: '120px'
 },
 {
 	title: '문서종류',
 	name: 'ext',
     type: 'text', 
     align: 'center',
     width: '60px'
 }, 
 {
     title: '사이즈',
     name: 'size',
     type: 'number', 
     align: 'right',
     width: '60px'
 },
 {
     title: '검출결과',
     name: 'check_cnt',
     type: 'number', 
     align: 'right',
     width: '60px'
 }
];

//사용자 그리드를 생성한다.
log.showGrid = function(gridData) {
	$("#gridWrapper").jsGrid({
		width: "95%",
		autowidth:true,
		height: "430px",
		sorting: true,
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
		rowClick: function(args) {
            ;
        },
		data:gridData,
		fields: log.dataFields
	});
};

//초기 페이지 로딩 시 검색 
$(document).ready(function () {

	$.ajax({
		url : "/api/dashboardCheckFileList",
		type: "POST",
		dataType : 'JSON',
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				console.log(result.data);
				log.showGrid(result.data);
			} else if ("NONE" == result.status) {
				notice.showGrid('');
			}
		},
		error : function(e) {
			alert('로그을 검색하는 도중 오류가 발생했습니다.');
		}
		
	});
});

