<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>

<%--<h1>List of users</h1>--%>
<%--<table border="2" width="70%" cellpadding="2">--%>
    <%--<tr><th>ID</th><th>Firstname</th><th>Lastname</th><th>Telephone</th><th>Email</th><th>Edit</th><th>Delete</th></tr>--%>
    <%--<c:forEach var="us" items="${list}">--%>
        <%--<tr>--%>
            <%--<td>${us.id}</td>--%>
            <%--<td>${us.name}</td>--%>
            <%--<td>${us.age}</td>--%>
            <%--<td>${us.createdate}</td>--%>
            <%--<td>${us.isAdmin}</td>--%>
            <%--<td><a href="edit/${us.id}">Edit</a></td>--%>
            <%--<td><a href="delete/${us.id}">Delete</a></td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>
<%--<br/>--%>
<%--<a href="userform">Add New User</a>--%>





<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/customTaglib.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Javarush Users List</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead"><strong>List of Javarush Users </strong></span></div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="well">Submit</button>
        </form>
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
        <c:forEach items="${users}" var="user" varStatus="itr">
            <tr>
                <td>${offset + itr.index +1 }</td>
                <td>${user.name }</td>
                <td>${user.age }</td>
                <th>${user.isAdmin }</th>
                <th>${user.createdate}</th>
                <td><a href="edit/${user.id}" class="btn btn-success custom-width">Edit</a></td>
                <td><a href="delete/${user.id}" class="btn btn-danger custom-width">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <tag:paginate max="7" offset="${offset}" count="${count}"
                  uri="/usersview" next="&raquo;" previous="&laquo;" />
</div>
<button href="userform">Add New User</button>

<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
</body>
</html>