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

<%--图片上传--%>
<h2> 请您选择需要上传的图片</h2>

<form id="form1" name="form1" method="post" action="/uploadFile" enctype="multipart/form-data">
    <table border="0">
        <tr>
            <td width="20%">输入名字：</td>
            <td><input type="text" name="username" value="${param.username}" required="true"/></td>
        </tr>
        <tr>
            <td width="20%">选择识别类别：</td>
            <td>
                <select name="PictureKindList">
                    <option value="basicGeneral" selected>通用文字识别</option>
                    <option value="webImage">网络图片文字识别</option>
                    <option value="idcard_front">身份证识别-正面(照片)</option>
                    <option value="idcard_back">身份证识别-反面(国徽)</option>
                    <option value="bankcard">银行卡识别</option>
                    <option value="drivingLicense">驾驶证识别</option>
                    <option value="vehicleLicense">行驶证识别</option>
                    <option value="plateLicense">车牌识别</option>
                    <option value="businessLicense">营业执照识别</option>
                    <option value="receipt">通用票据识别</option>
                    <option value="form">表格文字识别</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="20%">上传文件：</td>
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
