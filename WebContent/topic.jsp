<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Topic Page</title>
    <%@page import="forumDB.*" import="java.util.*"%>
    <script src="/myJspWeb/lib/js/nav.js"></script>
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/nav.css" />
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/index_Style.css" />
</head>
<body>
	<%
		ConfigDB.setConnection();
		int id = Integer.parseInt(request.getParameter("catid"));
		UserDB user = (UserDB)session.getAttribute("user");
		String userName = user.getUserName();
	%>
    <div id="header">
        <div id="logo">
            <img src="images/npu_icon.png" />
        </div>
        <div id="title1">
            <h1>Online Service Center</h1>
        </div>
        <div id="homeLink">
        	<a href="category.jsp">Back to category</a>-
            Hello <%=userName+" " %><a href="login.jsp">Logout</a></br></br></br>
            <a href="createtopic.jsp" style="float:right;">create topic</a>
        </div>
    </div>

    <%@ include file="list.html" %>
	
    <div id="contents">
    
    	<table id="topTable">
    		<tr class="">
    			<th class="catColumn"><p class="topColumn">Topic</p></th>
    			<th class="catColumn"><p class="topColumn">Owner</p></th>
    			<th class="catColumn"><p class="topColumn">Date</p></th>
    			<th class="catColumn"><p class="topColumn">Posts</p></th>
    		</tr>
	        <% 
				ArrayList<TopicDB> list = TopicDB.getTopList(id);
	        	for(int i = 0; i < list.size(); i++){
	        		TopicDB temp = list.get(i);
	        		String topicName = temp.getTopicName();
	        		String topicCont = temp.getTopicCont();
	        		Date topicDate = temp.getTopicDate();
	        		int topicCat = id;
	        		int topicUser = temp.getTopicUser();
	        		int topid = temp.getId();
	        %>
        	<tr>
        		<td class="catColumn">
	        		<form id="topicForm" action="post.jsp" method="GET">
		        		<a id="<%=topid %>" href="javascript:formSubmit()" onClick="get_click(this.id)"><%=topicName %></a>
	        			<input id="hdText" type="hidden" name="topid" value="">
	        		</form>
        		<p class="catdesc"><%=topicCont %></p>
        		</td>
        		<td class="owner"><%=UserDB.getUserNameById(topicUser) %></td>
        		<td class="createdate"><%=topicDate.toString() %></td>
        		<td class="numPost"><%=PostDB.getNumPost(topid) %></td>
        	</tr>
        <%	
        	}
        %>        
        </table>
    	<script>
		function formSubmit()
		{
			document.getElementById("topicForm").submit();
		}
		function get_click(id){
			document.getElementById("hdText").value=id;
		}
		</script>
	</div>
    <div id="footer">
        <p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p>
    </div>
</body>
</html>