var policy = {};
policy.form = {};
policy.search = {};
policy.data = {};


/*******************************************************************************************************/
//유형 Select 컨트롤 박스
policy.cellType = [
	{name:"S", id:"S"},
	{name:"U", id:"U"}
];


//사용여부 Select 컨트롤 박스
policy.cellCheckYn = [
	{name:"사용", id:"Y"},
	{name:"미사용", id:"N"}
];

//마스킹방향 Select 컨트롤 박스
policy.cellMaskDir = [
	{name:"전체", id:"A"},
	{name:"앞에서", id:"F"},
	{name:"뒤에서", id:"R"}
];

//사용자관리 그리드 필드를 정의한다.
policy.dataFields = [
 {
     title: '번호',
     name: 'typeCd',
     type: 'number', 
     width: 60,
     readOnly:true
 },
 {
     title: '유형',
     name: 'type',
     type: 'text', 
     width: 60,
     readOnly:true,
     css: 'hidden'
 },
 {
	 	title: '사용여부',
	 	name: 'checkYn',
	     type: 'select',
	     items: policy.cellCheckYn,
	     textField: "name",
	     valueField: "id",
	     width: '90px'
 },
 {
     title: '대항목명',
     name: 'bgItmNm',
     type: 'text', 
     validate: "required",
     align: 'center',
     width: '150px',
     readOnly:true
 },
 {
     title: '소항목명',
     name: 'smItmNm',
     type: 'text', 
     validate: "required",
     align: 'left',
     width: '150px',
     readOnly:true
 },
 {
     title: '검출패턴',
     name: 'pattern',
     type: 'text', 
     validate: "required",
     align: 'left',
     width: '400px',
     readOnly:true
 },
 {
     title: '검출문자열샘플',
     name: 'patternEx',
     type: 'text', 
     validate: "required",
     align: 'left',
     width: '200px',
     readOnly:true
 },
  {
     title: '마스킹방향',
     name: 'maskDir',
     type: 'select',
     items: policy.cellMaskDir,
     textField: "name",
     valueField: "id",
     width: '100px'
 },
 {
     title: '마스킹수',
     name: 'maskCnt',
     type: 'number',
     items: policy.cellMaskDir,
     width: '100px'
 },
 {
 	type : "control", 
 	modeSwitchButton: false,
 	deleteButton:false,
 }
];

//사용자 그리드를 생성한다.
policy.showGrid = function(gridData) {
	$("#gridWrapper").jsGrid({
		width: "98%",
		autowidth:true,
		height: "430px",
		viewrecords:true,
		inserting: false,
		editing: true,
		sorting: true,
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
		data:gridData,
		controller: {
			updateItem: policy.updatepolicy,
		},
		fields: policy.dataFields
	});
};

policy.updatepolicy = function(item) {
	if (confirm("수정하시겠습니까?")) {
		$.ajax({
			url : "/api/updatePolicy",
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
				alert('정책을 수정하는 도중 오류가 발생했습니다.');
			}
			
		});
	}
};

$(document).on('change', '#searchOption', function(e) {
	$('#searchText').val('');
	policy.search.type = $('#searchOption').val();
	policy.search.text = $('#searchText').val();
});


// 검색
$(document).on('click', '.btn_search', function(e) {

	policy.search.type = $('#searchOption').val();
	policy.search.text = $('#searchText').val();

	$.ajax({
		url : "/api/searchPolicy",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(policy.search),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				policy.data = result.data;
				policy.showGrid(policy.data);
			} else if ("NONE" == result.status) {
				alert('검색 조건을 확인해 주세요.');
				$('#searchText').focus();
				
			}
		},
		error : function(e) {
			alert('정책을 검색하는 도중 오류가 발생했습니다.');
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

//$(document).on('click', '#policy_btn_save', function(e) {
//	policy.getValues();
//	
//	if (confirm("저장하시겠습니까?")) {
//		$.ajax({
//			url : "/api/createpolicy",
//			type: "POST",
//			dataType : 'JSON',
//			data : JSON.stringify(policy.form),
//			contentType : "application/json; charset=UTF-8",
//			success : function(result) {
//				//console.log(result);
//				if ("OK" == result.status) {
//					alert('저장되었습니다.');
//				} else if ("DUP" == result.status) {
//					alert('이미 사용중인 아이디 입니다.');
//					$('#usrId').focus();
//					
//				}
//			},
//			error : function(e) {
//				alert('사용자를 생성하는 도중 오류가 발생했습니다.');
//			}
//			
//		});
//	}
//});

// 초기 페이지 로딩 시 검색 
$(document).ready(function () {

	$.ajax({
		url : "/api/searchPolicy",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify({type:'', text:''}),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				policy.showGrid(result.data);
			} else if ("NONE" == result.status) {
				alert('검색 조건을 확인해 주세요.');
				$('#searchText').focus();
				
			}
		},
		error : function(e) {
			alert('정책을 검색하는 도중 오류가 발생했습니다.');
		}
		
	});
	$('#searchText').focus();
});





