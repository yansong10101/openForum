<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Create Category</title>
    <%@page import="forumDB.*"%>
    <script src="/myJspWeb/lib/js/nav.js"></script>
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/nav.css" />
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/index_Style.css" />
</head>
<body>

	<%
		ConfigDB.setConnection();
		String name = request.getParameter("catname");
		String desc = request.getParameter("description");
	%>

    <div id="header">
        <div id="logo">
            <img src="images/npu_icon.png" />
        </div>
        <div id="title1">
            <h1>Online Service Center</h1>
        </div>
        <div id="homeLink">
            <a href="category.jsp">Student Home</a>
        </div>
    </div>

    <%@ include file="list.html" %>

    <div id="contents">
        <%
       		CategoryDB cat = new CategoryDB(name, desc);
       		cat.updateAll();
       		out.println("<p>Successful create a category</p>");        	
        %>
        <a href="ServletPro">Go to Category</a>
	</div>
    <div id="footer">
        <p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p>
    </div>
    <% ConfigDB.stopConnection(); %>
</body>
</html>