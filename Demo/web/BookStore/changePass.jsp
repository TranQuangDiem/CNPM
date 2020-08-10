<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <title>Best Store</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Best Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //for-mobile-apps -->
    <%--<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />--%>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/all.css" rel="stylesheet" type="text/css" media="all" />
    <link href="BookStore/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link href="BookStore/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link rel="stylesheet" href="bootstrap-4.3.1-dist/css/Stylecss.css" type="text/css" media="all"/>
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <script src="BookStore/js/jquery.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"> </script>
    <script src="BookStore/js/simpleCart.min.js"> </script>
    <!-- cart -->
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="BookStore/css/jquery-ui.css">
    <!-- for bootstrap working -->
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <script type="text/javascript" src="BookStore/js/bootstrap-3.1.1.min.js"></script>
    <!-- //for bootstrap working -->
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
    <!-- animation-effect -->
    <link href="css/animate.min.css" rel="stylesheet">
    <script src="js/wow.min.js"></script>
    <link href="BookStore/css/animate.min.css" rel="stylesheet">
    <script src="BookStore/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <link rel="stylesheet" href=css/all.css">
    <link rel="stylesheet" href=BookStore/css/all.css">
    <!-- //animation-effect -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<%
    String erremail = (String) request.getAttribute("errmail");
    String errpass = (String) request.getAttribute("errmatkhau");
    String errmatkhaucon = (String) request.getAttribute("errmatkhaucon");
    String errmaxacnhan = (String) request.getAttribute("errmaxacnhan");
    String email= (String) request.getAttribute("email");
%>
<body>
<!-- header -->
<jsp:include page="/header"/>
<!-- //header -->
<!-- breadcrumbs -->
<%--<div class="breadcrumbs">--%>
<%--    <div class="container">--%>
<%--        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">--%>
<%--            <li><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>--%>
<%--            <li class="active">Login Page</li>--%>
<%--        </ol>--%>
<%--    </div>--%>
<%--</div>--%>
<!-- //breadcrumbs -->
<!-- login -->
<div class="login">
    <div class="container">
        <h3 class="animated wow zoomIn" data-wow-delay=".5s">changePass</h3>
        <div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">
            <form action="http://localhost:8080/BookStore/BookStoreChangePass" method="post">
<%--                11.Người dùng, quản lý, quản trị viên nhập email,pass,nhập lại pass,mã xác thực.--%>
                <div class="alert-danger">
                    <%=erremail == null ? "" : erremail%>
                </div>
                <input type="text" name="email" id="inputEmail" placeholder="email   Vd: abc@gmail.com" required=" " value=<%=email==null?"":email%> >
                <div class="alert-danger">
                    <%=errpass == null ? "" : errpass%>
                </div>
                <input type="password" name="pass" placeholder="New Password    vd:Abc123@" required=" " >
                <div class="alert-danger">
                    <%=errmatkhaucon == null ? "" : errmatkhaucon%>
                </div>
                <input type="password" name="repass" placeholder="Enter a new password    vd:Abc123@"required=" " >
                <div class="alert-danger">
                    <%=errmaxacnhan == null ? "" : errmaxacnhan%>
                </div>
                <input type="text" name="maxacthuc" placeholder="Verification code    "required=" " >

                <input type="submit" value="change">
            </form>
        </div>
    </div>
</div>
<!-- //login -->
<!-- footer -->
<!-- //footer -->
</body>
</html>
