/**
 * P-SCAN APPLIANCE Main page Javascript Bussiness Logic.
 */


var pscan = {};

/* 화면 refresh없이 계속 submit해야하는 경우 form이 중복으로 document에 추가되기 때문에 reset하는 과정이 필요함. */
pscan.createForm = function (formName, method) {
	var $form = $('#' + formName);
	if($form.length < 1) {
		$form = $("<form/>").attr({id: formName, method:method});
		$(document.body).append($form);
	}
	$form.empty();
	return $form;
};

/* 정보 세팅 */
pscan.addParam = function($form, paramName, paramValue) {
	$("<input></input>").attr({type:"hidden", name:paramName, value:$.trim(paramValue)}).appendTo($form);
};

var p = {};

// 비밀번호 입력 후 엔터키 이벤트 처리
$(document).on('keypress', '#password', function( event ) {
  if ( event.which == 13 ) {
     event.preventDefault();
     $('.btn_login').click();
  }
});


// 로그인 버튼 클릭 이벤트 처리
$(document).on('click','.btn_login', function() {
	p.userId = $('#userId').val();
	p.password = $('#password').val();
	
	if (p.userId == '') {
		$('#userId').focus();
		alert('아이디를 입력해 주세요');
		return false;
	}
	if (p.password == '') {
		$('#password').focus();
		alert('비밀번호를 입력해 주세요');
		return false;
	}
	if (p.password.length > 30) {
		$('#password').focus();
		alert('비밀번호는 30자 이내로 입력해 주세요');
		return false;
	}
	
	$.ajax({
		url : "/api/login",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(p),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			//console.log(result);
			if ("OK" == result.status) {
				p.loginUser = result.data;
				p.reloadLoginForm('login');
			} else {
				alert('올바른 아이디, 비밀번호를 입력해 주세요');
			}
		},
		error : function(e) {
			
		}
		
	});
});

// 로그아웃 버튼 클릭 이벤트 처리
$(document).on('click','.btn_logout', function() {
	if (confirm('로그아웃 하시겠습니까?')) {
		$.ajax({
			url : "/api/logout",
			type: "POST",
			dataType : 'JSON',
			data : "",
			contentType : "application/json; charset=UTF-8",
			success : function(result) {
				//console.log(result);
				if ('LOGOUT' == result.status) {
					p.reloadLoginForm('logout');
				}
			},
			error : function(e) {
				
			}
		});
	}
	
});


$(document).ready(function() {
	//alert('load complete');
	$('#userId').focus();
});



// 상당 헤더 로그인 영역 리로드 이벤트 처리
p.reloadLoginForm = function(param) {
	$('.loginHeader').load('/appliance/header', p.loadMainBlock(param));
};

// 메인 블럭 리로드 이벤트 처리
p.loadMainBlock = function (param) {
	p.slideoptions = {effect: 'slide', easing: 'linear', duration:400, direction: 'left', complete:p.logOutCallback};
	if ('login' == param) {
		p.fadeMainBlock();
	} else if ('logout' == param) {
		// 하위 div 클래스 Name
		var divClassName = $('.main_contents>div').attr('class');
		var divClass = $('.' + divClassName);
		divClass.hide(p.slideoptions);
	}
};

p.logOutCallback = function() {
	p.slideoptions = {effect: 'slide', easing: 'linear', duration:400, direction: 'right'};
	$('.main_contents').load('/appliance/sectionMain', function() {
		$(this).hide().show(p.slideoptions);
	});
	$('#userId').focus();
};

// 메인 블럭 클리어 이벤트 처리
p.fadeMainBlock = function() {
	p.hideOptions = {effect: 'slide', easing: 'linear', duration:400, direction: 'left', complete:p.slideMainContents};
	$('.main_block').hide(p.hideOptions);
	
};
p.slideMainContents = function () {
	p.durationOption = {effect: 'slide', easing: 'linear', duration:400, direction: 'left'};
	if ('Y' == p.loginUser.adminYn) {
		$('.main_contents').hide(300).load('/appliance/sectionAdmin').show(p.durationOption);
	} else {
		$('.main_contents').hide(300).load('/appliance/sectionMember').show(p.durationOption);
	}
};	
p.changeMenu = function (url) {
	p.durationOption = {effect: 'slide', easing: 'linear', duration:400, direction: 'left'};
	if ('Y' == p.loginUser.adminYn) {
		$('.main_contents').hide(300).load(url).show(p.durationOption);
	} else {
		$('.main_contents').hide(300).load(url).show(p.durationOption);
	}
};
p.loadMenuContents = function(flag) {
	if ('dashboard' == flag) {
		p.menuURL = '/appliance/dashboard';
	} else if ('document' == flag) {
		p.menuURL = '/appliance/file';
	} else if ('member' == flag) {
		p.menuURL = '/appliance/user';
	} else if ('policy' == flag) {
		p.menuURL = '/appliance/policy';
	} else if ('history' == flag) {
		p.menuURL = '/appliance/history';
	} else if ('notice' == flag) {
		p.menuURL = '/appliance/notice';
	} else if ('setting' == flag) {
		p.menuURL = '/appliance/setting';
	} else if ('main' == flag) {
		p.menuURL = '/appliance';
	} else {
		//alert('error!');
		p.menuURL = '/appliance';
		return false;
	}
	$(location).attr('href', p.menuURL);
};

$(document).on('click', 'section.menu_list ul li', function() {
	var name = $(this).attr('class');
	var idx = name.lastIndexOf('_') + 1;
	var flag = name.substring(idx);
	p.loadMenuContents(flag);
});

// 로고 이미지 이징 효과
$('.img_logo').draggable({
	stop : function(){ // 드래그 종료시 실행
	    $(this).animate({ top : 0, left : 0 }, 1000, 'easeOutElastic' ); // 커지게 만들고 원위치로
	    // 제이쿼리UI 의 이징효과 사용
	}
});
// 로고 이미지 클릭 이벤트 처리
$(document).on('click', '.img_logo', function(){p.loadMenuContents('main');});
//비밀번호 입력 후 엔터키 이벤트 처리
$(document).on('keypress', '#initPassword', function( event ) {
  if ( event.which == 13 ) {
     event.preventDefault();
     $('.btn_pw_init').click();
  }
});
// 초기 비밀번호 변경버튼 클릭 이벤트 처리
$(document).on('click', '.btn_pw_init', function() {
	p.initPassword = $('#initPassword').val();

	if (p.initPassword == '') {
		$('#initPassword').focus();
		alert('비밀번호를 입력해 주세요');
		return false;
	}
	if (p.initPassword.length > 30) {
		$('#initPassword').focus();
		alert('비밀번호는 30자 이내로 입력해 주세요');
		return false;
	}
	
	if (confirm('로그인 비밀번호를 변경하시겠습니까?')) {
		$.ajax({
			url : "/api/setPassword",
			type: "POST",
			dataType : 'JSON',
			data : p.initPassword,
			contentType : "application/json; charset=UTF-8",
			success : function(result) {
				if ('OK' == result.status) {
					alert("변경되었습니다.");
					$(location).attr('href', '/appliance');
				}
			},
			error : function(e) {
				alert("로그인 비밀번호를 변경 실패.");
			}
		});
	}
});


