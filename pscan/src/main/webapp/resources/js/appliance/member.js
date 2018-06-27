var user = {};
user.form = {};
user.search = {};
user.data = {};


/*******************************************************************************************************/

//관리자여부 Select 컨트롤 박스
user.cellAdminYn = [
	{name:"관리자", id:"Y"},
	{name:"사용자", id:"N"}
];

//사용여부 Select 컨트롤 박스
user.cellUseYn = [
	{name:"사용", id:"Y"},
	{name:"미사용", id:"N"}
];

user.deleteConfirm =  function(item) {
 return '사용자 \"' + item.name + '\" 를 삭제하시겠습니까?';
};

//사용자관리 그리드 필드를 정의한다.
user.dataFields = [
 {
     title: 'idx',
     name: 'idx',
     type: 'number', 
     css: 'hide'
 },
 {
     title: '성명',
     name: 'name',
     type: 'text', 
     validate: "required",
     align: 'center',
     width: '120px'
 },
 {
     title: '아이디',
     name: 'userId',
     type: 'text', 
     validate: "required",
     align: 'center',
     width: '120px'
 },
 {
     title: '이메일',
     name: 'email',
     type: 'text', 
     align: 'left',
     width: '200px'
 },
 {
     title: '관리자여부',
     name: 'adminYn',
     type: 'select',
     items: user.cellAdminYn,
     textField: "name",
     valueField: "id",
     width: '100px'
 },
 {
 	title: '부서명',
 	name: 'deptNm',
     type: 'text',
     align: 'center',
     width: '100px'
 },
 {
 	title: '사번',
 	name: 'deptNo',
     type: 'text', 
     align: 'center',
     width: '100px'
 },
 {
 	title: '직위',
 	name: 'positionNm',
     type: 'text', 
     align: 'center',
     width: '150px'
 },
 {
 	title: '사용자유형',
 	name: 'userType',
     type: 'text', 
     align: 'center',
     width: '150px'
 },
 {
 	title: '사용여부',
 	name: 'useYn',
     type: 'select',
     items: user.cellUseYn,
     textField: "name",
     valueField: "id",
     width: '90px'
 },
 {
 	title: '비고',
 	name: 'description',
     type: 'text', 
     align: 'left',
     width: '200px'
 }, 
 {
 	type : "control", 
     width: ''
 }
];

//사용자 그리드를 생성한다.
user.showGrid = function(gridData) {
	$("#gridWrapper").jsGrid({
		width: "95%",
		autowidth:true,
		height: "430px",
		viewrecords:true,
		inserting: true,
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
		deleteConfirm: user.deleteConfirm,
		data:gridData,
		controller: {
			insertItem: user.createUser,
			updateItem: user.updateUser,
			deleteItem: user.deleteUser
		},
		fields: user.dataFields
	});
};

user.createUser = function(item) {
	if (confirm("저장하시겠습니까?")) {
		$.ajax({
			url : "/api/createUser",
			type: "POST",
			dataType : 'JSON',
			data : JSON.stringify(item),
			contentType : "application/json; charset=UTF-8",
			success : function(result) {
				if ("OK" == result.status) {
					$('.btn_search').click();
					alert('저장되었습니다.');
				} else if ("DUP" == result.status) {
					alert('이미 사용중인 아이디 입니다.');
				}
			},
			error : function(e) {
				alert('사용자를 생성하는 도중 오류가 발생했습니다.');
			}
			
		});
	}
};

user.updateUser = function(item) {
	if (confirm("수정하시겠습니까?")) {
		$.ajax({
			url : "/api/updateUser",
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
				alert('사용자를 수정하는 도중 오류가 발생했습니다.');
			}
			
		});
	}
};

user.deleteUser = function(item) {
	
	$.ajax({
		url : "/api/deleteUser",
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
			alert('사용자를 수정하는 도중 오류가 발생했습니다.');
		}
		
	});
};

$(document).on('change', '#searchOption', function(e) {
	$('#searchText').val('');
	user.search.type = $('#searchOption').val();
	user.search.text = $('#searchText').val();
});


// 사용자 검색
$(document).on('click', '.btn_search', function(e) {

	user.search.type = $('#searchOption').val();
	user.search.text = $('#searchText').val();

	$.ajax({
		url : "/api/searchUser",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(user.search),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				user.data = result.data;
				user.showGrid(user.data);
			} else if ("NONE" == result.status) {
				alert('검색 조건을 확인해 주세요.');
				$('#searchText').focus();
				
			}
		},
		error : function(e) {
			alert('사용자를 검색하는 도중 오류가 발생했습니다.');
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

//$(document).on('click', '#user_btn_save', function(e) {
//	user.getValues();
//	
//	if (confirm("저장하시겠습니까?")) {
//		$.ajax({
//			url : "/api/createUser",
//			type: "POST",
//			dataType : 'JSON',
//			data : JSON.stringify(user.form),
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
		url : "/api/searchUser",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify({type:'', text:''}),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if ("OK" == result.status) {
				user.showGrid(result.data);
			} else if ("NONE" == result.status) {
				alert('검색 조건을 확인해 주세요.');
				$('#searchText').focus();
				
			}
		},
		error : function(e) {
			alert('사용자를 검색하는 도중 오류가 발생했습니다.');
		}
		
	});
	$('#searchText').focus();
});





