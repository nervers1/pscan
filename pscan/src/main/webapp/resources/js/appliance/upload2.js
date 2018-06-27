/**
 * 
 */
$(function () {
	
    $('#fileupload').fileupload({
        dataType: 'json',
 
        done: function (e, data) {
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {
 
                $("#uploaded-files").append(
                        $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').html("<a href='/file/get/"+index+"'>Click</a>"))
                        )//end $("#uploaded-files").append()
            	}); 
        },
 
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                progress + '%'
            );
        },
 
        dropZone: $('#dropzone')
    });
});

$(document).on('click', '#btn_upload', function(e) {
	var fileObj = $('#fileupload').prop("files");
	var check = $("#uploaded-files input");
    console.log(check);
});
// 파일 객체 변경 이벤트
$(document).on('change','#fileupload', function(e) {
	alert('change');
});

// 파일이 추가되면...
var obj = $('#dropzone');
obj.on('drop', function (e) {
	e.preventDefault();
	var files = e.originalEvent.dataTransfer.files;

});
