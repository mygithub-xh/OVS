
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
    <meta name="author" content="Vincent Garreau" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="stylesheet" media="screen" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css"/>
    <link href="../h-ui/lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <style>
        .radio-box{
            float: left;
            width: 100px;
            text-align: center;
        }
        .radio-box input{
            display: block;
            float: left;
            height: 15px;
            width: 15px;
            margin-left: 25px;
        }

        .login-vcode{
            float: left;
            margin-left: 10px;
        }
        .login-vcode-input{
            float: left;
            width: 70px;
        }
        .login-vcode-input{
            float: left;
            width: 70px;
            margin-left: 20px;
        }
        .login-vcode-input input{
            height: 28px;
            width: 130px;
            background-color: rgba(255,255,255,-0.1);
            border: 0;
            border-bottom: 1px solid #cccccc;
        }
        .radio-box {
            margin: 0.5rem;
        }
        .radio-box input[type="radio"] {
            position: absolute;
            opacity: 0;
        }
        .radio-box input[type="radio"] + .radio-label:before {
            content: '';
            background: #f4f4f4;
            border-radius: 100%;
            border: 1px solid #b4b4b4;
            display:inline-block;
            width: 1.4em;
            height: 1.4em;
            position: relative;
            top: -0.2em;
            margin-right: 1em;
            vertical-align: top;
            cursor: pointer;
            text-align: center;
            -webkit-transition: all 250ms ease;
            transition: all 250ms ease;
        }
        .radio-box input[type="radio"]:checked + .radio-label:before {
            background-color: #3197EE;
            box-shadow: inset 0 0 0 4px #f4f4f4;
        }
        .radio-box input[type="radio"]:focus + .radio-label:before {
            outline: none;
            border-color: #3197EE;
        }
        .radio-box input[type="radio"]:disabled + .radio-label:before {
            box-shadow: inset 0 0 0 4px #f4f4f4;
            border-color: #b4b4b4;
            background: #b4b4b4;
        }
        .radio-box input[type="radio"] + .radio-label:empty:before {
            margin-right: 0;
        }

    </style>

</head>
<body>

<div id="particles-js">
    <div class="login">
        <div class="login-top">
            登录
        </div>
        <form id="form">
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="${pageContext.request.contextPath}/img/name.png"/></div>
            <div class="login-center-input">
                <input id="name" type="text" name="loginname" value="" placeholder="请输入您的用户名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'"/>
                <div class="login-center-input-text">用户名</div>
            </div>
        </div>
        <div class="login-center clearfix">
            <div class="login-center-img"><img src="${pageContext.request.contextPath}/img/password.png"/></div>
            <div class="login-center-input">
                <input id="psw" type="password" name="psw" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
                <div class="login-center-input-text">密码</div>
            </div>
        </div>

        <div class="login-center clearfix">
            <div class="login-vcode"><img title="点击图片切换验证码" id="vcodeImg" src="get_cpacha?vl=4&w=100&h=30"/></div>
            <div class="login-vcode-input ">
            <input  name="vcode" type="text" placeholder="请输入验证码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入验证码'">
            </div>
        </div>
            <div class=" login-center clearfix" >
                <div class="radio-box">
                    <input type="radio" id="radio-2" name="type" value="2" />
                    <label for="radio-2" class="radio-label">用户</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-1" checked name="type" value="1" />
                    <label for="radio-1" class="radio-label">管理员</label>
                </div>
            </div>
        <div class="login-button" onclick="" id="login_check">
            登录
        </div>
        </form>
    </div>
    <div class="sk-rotating-plane"></div>
</div>

<!-- scripts -->
<script src="${pageContext.request.contextPath}/webjars/jquery/3.3.1-2/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/particles.min.js"></script>
<script src="${pageContext.request.contextPath}/js/app.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../h-ui/lib/icheck/jquery.icheck.min.js"></script>

<script type="text/javascript">

    //点击图片切换验证码
    $("#vcodeImg").click(function(){
        this.src="get_cpacha?vl=4&w=100&h=30&t="+new Date().getTime();
    });


    function hasClass(elem, cls) {
        cls = cls || '';
        if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
        return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
    }

    function addClass(ele, cls) {
        if (!hasClass(ele, cls)) {
            ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
        }
    }

    function removeClass(ele, cls) {
        if (hasClass(ele, cls)) {
            var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
            while (newClass.indexOf(' ' + cls + ' ') >= 0) {
                newClass = newClass.replace(' ' + cls + ' ', ' ');
            }
            ele.className = newClass.replace(/^\s+|\s+$/g, '');
        }
    }
    document.querySelector(".login-button").onclick = function(){
        addClass(document.querySelector(".login"), "active")
        setTimeout(function(){
            addClass(document.querySelector(".sk-rotating-plane"), "active")
            document.querySelector(".login").style.display = "none"
        },800)
        setTimeout(function(){
            // 发送ajax 请求 需要 五步
            // （1）创建异步对象
            var ajaxObj = new XMLHttpRequest()
            // （2）设置请求的参数。包括：请求的方法、请求的url。
            ajaxObj.open('post', '${pageContext.request.contextPath}/system/login');
            ajaxObj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

            // （3）发送请求
            ajaxObj.send($("form").serialize());

            //（4）注册事件。 onreadystatechange事件，状态改变时就会调用。
            //如果要在数据完整请求回来的时候才调用，我们需要手动写一些判断的逻辑。
            ajaxObj.onreadystatechange = function () {

                // 为了保证 数据 完整返回，我们一般会判断 两个值
                if (ajaxObj.readyState == 4 && ajaxObj.status == 200) {
                    // 如果能够进到这个判断 说明 数据 完美的回来了,并且请求的页面是存在的
                    // 5.在注册的事件中 获取 返回的 内容 并修改页面的显示
                    var jsonObj = JSON.parse( ajaxObj.responseText );
                    if (jsonObj.type=='success')
                        if($('input[name="type"]:checked').val()==1)
                            window.parent.location.href = '${pageContext.request.contextPath}/system/index';
                        else
                            window.parent.location.href = '${pageContext.request.contextPath}/system/main';
                    else {
                           alert(jsonObj.msg)
                            $("#vcodeImg").click();//切换验证码
                            $("input[name='vcode']").val("");//清空验证码输入框
                    }

                }
                removeClass(document.querySelector(".login"), "active")
                removeClass(document.querySelector(".sk-rotating-plane"), "active")
                document.querySelector(".login").style.display = "block"
            }
        },1500)




    }
</script>

</body>
</html>