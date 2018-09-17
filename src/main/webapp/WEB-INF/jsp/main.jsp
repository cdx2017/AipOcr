<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>

<br>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文字识别cdx</title>
    <style type='text/css'>
        ul {
            list-style: none;
        }
    </style>
</head>
<h1>文字识别</h1>

<%--音频上传--%>
<h2> 请您选择需要上传的图片</h2>

<form id="form1" name="form1" method="post" action="/uploadFile">
    <table border="0">
        <tr>
            <input type="text" name="username" value="${param.username}" hidden="true"/>
            <td>上传文件：</td>
            <td><input name="file" id="file" type="file" size="20" accept="image/*"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="submit" value="提交">
                <input type="reset" name="reset" value="重置">
            </td>
        </tr>
    </table>
</form>

<script src="js/main/jquery.js"></script>
<%--<script src="js/main/main.js"></script>--%>
</body>
</html>
