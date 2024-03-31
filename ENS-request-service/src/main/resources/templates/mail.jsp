<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mails</title>
</head>
<body>
<h2>List of Mails</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Subject</th>
        <th>Content</th>
    </tr>
    <c:forEach var="mail" items="${mails}">
        <tr>
            <td>${mail.id}</td>
            <td>${mail.subject}</td>
            <td>${mail.content}</td>
        </tr>
    </c:forEach>
</table>

<h2>Add New Mail</h2>
<form action="addMail" method="post">
    Subject: <input type="text" name="subject"><br>
    Content: <input type="text" name="content"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>