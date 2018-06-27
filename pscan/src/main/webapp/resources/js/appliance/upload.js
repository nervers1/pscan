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
Dropzone.autoDiscover = false;
$(function() {
	
	var myDropzone = new Dropzone("#dropzone", { url: "/file/upload"});
	var noop = function () {}; 
	Dropzone.options.dropzone = {
			url: "/file/upload",
			method: "post",
			autoDiscover: false,
			withCredentials: false,
			parallelUploads: 10,
			uploadMultiple: true,
			maxFilesize: null,
			paramName: "file",
			previewTemplate: '', 
			createImageThumbnails: false,
			maxThumbnailFilesize: 10,
			thumbnailWidth: 150,
			thumbnailHeight: 120,
			filesizeBase: 1024,
			maxFiles: null,
			params: {},
			clickable: false,
			ignoreHiddenFiles: true,
			acceptedFiles: null,
			acceptedMimeTypes: null,
			autoProcessQueue: false,
			autoQueue: true,
			addRemoveLinks: false,
			previewsContainer: null,
			hiddenInputContainer: "body",
			capture: null,
			renameFilename: null,
			dictDefaultMessage: "Drop files here to upload",
			dictFallbackMessage: "Your browser does not support drag'n'drop file uploads.",
			dictFallbackText: "Please use the fallback form below to upload your files like in the olden days.",
			dictFileTooBig: "File is too big ({{filesize}}MiB). Max filesize: {{maxFilesize}}MiB.",
			dictInvalidFileType: "You can't upload files of this type.",
			dictResponseError: "Server responded with {{statusCode}} code.",
			dictCancelUpload: "Cancel upload",
			dictCancelUploadConfirmation: "Are you sure you want to cancel this upload?",
			dictRemoveFile: "Remove file",
			dictRemoveFileConfirmation: null,
			dictMaxFilesExceeded: "You can not upload any more files.",
			accept: function(file, done) {
				return done();
			},
			init: function() {
				return noop;
			},
			forceFallback: false,
			fallback: function() {
				var child, messageElement, span, _i, _len, _ref;
				this.element.className = "" + this.element.className + " dz-browser-not-supported";
				_ref = this.element.getElementsByTagName("div");
				for (_i = 0, _len = _ref.length; _i < _len; _i++) {
					child = _ref[_i];
					if (/(^| )dz-message($| )/.test(child.className)) {
						messageElement = child;
						child.className = "dz-message";
						continue;
					}
				}
				if (!messageElement) {
					messageElement = Dropzone.createElement("<div class=\"dz-message\"><span></span></div>");
					this.element.appendChild(messageElement);
				}
				span = messageElement.getElementsByTagName("span")[0];
				if (span) {
					if (span.textContent != null) {
						span.textContent = this.options.dictFallbackMessage;
					} else if (span.innerText != null) {
						span.innerText = this.options.dictFallbackMessage;
					}
				}
				return this.element.appendChild(this.getFallbackForm());
			},
			resize: function(file) {
				var info, srcRatio, trgRatio;
				info = {
						srcX: 0,
						srcY: 0,
						srcWidth: file.width,
						srcHeight: file.height
				};
				srcRatio = file.width / file.height;
				info.optWidth = this.options.thumbnailWidth;
				info.optHeight = this.options.thumbnailHeight;
				if ((info.optWidth == null) && (info.optHeight == null)) {
					info.optWidth = info.srcWidth;
					info.optHeight = info.srcHeight;
				} else if (info.optWidth == null) {
					info.optWidth = srcRatio * info.optHeight;
				} else if (info.optHeight == null) {
					info.optHeight = (1 / srcRatio) * info.optWidth;
				}
				trgRatio = info.optWidth / info.optHeight;
				if (file.height < info.optHeight || file.width < info.optWidth) {
					info.trgHeight = info.srcHeight;
					info.trgWidth = info.srcWidth;
				} else {
					if (srcRatio > trgRatio) {
						info.srcHeight = file.height;
						info.srcWidth = info.srcHeight * trgRatio;
					} else {
						info.srcWidth = file.width;
						info.srcHeight = info.srcWidth / trgRatio;
					}
				}
				info.srcX = (file.width - info.srcWidth) / 2;
				info.srcY = (file.height - info.srcHeight) / 2;
				return info;
			},
	};
});

$(document).on('click', '.btn_check', function() {
	var message = confirm("개인정보 검출 페이지로 이동하시겠습니까?");
	if (message) {
		location.href='/appliance/check';
	}
});
