<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="collapse" id="regpage">
    <div class="card card-body">
        <div class="container">
            <form:form method="POST" modelAttribute="userForm" class="form-signin">

                    <form:input type="text" path="login" class="form-control"
                                placeholder="Login"></form:input>
                    <div class="has-error">
                        <form:errors path="login"></form:errors></div>


                    <form:input type="text" path="firstName" class="form-control"
                                placeholder="FirstName"></form:input>
                    <div class="has-error">
                        <form:errors path="firstName"></form:errors></div>


                    <form:input type="text" path="surName" class="form-control"
                                placeholder="SurName"></form:input>
                    <div class="has-error">
                        <form:errors path="surName"></form:errors></div>


                    <form:input type="text" path="phoneNumber" class="form-control"
                                placeholder="PhoneNumber"></form:input>
                    <div class="has-error">
                        <form:errors path="phoneNumber"></form:errors></div>


                    <form:input type="date" path="birthDate" class="form-control"
                                placeholder="BirthDate"></form:input>
                    <div class="has-error">
                        <form:errors path="birthDate"></form:errors></div>


                    <form:input type="text" path="email" class="form-control" placeholder="Email"></form:input>
                    <div class="has-error">
                        <form:errors path="email"></form:errors></div>


                    <form:input type="password" path="password" class="form-control"
                                placeholder="Password"></form:input>
                    <div class="has-error">
                        <form:errors path="password"></form:errors></div>


                    <form:input type="password" path="confirmPassword" class="form-control"
                                placeholder="Confirm your password"></form:input>
                    <div class="has-error">
                        <form:errors path="confirmPassword"></form:errors>
                    </div>

                <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                <button class="btn btn-xs" onclick="a()">Sign up</button>
            </form:form>
        </div>
    </div>
</div>