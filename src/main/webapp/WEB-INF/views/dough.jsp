<%@ page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<c:import url="/WEB-INF/views/header.jsp"/>

<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#sidebar-collapse"><span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span></button>
            <a class="navbar-brand" href="/jsDonut/admin/adminPanel"><span></span>Admin Panel</a>
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                    <em class="fa fa-envelope"></em><span class="label label-danger">15</span>
                </a>
                    <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <div class="dropdown-messages-box"><a href="${contextPath}/resources/assets/profile.html"
                                                                  class="pull-left">
                                <img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
                            </a>
                                <div class="message-body">
                                    <small class="pull-right">3 mins ago</small>
                                    <a href="#"><strong>John Doe</strong> commented on <strong>your photo</strong>.</a>
                                    <br/>
                                    <small class="text-muted">1:24 pm - 25/03/2015</small>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="dropdown-messages-box"><a href="${contextPath}/resources/assets/profile.html"
                                                                  class="pull-left">
                                <img alt="image" class="img-circle" src="http://placehold.it/40/30a5ff/fff">
                            </a>
                                <div class="message-body">
                                    <small class="pull-right">1 hour ago</small>
                                    <a href="#">New message from <strong>Jane Doe</strong>.</a>
                                    <br/>
                                    <small class="text-muted">12:27 pm - 25/03/2015</small>
                                </div>
                            </div>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <div class="all-button"><a href="#">
                                <em class="fa fa-inbox"></em> <strong>All Messages</strong>
                            </a></div>
                        </li>
                    </ul>
                </li>
                <li class="dropdown"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                    <em class="fa fa-bell"></em><span class="label label-info">5</span>
                </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li><a href="#">
                            <div><em class="fa fa-envelope"></em> 1 New Message
                                <span class="pull-right text-muted small">3 mins ago</span></div>
                        </a></li>
                        <li class="divider"></li>
                        <li><a href="#">
                            <div><em class="fa fa-heart"></em> 12 New Likes
                                <span class="pull-right text-muted small">4 mins ago</span></div>
                        </a></li>
                        <li class="divider"></li>
                        <li><a href="#">
                            <div><em class="fa fa-user"></em> 5 New Followers
                                <span class="pull-right text-muted small">4 mins ago</span></div>
                        </a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.container-fluid -->
</nav>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <div class="profile-sidebar">
        <div class="profile-userpic">
            <img src="http://placehold.it/50/30a5ff/fff" class="img-responsive" alt="">
        </div>
        <div class="profile-usertitle">
            <div class="profile-usertitle-name">ADMIN</div>
            <div class="profile-usertitle-status"><span class="indicator label-success"></span>Online</div>
        </div>
        <div class="clear"></div>
    </div>
    <div class="divider"></div>
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li class="${productActive}"><a href="${contextPath}/jsDonut/admin/product"><em
                class="fa fa-dashboard">&nbsp;</em> Products</a></li>
        <li class="${sprinkleActive}"><a href="${contextPath}/jsDonut/admin/sprinkle"><em
                class="fa fa-dashboard">&nbsp;</em> Sprinkles</a></li>
        <li class="${doughActive}"><a href="${contextPath}/jsDonut/admin/dough"><em class="fa fa-dashboard">&nbsp;</em>
            Doughs</a></li>
        <li class="${fillingActive}"><a href="${contextPath}/jsDonut/admin/filling"><em
                class="fa fa-dashboard">&nbsp;</em> Fillings</a></li>
        <%--<li><a href="${contextPath}/resources/assets/widgets.html"><em class="fa fa-calendar">&nbsp;</em> Widgets</a></li>
        <li><a href="${contextPath}/resources/assets/charts.html"><em class="fa fa-bar-chart">&nbsp;</em> Charts</a></li>
        <li><a href="${contextPath}/resources/assets/elements.html"><em class="fa fa-toggle-off">&nbsp;</em> UI Elements</a></li>
        <li><a href="${contextPath}/resources/assets/panels.html"><em class="fa fa-clone">&nbsp;</em> Alerts &amp; Panels</a></li>
        <li class="parent "><a data-toggle="collapse" href="#sub-item-1">
            <em class="fa fa-navicon">&nbsp;</em> Multilevel <span data-toggle="collapse" href="#sub-item-1"
                                                                   class="icon pull-right"><em class="fa fa-plus"></em></span>
        </a>
            <ul class="children collapse" id="sub-item-1">
                <li><a class="" href="#">
                    <span class="fa fa-arrow-right">&nbsp;</span> Sub Item 1
                </a></li>
                <li><a class="" href="#">
                    <span class="fa fa-arrow-right">&nbsp;</span> Sub Item 2
                </a></li>
                <li><a class="" href="#">
                    <span class="fa fa-arrow-right">&nbsp;</span> Sub Item 3
                </a></li>
            </ul>
        </li>
        <li><a href="login.html"><em class="fa fa-power-off">&nbsp;</em> Logout</a></li>--%>
    </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#">
                <em class="fa fa-home"></em>
            </a></li>
            <li class="active">Doughs</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Doughs</h1>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading container-head">
                    Dough list
                </div>
                <div class="panel-body container-body">
                    <div class="row">
                        <div class="col-md-12 dough-list">
                            <table class="table table-striped dough-table">
                                <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Calories</th>
                                    <th>Price</th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:choose>
                                    <c:when test="${doughList.size() > 0}">
                                        <с:forEach var="dough" items="${doughList}">
                                            <tr class="dough-table__row" data-id="${dough.id}">
                                                <th>${dough.id}</th>
                                                <th>${dough.name}</th>
                                                <th>${dough.calories}</th>
                                                <th>${dough.price}</th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-primary dough-edit">Edit
                                                    </button>
                                                </th>
                                                <th>
                                                    <button type="button" class="btn btn-md btn-danger dough-delete">
                                                        Delete
                                                    </button>
                                                </th>
                                            </tr>
                                        </с:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th colspan="4">Not dough</th>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                </tbody>
                            </table>


                        </div>
                        <div class="col-md-12 dough-form block__display-none">
                            <form role="form">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control dough-name" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <label>Calories</label>
                                    <input class="form-control dough-calories" placeholder="Calories">
                                </div>
                                <div class="form-group">
                                    <label>Price</label>
                                    <input class="form-control dough-price" placeholder="Price">
                                </div>
                            </form>
                            <div class="row">
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-success btn-block dough-save">Save
                                    </button>
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-lg btn-danger btn-block dough-close">Cancel
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->
</div>    <!--/.main-->

<c:import url="/WEB-INF/views/footer.jsp"/>

</body>
</html>