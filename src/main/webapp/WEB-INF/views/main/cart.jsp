<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Donut Shop</title>
    <!-- for-mobile-apps -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Donut Shop"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!--//for-mobile-apps -->
    <!--Custom Theme files -->
    <link href="${contextPath}/resources/assetsMainPages/css/bootstrap.css" rel="stylesheet" type="text/css"
          media="all"/>
    <link href="${contextPath}/resources/assetsMainPages/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link rel="stylesheet" href="${contextPath}/resources/assetsMainPages/css/flexslider.css" type="text/css"
          media="screen"/>
    <link href="${contextPath}/resources/assetsAdminPanel/css/sweetalert.css" rel="stylesheet">
    <link href="${contextPath}/resources/assetsAdminPanel/css/project.css" rel="stylesheet">
    <link href="${contextPath}/resources/assetsMainPages/css/cardPay.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <!--//Custom Theme files -->
    <!--js-->
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/modernizr.custom.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/jsData/sendAjax.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/sweetalert.min.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/jsData/product.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/bootstrap.js"></script>

    <!--cart-->
    <script src="${contextPath}/resources/assetsMainPages/js/simpleCart.min.js"></script>
    <!--cart-->
    <!--web-fonts-->
    <link href='//fonts.googleapis.com/css?family=Raleway:400,100,100italic,200,200italic,300,400italic,500,500italic,600,600italic,700,700italic,800,800italic,900,900italic'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
          rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Pompiere' rel='stylesheet' type='text/css'>
    <link href='//fonts.googleapis.com/css?family=Fascinate' rel='stylesheet' type='text/css'>
    <!--web-fonts-->
    <!--animation-effect-->
    <link href="${contextPath}/resources/assetsMainPages/css/animate.min.css" rel="stylesheet">
    <script src="${contextPath}/resources/assetsMainPages/js/wow.min.js"></script>
    <script>
        new WOW().init();
    </script>
    <!--//animation-effect-->
    <!--close-button-->
    <script>$(document).ready(function (c) {
        $('.alert-close').on('click', function (c) {
            $('.cart-header').fadeOut('slow', function (c) {
                $('.cart-header').remove();
            });
        });
    });
    </script>
    <script>$(document).ready(function (c) {
        $('.alert-close1').on('click', function (c) {
            $('.cart-header1').fadeOut('slow', function (c) {
                $('.cart-header1').remove();
            });
        });
    });
    </script>
    <script>$(document).ready(function (c) {
        $('.alert-close2').on('click', function (c) {
            $('.cart-header2').fadeOut('slow', function (c) {
                $('.cart-header2').remove();
            });
        });
    });
    </script>
    <!--//close-button-->
    <!--start-smooth-scrolling-->
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/move-top.js"></script>
    <script type="text/javascript" src="${contextPath}/resources/assetsMainPages/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
    </script>
    <!--//end-smooth-scrolling-->
</head>
<body>

<div class="breadcrumbs">
    <div class="container">
        <ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
            <li><a href="/jsDonut/welcome"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a>
            </li>
            <li><a href="/jsDonut/filter"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>Filter</a>
            </li>
        </ol>
    </div>
</div>
<!--header-->
<div class="header">
    <div class="top-header navbar navbar-default"><!--header-one-->
        <div class="container">
            <div class="nav navbar-nav wow fadeInLeft animated" data-wow-delay=".5s">
                <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                    <p>Welcome to Donut Shop<a href="${contextPath}/jsDonut/registration">Sign Up </a> Or <a
                            href="${contextPath}/jsDonut/login">Sign In </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <p> Hi Admin, let's work<a href="/jsDonut/admin/adminPanel">Admin Panel</a> <a
                            href="/jsDonut/account">My Account</a> <a href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_USER')">
                    <p>Hi, ${pageContext.request.userPrincipal.name} <a href="/jsDonut/account">My Account</a> <a
                            href="/jsDonut/logout">Sing Out </a></p>
                </sec:authorize>
            </div>
            <div class="nav navbar-nav navbar-right social-icons wow fadeInRight animated" data-wow-delay=".5s">
                <ul>
                    <li><a href="#"> </a></li>
                    <li><a href="#" class="pin"> </a></li>
                    <li><a href="#" class="in"> </a></li>
                    <li><a href="#" class="be"> </a></li>
                    <li><a href="#" class="you"> </a></li>
                    <li><a href="#" class="vimeo"> </a></li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <div class="header-two navbar navbar-default"><!--header-two-->
        <div class="container">
            <div class="nav navbar-nav header-two-left">
                <ul>
                    <li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>+7 965 002 43 21</li>
                    <li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i><a
                            href="mailto:info@example.com">KrupchakAS@yandex.ru</a></li>
                </ul>
            </div>
            <div class="nav navbar-nav logo wow zoomIn animated" data-wow-delay=".7s">
                <h1><a href="${contextPath}/jsDonut/welcome">Welcome to Donut Shop<b></b><span class="tag">Everything for your Sweet Dream </span>
                </a>
                </h1>
            </div>
            <div class="nav navbar-nav navbar-right header-two-right">
                <div class="header-right my-account">
                    <a href="contact.jsp"><span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                        CONTACT US </a>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//header-->

<!--cart-items-->
<div class="cart-info cart-items ">
    <div class="container">
        <c:choose>
            <c:when test="${sessionScope.order.productList.size() > 0}">
                <с:forEach var="product" items="${sessionScope.order.productList}">
                    <div class="divForRemove">
                        <div class="single-top-left simpleCart_shelfItem wow fadeInRight animated" data-wow-delay=".5s">
                            <div style="float: right" data-id="${product.id}" class="btn_form">
                                <a href="#" style="color: red" class="DeleteProductFromCart item_add">x</a>
                            </div>
                            <span>${product.category.name}</span>
                            <p style="color: #c0a16b;font-size: 20px">${product.name}</p>
                            <h3 style="float: right">${product.price * product.quantity}₽</h3>
                            <br>
                            <br>
                            <h6 style="color: #1b6d85">${product.description}</h6>
                            <h4 style="float: right">Quantity ${product.quantity}</h4>
                        </div>
                    </div>
                    <hr>
                </с:forEach>
                <h2 class="Total-price wow fadeInRight animated" style="float:right">
                    TotalPrice: ${sessionScope.order.totalPrice}</h2>
                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
                    <div class="row">
                        <div class="col-sm-3">
                            <label for="PaymentOption">Payment Option</label>
                            <select id="PaymentOption"
                                    class="form-control">
                                <option disabled value="0">Choose Payment Option</option>
                                <option value="1">CASH</option>
                                <option value="2">CARD</option>
                            </select>
                            <br>

                            <div class="payment block__display-none">
                                <form>
                                    <div style="margin-bottom: 5px" class="form-group owner">
                                        <input type="text"  placeholder="Owner" class="form-control" id="owner">
                                    </div>
                                    <div style="margin-bottom: 5px" class="form-group CVV">
                                        <input type="text" placeholder="CVV" class="form-control" id="cvv">
                                    </div>
                                    <div style="margin-bottom: 5px" class="form-group" id="card-number-field">

                                        <input  type="text" placeholder="Card Number" class="form-control" id="cardNumber">
                                    </div>
                                    <div style="margin-bottom: 5px" class="form-group" id="expiration-date">
                                        <label>Expiration Date</label>
                                        <select>
                                            <option value="01">January</option>
                                            <option value="02">February </option>
                                            <option value="03">March</option>
                                            <option value="04">April</option>
                                            <option value="05">May</option>
                                            <option value="06">June</option>
                                            <option value="07">July</option>
                                            <option value="08">August</option>
                                            <option value="09">September</option>
                                            <option value="10">October</option>
                                            <option value="11">November</option>
                                            <option value="12">December</option>
                                        </select>
                                        <select>
                                            <option value="16"> 2016</option>
                                            <option value="17"> 2017</option>
                                            <option value="18"> 2018</option>
                                            <option value="19"> 2019</option>
                                            <option value="20"> 2020</option>
                                            <option value="21"> 2021</option>
                                        </select>
                                    </div>
                                    <div style="margin-bottom: 5px" class="form-group" id="credit_cards">
                                        <img src="${contextPath}/resources/assetsMainPages/images/visa.jpg" id="visa">
                                        <img src="${contextPath}/resources/assetsMainPages/images/mastercard.jpg" id="mastercard">
                                    </div>
                                </form>
                            </div>

                        </div>

                        <div class="col-sm-3">
                            <label for="DeliveryOption">Delivery Option</label>
                            <select id="DeliveryOption" class="form-control">
                                <option disabled value="0">Choose Delivery Option</option>
                                <option value="1">PICKUP</option>
                                <option value="2">DELIVERY</option>
                            </select>
                            <br>
                            <div class="delivery-option form-group block__display-none">
                                <form class="login">
                                    <input style="margin-bottom: 5px" id="City" placeholder="City" minlength="2" maxlength="32" class="form-control">
                                    <input style="margin-bottom: 5px" id="Street" placeholder="Street" minlength="2" maxlength="32" class="form-control">
                                    <input style="margin-bottom: 5px" id="HouseNumber" placeholder="HouseNumber" minlength="1" maxlength="8"
                                           class="form-control">
                                    <input style="margin-bottom: 5px" id="ApartmentNumber" placeholder="ApartmentNumber" minlength="1" maxlength="8"
                                           class="form-control">
                                    <input style="margin-bottom: 5px" id="PostCode" placeholder="PostCode" minlength="2" maxlength="16"
                                           class="form-control">
                                </form>
                            </div>

                        </div>
                        <div class="col-sm-3"><p class="pickup-option">Самовывоз: -> по адресуг.Санкт-Петербургул.Бухарестская д.100.</p></div>
                        <div class="col-sm-3"><h1 style="padding-left: 160px; padding-top: 10px"><a href="#"><span
                                class="OrderSave label label-success">Save Order</span></a></h1>
                        </div>

                    </div>
                </sec:authorize>
                <sec:authorize access="!hasRole('ROLE_ADMIN') and !hasRole('ROLE_USER')">
                    <h3 style="padding-left: 660px; color: red">To Buy, You Must -> <a href="${contextPath}/jsDonut/login">Sign in</a>
                    </h3>
                </sec:authorize>
            </c:when>
            <c:otherwise>
                <span>Cart is empty</span>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<!--//cart-items-->
<!--footer-->
<div class="footer">
    <div class="container">
        <div class="footer-info">
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".5s">
                <h4 class="footer-logo"><a href="${contextPath}/jsDonut/welcome">Donut <b>Shop</b> <span class="tag">Everything for you Sweet Dream  </span>
                </a></h4>
                <p>© 2018 Donut Shop. All rights reserved</p>
            </div>
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".7s">
                <h3>Popular</h3>
                <ul>
                    <li><a href="about.jsp">About Us</a></li>
                    <li><a href="products.jsp">new</a></li>
                    <li><a href="contact.jsp">Contact Us</a></li>
                    <li><a href="faq.jsp">FAQ</a></li>
                    <li><a href="filter.jsp">Wishlist</a></li>
                </ul>
            </div>
            <div class="col-md-4 footer-grids wow fadeInUp animated" data-wow-delay=".9s">
                <h3>Subscribe</h3>
                <p>Sign Up Now For More Information <br> About Our Company </p>
                <form>
                    <input type="text" placeholder="Enter Your Email" required="">
                    <input type="submit" value="Go">
                </form>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>
<!--//footer-->
<!--search jQuery-->
<script src="${contextPath}/resources/assetsMainPages/js/main.js"></script>
<!--//search jQuery-->
<!--smooth-scrolling-of-move-up-->
<script type="text/javascript">
    $(document).ready(function () {

        var defaults = {
            containerID: 'toTop', // fading element id
            containerHoverID: 'toTopHover', // fading element hover id
            scrollSpeed: 1200,
            easingType: 'linear'
        };

        $().UItoTop({easingType: 'easeOutQuart'});

    });
</script>
<!--//smooth-scrolling-of-move-up-->
<!--Bootstrap core JavaScript
================================================== -->
<!--Placed at the end of the document so the pages load faster -->


</body>
</html>