<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<%@page import="forumDB.*"%>
<script src="/myJspWeb/lib/js/jquery-1.9.1.js"></script>
<script src="/myJspWeb/lib/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="/myJspWeb/lib/CSS/login.css" />
<link rel="stylesheet" href="/myJspWeb/lib/CSS/jquery-ui-1.10.3.custom.css" />
</head>
<body>
	<%
		ConfigDB.setConnection();
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
	%>
	<div id="header">
    	<div id="backgroudHeader">
	        <img src="images/npu_icon.png" height="50" />
	        <a href="signup.html" class="headerLink" style="color:white; right:50px;">Register</a>
	    </div>
    </div>
    <%
    	if(name==null && pass==null){    		
    %>
	    <h1>Welcome to NPU Forum !</h1>
	    <div id="block">
	        <form name="login" action="testLogin.jsp" method="POST">
	            <h4>User Name</h4><input type="text" name="username"/>
	            <h4>Password</h4><input type="password" name="password"/>
	            <div id="confirm">
	                <input type="submit" class="button" value="Login"/>
	                <a href="#">forget password ?</a>
	            </div>
	        </form>
	    </div>
    <%
    	}else if(name!=null && pass!=null){
    		if(UserDB.isSamePasswd(name, pass)){
    			UserDB user = UserDB.getUserByName(name);
    			session.setAttribute("user",user);
    %>
	    <div class="loginMsg">
	    	<p>Successful login !</p>
	    	<p><a href="ServletPro">click here to forum</a>-<a href="testServlet.html">create a category</a></p>
	    </div>
    <%
    	}else{
	%>
	    <div class="loginMsg">
	    	<p>Error information</p>
	    	<p><a href="testLogin.jsp">go back</a></p>
	    </div>
	<%
    		}
    	}
    %>
    <script>
	    $(function () {
	        $(".button").button();
	    });
    </script>
</body>
</html>