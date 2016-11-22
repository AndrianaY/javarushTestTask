<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Javarush Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet">
</head>

<body>
<div class="generic-container">

    <c:choose>
        <c:when test="${edit}">
            <div class="well lead">User Editing Form</div>
        </c:when>
        <c:otherwise>
            <div class="well lead">User Registration Form</div>
        </c:otherwise>
    </c:choose>


    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="name">Name</label>
                <div class="col-md-7">
                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="name" class="help-inline"/>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="age">Age</label>
                <div class="col-md-7">
                    <form:input type="text" path="age" id="age" class="form-control input-sm" />
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="age" class="help-inline"/>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="isAdmin">Profile type</label>
                <div class="col-md-7">
                    <form:input type="text" path="isAdmin" id="isAdmin" class="form-control input-sm" />
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="isAdmin" class="help-inline"/>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="createdate">create date</label>
                <div class="col-md-7">
                    <form:input type="text" readonly="true" path="createdate" id="createdate" class="form-control input-sm" modelAttribute="date" value="${date}"/>
                    <%--<div class="has-error">--%>
                        <%--<form:errors path="createdate" class="help-inline"/>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/usersview' />">Cancel</a>
                    </c:when>
                    <c:when test="${searched}${edit}">
                        <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/tosearchresults' />">Cancel</a>
                    </c:when>
                    <c:otherwise>
                        <input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a
                            href="<c:url value='/usersview' />">Cancel</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>


