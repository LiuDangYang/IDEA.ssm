<html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<head >
</head>
<body>
<h2>POI父子级文件上传</h2>
<form action="<%= request.getContextPath()%>/user/showUser.do" method="post" enctype="multipart/form-data" >
         <input type="file" id="myFile" name="myFile" value="">
         <input type="text" id="name" name="name" value="">
    <input type="submit" value="提交">
</form>
</body>
</html>
