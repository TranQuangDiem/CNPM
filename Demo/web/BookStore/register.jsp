<!DOCTYPE html>
<html>
<head>
    <title>Best Store</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Best Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- //for-mobile-apps -->
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- js -->
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <!-- //js -->
    <!-- cart -->
    <script src="js/simpleCart.min.js"></script>
    <!-- cart -->
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <!-- for bootstrap working -->
    <script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
    <!-- //for bootstrap working -->
    <!-- animation-effect -->
    <link href="css/animate.min.css" rel="stylesheet">
    <script src="js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
	<link rel="stylesheet" href="../FontAwesome/css/all.css">
	<!-- //animation-effect -->
    <script src="js/checkForm.js"></script>
</head>
<body>
<!-- header -->
<jsp:include page="/header"/>
<!-- //header -->
<!-- breadcrumbs -->
<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
            <li class="active">Register Page</li>
        </ol>
    </div>
</div>
<!-- //breadcrumbs -->
<!-- register -->
<div class="register">
    <div class="container">
        <%
            if (request.getParameter("error")!=null){
        %>
        <div class="alert alert-danger" role="alert">
            <strong>Oh snap!</strong> Your email is already used!
        </div>
        <%
            }
            if (request.getParameter("info")!=null){
        %>
        <div class="alert alert-success" role="alert">
            Create account success! Please check your email to active account before login!
        </div>
        <%
            }
        %>
        <h3 class="animated wow zoomIn" data-wow-delay=".5s">Register Here</h3>
        <div class="login-form-grids">
            <h5 class="animated wow slideInUp" data-wow-delay=".5s">profile information</h5>
            <form id="register-form" name="registerForm" class="animated wow slideInUp" data-wow-delay=".5s" onsubmit="return validateForm()" method="post">

                <input id="email" name="email" type="email" placeholder="Email Address *" onkeyup="validateForm()">
                <p style="color: red; font-size: 12px" id="errorEmail"></p>
                <input id="pass" name="pass" type="password" placeholder="Password *" onkeyup="validateForm()">
                <p style="color: red; font-size: 12px" id="errorPassword"></p>
                <input id="retypePass" name="retypePass" type="password" placeholder="Password Confirmation *" onkeyup="validateForm()">
                <p style="color: red; font-size: 12px" id="errorRepass"></p>

                <div class="register-check-box">
                    <div class="check">
                        <label class="checkbox"><input type="checkbox" name="checkbox"><i> </i>I accept the terms and
                            conditions</label>
                    </div>
                </div>
                <input type="submit" value="Register">
            </form>
            <script>
                function validateForm() {
//
                    var pass = document.registerForm.pass.value;
                    var repass = document.registerForm.retypePass.value;
                    var email = document.registerForm.email.value;
                    var checkbox = document.registerForm.checkbox.checked;
                    var valid = true;
                    //pattern định dạng email
                    var emailPattern =/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    //pattern định dạng password: tối thiểu 8 kí tự, gồm chữ hoa, chữ thường, số và kí tự đặc biệt
                    var passPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/

                    if (!emailPattern.test(email)) {
                        document.getElementById("errorEmail").innerHTML = "* Email is wrong";
                        valid = false;
                    } else {
                        document.getElementById("errorEmail").innerHTML = "";
                    }

                    if (!passPattern.test(pass.trim())) {
                        document.getElementById("errorPassword").innerHTML = "* Password at least 8 characters include uppercase character, lowercase character, special character, number";
                        valid = false;
                    } else {
                        document.getElementById("errorPassword").innerHTML = "";
                    }

                    if (repass.trim() != pass.trim()) {
                        document.getElementById("errorRepass").innerHTML = "* Retype password must be compatible with password";
                        valid = false;
                    } else {
                        document.getElementById("errorRepass").innerHTML = "";
                    }

                    if (!checkbox){
                        valid = false;
                    }

                    return valid;
                }
            </script>
        </div>
        <div class="register-home animated wow slideInUp" data-wow-delay=".5s">
            <a href="/">Home</a>
        </div>
    </div>
</div>
<!-- //register -->
<!-- footer -->

<!-- //footer -->
</body>
</html>