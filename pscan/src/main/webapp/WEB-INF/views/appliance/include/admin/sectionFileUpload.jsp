<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<div class="main_block2">
	<h2>개인정보검출</h2>
	<h3>파일을 드래그 하거나 아래 영역을 클릭해서 문서를 등록하세요</h3>
	<div>
		<form method="post" class="dropzone" id="dropzone">
			<div class="fallback">
			  <input name="file" type="file" multiple />
			</div>
		</form>
	</div>
	<input type="submit" class="btn_check" alt="개인정보검출" value="개인정보검출">
	
</div>
<!-- <div class="main_block2"> -->
<!-- 	<h1>파일업로드, 비식별화 테스트</h1> -->
<!-- 	<div style="width:500px;padding:20px"> -->
	 
<!-- 	    <input id="fileupload" type="file" name="files[]" data-url="/file/upload" multiple> -->
	 
<!-- 	    <div id="dropzone">Drop files here</div> -->
	 
<!-- 	    <div id="progress"> -->
<!-- 	        <div style="width: 0%;"></div> -->
<!-- 	    </div> -->
	 
<!-- 	    <table id="uploaded-files"> -->
<!-- 	        <tr> -->
<!-- 	            <th>File Name</th> -->
<!-- 	            <th>File Size</th> -->
<!-- 	            <th>File Type</th> -->
<!-- 	            <th>Download</th> -->
<!-- 	        </tr> -->
<!-- 	    </table> -->
<!-- 	</div> -->
<!-- </div> -->