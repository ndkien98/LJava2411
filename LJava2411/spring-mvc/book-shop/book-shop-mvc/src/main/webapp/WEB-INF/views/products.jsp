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
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.bookTitle}</td>
            <td>${product.author}</td>
            <td>${product.pageCount}</td>
            <td>${product.publisher}</td>
            <td>${product.publicationYear}</td>
            <td>${product.genre}</td>
            <td>${product.price}</td>
            <td>${product.discount}</td>
            <td>${product.stockQuantity}</td>
            <td>
                <a href="view?id=${product.id}">View</a> |
                <a href="edit?id=${product.id}">Edit</a> |
                <a href="delete?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

