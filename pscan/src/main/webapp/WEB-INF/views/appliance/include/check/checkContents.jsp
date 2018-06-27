<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="main_block2">
	<div class="checkTitle">
		<h2>개인정보검출</h2>
	</div>
	<div class="btn-check-file">개인정보검출</div>
	<div class="check-guide">
	※ 원하시는 검출항목을 아래 <span class="bold3">검출항목</span>란에서 선택하시고, <span class="bold3">업로드 파일 목록</span>에서 파일을 선택하신 후 우측 상단의 <span class="bold3">개인정보검출</span> 버튼을 누르세요.
	</div>
	<div class="policy-input">
		<span class="policy-title"> 
		검출항목
		</span>
		<div class="policy-list"></div>
	</div>
	<div class="checkFileArea">
		<span class="policy-title">
		업로드 파일 목록 
		</span>
		<div class="checkList">
			<div class="div-table">
				<div class="div-row">
					<div class="div-cell text-center w50"><input type="checkbox" class="checkAllFile"></div>
					<div class="div-cell text-center w450">파일명</div>
					<div class="div-cell text-center w150">사이즈</div>
					<div class="div-cell text-center w150">타입</div>
					<div class="div-cell text-center w150">상태</div>
					<div class="div-cell text-center w150">등록자</div>
				</div>
			</div>
		</div>
	</div>
	
</div>