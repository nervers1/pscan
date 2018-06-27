/**
 *  P-SCAN 공지사항, 공지사항관리 메뉴 javascript 비즈니스 로직
 */
var notice = {};

// 개행문자치환(엔터 --> <br />)
notice.convert = function (str) {
	return str.replace(/(?:\r\n|\r|\n)/g, '<br />');
};

//공지사항 저장 버튼 클릭 이벤트
$(document).on('click', '.btn-notice-save', function(){
	notice.param = {};
	notice.param.title = $('input#title').val();
	notice.param.content = notice.convert($('textarea#notice_content').val());
	
	if (notice.param.title == '') {
		alert('공지사항 제목을 입력해 주세요.');
		$('input#title').focus();
		return false;
	}
	if (notice.param.content == '') {
		alert('공지사항 내용을 입력해 주세요.');
		$('textarea#notice_content').focus();
		return false;
	}

	$.ajax({
		url : "/api/createNotice",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(notice.param),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			alert('공지사항을 등록하였습니다.');
			window.opener.location.reload(true);
			window.close();
		},
		error : function(e) {
			alert('공지사항을 등록하는 도중 오류가 발생했습니다.');
		}
		
	});
});

//공지사항 취소버튼 클릭이벤트(이전 페이지로 돌아감)
$(document).on('click', '.btn-notice-cancel', function(){
	if (confirm("공지사항 등록을 취소하시겠습니까?")) {
		window.close();	
	};
		
});
