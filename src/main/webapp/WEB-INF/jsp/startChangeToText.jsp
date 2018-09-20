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
        <th width='20%'>识别类型</th>
        <th width='60%'>识别结果</th>
        <th width='10%'>语言</th>
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
                var word_result = "";
                var words_result = "";
                var newresult = result.words_result;
                var kind = result.kind;

                if (kind == "身份证识别" || kind == "营业执照识别") {
                    words_result = newresult.replace(/:{"words"/g, "");
                    var words_result_array = words_result.split(",");
                    for (var i = 0; i < words_result_array.length; i = i + 5) {
                        word_result += words_result_array[i] + " , "
                    }
                } else if (kind == "驾驶证识别" || kind == "行驶证识别") {
                    words_result = newresult.replace(/{"words":/g, "");
                    word_result = words_result.replace(/}/g, " ");
                } else if (kind == "车牌号识别") {
                    var words_result_array = newresult.split('","');
                    word_result += words_result_array[0] + '" , ';
                    words_result = words_result_array[1];
                    words_result_array = words_result.split('}],');
                    word_result += words_result_array[1] + '"}]';
                } else if (kind == "通用票据识别") {
                    words_result = newresult.split('"words":');
                    for (var i = 1; i < words_result.length; i++) {
                        var words_result_array = words_result[i].split(',"location"');
                        for (var j = 0; j < words_result_array.length; j = j + 2) {
                            word_result += words_result_array[j] + " , "
                        }
                    }
                } else {//通用、网络、银行卡、表格
                    word_result = newresult.replace(/"},{"words":"/g, " , ");
                }

                str += "<tr>" +
                "<td align = 'center'>" + kind + "</td>" +
                "<td align = 'center'>" + word_result + "</td>" +
                "<td align = 'center'>" + result.language + "</td>" +
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