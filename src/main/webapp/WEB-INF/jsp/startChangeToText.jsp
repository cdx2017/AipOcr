<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>开始识别cdx </title>
</head>
<style>
    * {
        font-family: "宋体";
        font-size: 14px
    }

    td {
        word-wrap: break-word;
    }
</style>
<body>

<div style="text-align:center;">
    <input type="button" name="submit" value="开始识别" onclick="change()">

    <form action="main" method="post">
        <input type="text" name="username" value="${param.username}" hidden="true"/>

        <p><input type="submit" value="返回主页"
                  style="color: #00c6ff;background-color: #ffffff;border:none;font-size: 20px;"/></p>
    </form>
</div>
<table id="table-result" width="80%" align="center" border="1">
    <tr>
        <th width='60%'>识别结果</th>
        <th width='20%'>语言</th>
    </tr>
    <tbody id="tbody-result">
    </tbody>
</table>

<script type="text/javascript">
    var tbody = window.document.getElementById("tbody-result");
    function change() {
        alert("正在识别，请稍后。。。");
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            url: "/PictureToText",//url
            data: {"username": "${param.username}"},
            success: function (result) {
                var str = "";
                var newresult = result.words_result.replace(/"},{"words":"/g," , ");
                str += "<tr>" +
                "<td align='center' width='20%'>" + newresult + "</td>" +
                "<td align='center' width='20%'>" + result.language + "</td>" +
                "</tr>";

                tbody.innerHTML = str;
            },
            error: function () {
                alert("异常！");
            }
        });
    }
</script>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</body>
</html>