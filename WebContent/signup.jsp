<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Sign up page</title>
    <%@page import="forumDB.*"%>
    <script src="/myJspWeb/lib/js/nav.js"></script>
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/nav.css" />
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/index_Style.css" />
</head>
<body>

	<%
		ConfigDB.setConnection();
		String name = request.getParameter("userName");
		String pass = request.getParameter("password");
		String confirm = request.getParameter("confirm");
		String email = request.getParameter("email");
	%>

    <div id="header">
        <div id="logo">
            <img src="images/npu_icon.png" />
        </div>
        <div id="title1">
            <h1>Online Service Center</h1>
        </div>
        <div id="homeLink">
            <a href="login.jsp">Login</a>
        </div>
    </div>

    <%@ include file="list.html" %>

    <div id="contents">
        <% 
			if(UserDB.isSameName(name)){
				out.println("<p>The name has already been used</p>");
		%>
			<p><a href="signup.html">back</a></p>
		<%
			}else if(!pass.equals(confirm)){
        		out.println("<p>The password comfirmation is incrrect</p>");
   		%>
			<p><a href="signup.html">back</a></p>
   		<%
        	}else{
        		UserDB usr = new UserDB(name, pass, email);
        		usr.updateAll();
        		out.println("<p>Successful create the account</p>");
   		%>
   			<p><a href="login.jsp">login</a></p>
   		<%
        		ConfigDB.stopConnection();
        	}
        	
        %>
	</div>
    <div id="footer">
        <p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p>
    </div>
</body>
</html>