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
    String email = (String) request.getAttribute("email");
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
        <h3 class="animated wow zoomIn" data-wow-delay=".5s">ForgotPassword</h3>
        <div class="login-form-grids animated wow slideInUp" data-wow-delay=".5s">
            <form action="http://localhost:8080/BookStore/BookStoredoForgotPass" method="post">
                <span>* Nhập email của tài khoản để lấy mã xác thực</span>
                <div class="alert-danger">
                    <%=erremail == null ? "" : erremail%>
                </div>
<%--                3.người dùng nhập email của tài khoản để lấy mã xác thực--%>
                <input type="text" name="email" id="inputEmail" placeholder="email   Vd: abc@gmail.com" required=" " value=<%=email==null?"":email%> >
                <input type="submit" value="send">
            </form>
        </div>
    </div>
</div>
<!-- //login -->
<!-- footer -->
<!-- //footer -->
</body>
</html>
