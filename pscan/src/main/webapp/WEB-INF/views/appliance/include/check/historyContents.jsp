<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="main_block2">
<div class="memberHeader">
	<h2 class="memberTitle">검출이력</h2> 
</div>
<div class="his-search-option">
	<select id="his-option" class="his-select">
		<option value="file" selected>파일명</option>
		<option value="date">날짜</option>
		<option value="document">문서유형</option>
	</select>
</div>
<div class="his-input-layout">
	<input class="his-searchText" type="text">
</div>
<div class="his-date-layout">
	<div class="his-date-from">From:<input class="calendar-input" type="text" id="historyFrom"></div>
	<div class="his-dash">-</div>
	<div class="his-date-to">To:<input class="calendar-input" type="text" id="historyTo"></div>
</div>
<div class="his-doc-layout">
	<select id="docType" class="his-select-doc">
		<option value="hwp" selected>한글(hwp)</option>
		<option value="word">워드(doc, docx)</option>
		<option value="ppt">파워포인트(ppt, pptx)</option>
		<option value="xls">엑셀(xls, xlsx)</option>
		<option value="pdf">PDF</option>
		<option value="txt">텍스트(txt, log, ...)</option>
	</select>
</div>
<div class="his-btn_search">검색</div>
</div>