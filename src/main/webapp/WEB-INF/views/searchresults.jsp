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
<div class="generic-container" width="100%">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead"><strong>List of founded by "${searchedtext}" Javarush Users </strong></span></div>

        <form:form method="POST" class="form-horizontal">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>N#</th>
                <th>Name</th>
                <th>Age</th>
                <th>isAdmin</th>
                <th>create date</th>
                <th>edit</th>
                <th>delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${searchresults}" var="user" varStatus="itr">
                <tr>
                    <td>${offset + itr.index +1 }</td>
                    <td>${user.name }</td>
                    <td>${user.age }</td>
                    <th>${user.isAdmin }</th>
                    <th>${user.createdate}</th>
                    <td><a href="<c:url value='/edit-user-${user.id}' />" class="btn btn-success custom-width">edit</a></td>
                    <td><a href="<c:url value='/delete-${searchedtext}-user-${user.id}' />" class="btn btn-danger custom-width">delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <tag:paginate max="10" offset="${offset}" count="${count}"
                      uri="/usersview" next="&raquo;" previous="&laquo;" />
        </form:form>

        <span class="well floatRight">
        Go back to <a href="<c:url value='/usersview' />">Users List</a>
        </span>
    </div>
</div>

</body>
</html>