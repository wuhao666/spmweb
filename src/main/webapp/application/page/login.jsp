<!--<%=request.getRequestURI() %>-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta charset="utf-8" />
    <title>登录</title>
    <link href="../css/style.css" rel="stylesheet" />
</head>
<body class="login">
    <header class="header">
        <div class="datavenus left">
            <img src="../img/datavenus.png" width="176" height="31" />
        </div>
        <div class="logo right">
            <img src="../img/logo.png" width="214" height="32" />
        </div>
    </header>
    <div class="container">
        <section class="login-form">
            <header>
                全网舆情洞察系统
            </header>
            <section id="loginInfo">
                <div class="form form-single">
                    <input type="text" id="account" class="account" placeholder="用户名" value="" />
                    <div id="accountError" class="error visible">*用户名错误</div>
                </div>
                <div class="form form-single">
                    <input type="password" id="pwd" class="pwd" placeholder="密码" value="" />
                    <div id="pwdError" class="error visible">*密码错误</div>
                </div>
                <div class="login-control">
                    <div class="checkbox checkbox-default">
                        <span class="checkboxinput">
                            <input id="remember" type="checkbox" />
                            <label for="remember"></label>
                        </span>
                        <span class="checkboxlabel">记住密码</span>
                    </div>
                    <a href="#" class="link right">忘记密码</a>
                </div>
                <div class="login-submit">
                    <input type="submit" id="btnLoginIn" value="登&nbsp;&nbsp;&nbsp;&nbsp;录" class="btn btn-login" />
                </div>
            </section>
        </section>
    </div>
    <script type="text/javascript" src="../js/jquery/jquery-1.11.1.min.js"></script>
    <script>
        $(function () {
            var infoStr = window.localStorage.getItem("pagechoice_spidertool");
            if (null !== infoStr) {
                var info = JSON.parse(infoStr);
                $("#account").val(info.account);
                $("#pwd").val(info.pwd);
                $("#remember").prop("checked", true);
            }

            $("#btnLoginIn").click(function () { loginIn(); });

            var loginIn = function () {
                var account = $("#account").val();
                var pwd = $("#pwd").val();
                if (!Validate(account, pwd)) {
                    return false;
                }
                $.ajax({
                    url: '<%=request.getContextPath()%>/login.do',
                    type: 'POST',
                    data: { username: account, passwd: pwd },
                    success: function (data, status) {
                        if (1 === data) {
                            RememberInfo(account, pwd);
                            window.location.href = "<%=request.getContextPath()%>/index.do";
                        } else if (0 === data) {
                            $("#accountError").removeClass("visible");
                        } else if (2 === data) {
                            $("#pwdError").removeClass("visible");
                        }
                    },
                    error: function () {

                    }
                });
                function Validate(account, pwd) {
                    $(".error").addClass("visible");
                    var res = false;
                    if ("" === account) {
                        $("#accountError").removeClass("visible");
                    }
                    else if ("" === pwd) {
                        $("#pwdError").removeClass("visible");
                    }
                    else {
                        res = true;
                    }

                    return res;
                }
                function RememberInfo(account, pwd) {
                    var info = {};
                    info.account = account;
                    info.pwd = pwd;

                    var checked = $("#remember").filter(":checked").length == 1;
                    if (checked) {
                        window.localStorage.setItem("pagechoice_spidertool", JSON.stringify(info));
                    }
                    else {
                        window.localStorage.removeItem("pagechoice_spidertool");
                    }
                }
            }
        });
    </script>
</body>
</html>
