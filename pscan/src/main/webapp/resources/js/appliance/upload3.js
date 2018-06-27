/**
 * P-SCAN 파일 업로드 비즈니스 로직
 */
// [FLOW]
// 파일이 추가되면 파일 메타 정보를 리스트 객체로 저장한다.
// 파일이 다시 변경되는 경우 기존 리스트를 추가된 정보와 함께 업데이트한다.
// 최종 파일 리스트를 저장
// List View ...
// 리스트에서 삭제하고 싶은 파일들을 선택한다(기본적으로 추가된 파일들은 체크된 상태로 저장된다).
// 최종적으로 선택된 파일들을 formData객체에 담아 업로드한다.
// (Java)
// 업로드된 파일 리스트를 DB(이력:History)에 저장하고, 저장된 리스트를 조회하여 View(새로운 페이지)에 표시한다 - 새로운 페이지
// 리스트를 개인정보 검출 화면에 보여주고 '검출' 버튼을 클릭해 개인정보 검출을 시도한다.
// 검출결과 리스트를 DB에 저장하고 개인정보 비식별화 조치여부에 따라 처리하여 처리된 파일들을 별도의 directory에 저장한다.
// 검출이 끝난 파일들은 삭제처리한다.
// 최종 비식별화 결과 리스트를 조회하여 View에 전달한다. - 검출 페이지 하단
// 다운로드 링크를 통해 비식별화 조치된 파일들을 다운로드한다. 
// 다운로드시 다운로드 여부를 Flag값으로 전달해서 update한다.


// 파일 객체 변경 이벤트
$(document).on('change','#file', function(e) {
	var files = e.target.files;
	showFileList(files);
});

var showFileList = function(files) {
	var fileList = $('#uploadFileList');
	//fileList.html('');

	/*if (files.items) {
		for (var i = 0; i < data.items.length; i++) {
			var t_file = data.items[i].getAsFile();
		}
	}*/
	
    $.each(files, function (index, file) {
    	var listItem = $('<li/>', {class:"fileListItem"});
    	listItem.append($('<div/>', {class:"fileListItemCheck"}).append($('<input/>', {type:"checkbox", name:"checkFile"})));
    	listItem.append($('<div/>', {class:"fileListItemName", text:file.name}));
    	listItem.append($('<div/>', {class:"fileListItemType", text:file.type}));
    	listItem.append($('<div/>', {class:"fileListItemSize", text:Number(file.size / 1024).toFixed(2) + ' Kb'}));
    	listItem.appendTo(fileList);
    });
};


$("#fileupload").bind('change', function(event) {
    files.push(event.target.files);
});

$(function () {
    var dropZoneId = "drop-zone";
    var buttonId = "clickHere";
    var mouseOverClass = "mouse-over";

    var dropZone = $("#" + dropZoneId);
    var ooleft = dropZone.offset().left;
    var ooright = dropZone.outerWidth() + ooleft;
    var ootop = dropZone.offset().top;
    var oobottom = dropZone.outerHeight() + ootop;
    var inputFile = dropZone.find("input");
    document.getElementById(dropZoneId).addEventListener("dragover", function (e) {
        e.preventDefault();
        e.stopPropagation();
        dropZone.addClass(mouseOverClass);
        var x = e.pageX;
        var y = e.pageY;

        if (!(x < ooleft || x > ooright || y < ootop || y > oobottom)) {
            inputFile.offset({ top: y - 15, left: x - 100 });
        } else {
            inputFile.offset({ top: -400, left: -400 });
        }

    }, true);

    if (buttonId != "") {
        var clickZone = $("#" + buttonId);

        var oleft = clickZone.offset().left;
        var oright = clickZone.outerWidth() + oleft;
        var otop = clickZone.offset().top;
        var obottom = clickZone.outerHeight() + otop;

        $("#" + buttonId).mousemove(function (e) {
            var x = e.pageX;
            var y = e.pageY;
            if (!(x < oleft || x > oright || y < otop || y > obottom)) {
                inputFile.offset({ top: y - 15, left: x - 160 });
            } else {
                inputFile.offset({ top: -400, left: -400 });
            }
        });
    }

    document.getElementById(dropZoneId).addEventListener("drop", function (e) {
        $("#" + dropZoneId).removeClass(mouseOverClass);
    }, true);

})