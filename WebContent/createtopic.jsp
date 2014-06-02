<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Create Topic</title>
    <%@page import="forumDB.*"%>
    <script src="/myJspWeb/lib/js/nav.js"></script>
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/nav.css" />
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/index_Style.css" />
</head>
<body>

	<%
		ConfigDB.setConnection();
	
		UserDB user = (UserDB)session.getAttribute("user");
		String userName = user.getUserName();
		int userId = UserDB.getIdByName(userName);
	
		int id = Integer.parseInt(request.getParameter("catid"));
		String name = request.getParameter("topname");
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
            <a href="login.jsp">Logout</a>-<a href="category.jsp">Home</a>
        </div>
    </div>

    <%@ include file="list.html" %>

    <div id="contents">
        <% 
        	if(name==null){        	
        %>
        <div id="homeTag">
            <h2>Create Topic</h2>
            <div id="line"></div>
        </div>

        <div id="catInfo">
            <form id="createcat" action="createtopic.jsp" method="POST">
            	<p><label class="signupLabel">Subject Name :</label><input class="catText" type="text" name="topname"></p>
            	<p><label class="signupLabel">Description  :</label><input class="catText" type="text" name="description" style="height:100px;"></p>
            	<br>
            	<p><input type="submit" id="signupLabel" style="margin-top:70px;"></p>
            </form>
        </div>        
        <%
        	}else{
        		TopicDB top = new TopicDB(name, id, 10101);
        		top.setTopicCont(desc);
           		top.updateAll();
           		out.println("<p>Successful create a category</p>");
        	}
        %>
	</div>
    <div id="footer">
        <p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p>
    </div>
    <% ConfigDB.stopConnection(); %>
</body>
</html>