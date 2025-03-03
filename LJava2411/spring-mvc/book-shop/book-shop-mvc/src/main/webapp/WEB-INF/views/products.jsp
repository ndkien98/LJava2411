<%--
  Created by IntelliJ IDEA.
  User: 7390
  Date: 2/27/2025
  Time: 8:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Management</title>
</head>
<body>
<h2>Product Management</h2>
<table border="1">
    <thead>
    <tr>
        <th>#</th>
        <th>ID</th>
        <th>Book Title</th>
        <th>Author</th>
        <th>Page Count</th>
        <th>Publisher</th>
        <th>Publication Year</th>
        <th>Genre</th>
        <th>Price</th>
        <th>Discount</th>
        <th>Stock Quantity</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="productDTO" items="${productDTOS}">
        <tr>
            <td>${productDTO.id}</td>
            <td>${productDTO.bookTitle}</td>
            <td>${productDTO.author}</td>
            <td>${productDTO.pageCount}</td>
            <td>${productDTO.publisher}</td>
            <td>${productDTO.publicationYear}</td>
            <td>${productDTO.genre}</td>
            <td>${productDTO.price}</td>
            <td>${productDTO.discount}</td>
            <td>${productDTO.stockQuantity}</td>
            <td>
                <a href="view?id=${productDTO.id}">View</a> |
                <a href="edit?id=${productDTO.id}">Edit</a> |
                <a href="delete?id=${productDTO.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

