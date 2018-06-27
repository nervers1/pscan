/**
 * 이력관리 자바스크립트 비즈니스 로직
 */
var history = {};
history.current = new Date();
history.lastWeek = function() {
	var d = new Date();
	var dayOfMonth = d.getDate();
	d.setDate(dayOfMonth - 7);
	return getDateStr(d);
};
$(function() {
	
	history.option = $('#his-option').val();
	$('.his-input-layout').show();
	$('.his-date-layout').hide();
	$('.his-doc-layout').hide();
	
	$('#historyFrom').datepicker({ 
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
	});
	$('#historyTo').datepicker({   
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });
	$('#historyTo').datepicker('setDate', history.current);
	$('#historyFrom').datepicker('setDate', '-7');

});
$(document).on('change', '#his-option', function() {
	history.change_option();
});
history.change_option = function () {
	history.option = $('#his-option').val();
	if ('date' == history.option) {
		$('.his-input-layout').hide();
		$('.his-date-layout').show();
		$('.his-doc-layout').hide();
	} else if ('file' == history.option) {
		$('.his-input-layout').show();
		$('.his-date-layout').hide();
		$('.his-doc-layout').hide();
	} else {
		$('.his-input-layout').hide();
		$('.his-date-layout').hide();
		$('.his-doc-layout').show();
	}
};