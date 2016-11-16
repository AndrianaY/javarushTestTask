<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>List of users</h1>
<table border="2" width="70%" cellpadding="2">
    <tr><th>Firstname</th><th>Lastname</th><th>Telephone</th><th>Email</th><th>Edit</th><th>Delete</th></tr>
    <c:forEach var="us" items="${list}">
        <tr>
            <td>${us.id}</td>
            <td>${us.firstname}</td>
            <td>${us.lastname}</td>
            <td>${us.telephone}</td>
            <td>${us.email}</td>
            <td><a href="edit/${us.id}">Edit</a></td>
            <td><a href="delete/${us.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="userform">Add New User</a>