<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">

<title>jQuery File Upload Example</title>
<script src="/resources/js/jquery-3.3.1.min.js"></script>
<script src="/resources/jQueryFileUpload/js/vendor/jquery.ui.widget.js"></script>
<script src="/resources/jQueryFileUpload/js/jquery.iframe-transport.js"></script>
<script src="/resources/jQueryFileUpload/js/jquery.fileupload.js"></script>
 
<!-- we code these -->
<link href="/resources/css/appliance/dropzone.css" type="text/css" rel="stylesheet" />
<script src="/resources/js/appliance/file.js"></script>
</head>
 
<body>
<h1>파일업로드, 비식별화 테스트</h1>
<div style="width:500px;padding:20px">
 
    <input id="fileupload" type="file" name="files[]" data-url="/file/upload" multiple>
 
    <div id="dropzone">Drop files here</div>
 
    <div id="progress">
        <div style="width: 0%;"></div>
    </div>
 
    <table id="uploaded-files">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Download</th>
        </tr>
    </table>
 
</div>
</body> 
</html>