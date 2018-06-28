/**
 * 
 */
var check = {};

// 정규식리스트  Ajax 호출
check.policyList = function() {
	$.ajax({
		url : "/api/listPolicy",
		type: "POST",
		dataType : 'JSON',
		data : "",
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			check.policy = result.data;
			check.listPolicy(check.policy);
			
		},
		error : function(e) {
			alert('정책을 불러오지 못했습니다.');
		}
	});
};
// 파일리스트  Ajax 호출
check.listFiles = function() {
	$.ajax({
		url : "/api/listFiles",
		type: "POST",
		dataType : 'JSON',
		data : "",
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			check.data = result.data;
			check.listFile(check.data);
		},
		error : function(e) {
			
		}
	});
};
$(document).ready(function() {
	check.policyList();
	check.listFiles();
	$(document).on('click', '.checkAllFile', check.checkAllFile);
});

// 정규식리스트  View
check.listPolicy = function(data) {
	var pannel = $('.policy-list');
	var policyBr = $('<br />');
	$.each(data, function(idx, item){
		var checkbox = $('<input />', {
			class: 'policy-check',
			type: 'checkbox',
			name: 'policy',
			value: item.typeCd,
			id: 'chk_' + item.typeCd,
			'checked': false
		});
		var label = $('<label />', {
			'for': 'chk_' + item.typeCd,
			text: item.smItmNm
		});
		var policyItem = $('<div />', {
			class: "policy-item"
		});
		policyItem.append(checkbox).append(label);
		pannel.append(policyItem);
	});
	
};

// 파일리스트  View
check.listFile = function (data) {
	$.each(data, function(idx, item){
		var row = $('<div />', {
			class: "div-row"
		});
		var column1 = $('<div />', {
			class: "div-cell text-center w50",
			html: '<input type="checkbox" value="'+ item.fileId +'" name="uploadFileList">'
		});
		var column2 = $('<div />', {
			class: "div-cell text-left",
			text: item.orgName + '.'+ item.ext
		});
		var column3 = $('<div />', {
			class: "div-cell text-right",
			text: item.size + ' Kb'
		});
		var column4 = $('<div />', {
			class: "div-cell text-center",
			text: item.mimeType
		});
		
		if (item.statusCd == '100') {
			item.statusName = '업로드완료';
		} else if (item.statusCd == '200') {
			item.statusName = '검출완료';
		}
		
		var column5 = $('<div />', {
			class: "div-cell text-center",
			text: item.statusName
		});
		var column6 = $('<div />', {
			class: "div-cell text-center",
			text: item.reg_id
		});
		row.append(column1).append(column2).append(column3).append(column4).append(column5).append(column6);
		$('.div-table').append(row);
		
	});
};

// 파일리스트 헤더 체크박스 선택 Toggle event
check.checkAllFile = function (e) {
	if (this.checked) {
		$('.div-cell input').prop('checked', true);
	} else {
		$('.div-cell input').prop('checked', false);
	}
};

// 개인정보검출 버튼 클릭 이벤트
$(document).on('click', '.btn-check-file', function() {check.buttonCheck()});
check.buttonCheck = function(e) {
	
	// Validations
	if (!check.checkPolicy()||!check.checkfiles()) {
		return false;
	}
	
	var params = {};
	params.typeCds = check.policyKey; // 검출항목
	params.fileIds = check.fileKey;   // 검출파일목록

	$.ajax({
		url : "/api/check",
		type: "POST",
		dataType : 'JSON',
		data : JSON.stringify(params),
		contentType : "application/json; charset=UTF-8",
		success : function(result) {
			if (result.status == 'OK') {
				check.checkResult = result.data;
				console.log(check.checkResult);
				location.replace("/appliance/checkResult");
			}
		},
		error : function(e) {
			alert('개인정보를 검출하지 못했습니다.');
		}
	});
}

// 검출항목 Check
check.checkPolicy = function() {
	var checkObj = $('.policy-check[name="policy"]:checked');
	var checkCnt = checkObj.length;
	
	if (checkCnt == 0) {
		alert("개인정보검출을 원하시는 검출항목을 선택해 주세요");
		return false;
	}
	
	check.policyKey = $.map(checkObj, function(item) {
		return $(item).attr("value");
	});
	return true;
};

// 업로드 파일 목록 Check
check.checkfiles = function() {
	var checkObj =  $('input[name="uploadFileList"]:checked');
	var checkCnt = checkObj.length;
	if (checkCnt == 0) {
		alert("개인정보검출을 원하시는 파일을 선택해 주세요");
		return false;
	}
	check.fileKey = $.map(checkObj, function(item) {
		return $(item).attr("value");
	});
	return true;
};
