<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Post Page</title>
    <%@page import="forumDB.*" import="java.util.*"%>
    <script src="/myJspWeb/lib/js/nav.js"></script>
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/nav.css" />
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/index_Style.css" />
</head>
<body>
	<%
		ConfigDB.setConnection();
		int id = Integer.parseInt(request.getParameter("topid"));
		
		UserDB user = (UserDB)session.getAttribute("user");
		String userName = user.getUserName();
		int userId = UserDB.getIdByName(userName);
		
		String title = TopicDB.getTopicTitle(id);
		String desc = TopicDB.getTopicDesc(id);		
		String postDesc = request.getParameter("postdescription");
		int catId = TopicDB.getTopicCatId(id);
		String backMsg = null;
		if(postDesc!=null){
			PostDB temp = new PostDB(postDesc,id,userId);
			temp.updateAll();
			backMsg = "Successful post a message !";
			postDesc = null;
		}
	%>
    <div id="header">
        <div id="logo">
            <img src="images/npu_icon.png" />
        </div>
        <div id="title1">
            <h1>Online Service Center</h1>
        </div>
        <div id="homeLink">
        	<a href="topic.jsp?catid=<%=catId %>">Back to topics</a>-
            Hello <%=userName+" " %><a href="login.jsp">Logout</a>
        </div>
    </div>

    <%@ include file="list.html" %>
	
    <div id="contents">
    	
    	<%
    		if(backMsg!=null){
    	%>
    		<div id="backMsg"><%=backMsg %></div>    	
    	<%
    		}
    	%>
    	
    	<div id="postHeader">    		
    		<img id="postImg" src="images/post_log.jpg" style="width:50px;"/>
    		<h2><%=title %></h2>    		
    		<p><%=desc %></p>    		
    	</div>
    
    	<table id="topTable">
    		<tr class="">
    			<th class="catColumn"><p class="topColumn">Posts</p></th>
    			<th class="catColumn"><p class="topColumn">Owner</p></th>
    			<th class="catColumn"><p class="topColumn">Date</p></th>
    		</tr>    
    	<%
	    	ArrayList<PostDB> list = PostDB.getPostList(id);
	    	for(int i = 0; i < list.size(); i++){
	    		PostDB temp = list.get(i);
	    		String postCont = temp.getPostCont();
	    		Date postDate = temp.getPostDate();
	    		int postById = temp.getPostUser();
	    		String postUserName = UserDB.getUserNameById(postById);
    	%>
    		<tr>
        		<td class="catColumn"><p class="catdesc"><%=postCont %></p></td>
        		<td class="owner"><%=postUserName %></td>
        		<td class="createdate"><%=postDate.toString() %></td>
        	</tr>
    	<% } %>
    	
    	</table>
    	
    	<form id="postForm" action="post.jsp" method="POST">
           	<p><label class="postLabel">post comments :</label><input class="postText" type="text" name="postdescription" style="height:50px;"></p>
           	<br>
           	<input type="hidden" name="topid" value="<%=id %>">
           	<p><input type="submit" id="signupLabel" onclick="formReset()" style="margin-top:25px; width:70px; font-size:20px; font-family:Comic Sans; height:35px;" value="post"></p>
    	</form>
    	
	</div>
    <div id="footer">
        <p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p>
    </div>
</body>
</html>