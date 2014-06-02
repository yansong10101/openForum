<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Category Page</title>
    <%@page import="forumDB.*" import="java.util.*"%>
    <script src="/myJspWeb/lib/js/nav.js"></script>
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/nav.css" />
    <link rel="stylesheet" href="/myJspWeb/lib/CSS/index_Style.css" />
</head>
<body>
	<%
		ConfigDB.setConnection();
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
            Hello <%=userName+" " %><a href="login.jsp">Logout</a></br></br></br>
            <%
            	if(userName.equals("admin")){
            %>
            		<a href="createCat.html" style="float:right;">create category</a>
            <%		
            	}
            %>
        </div>
    </div>

    <%@ include file="list.html" %>

    <div id="contents">
    
    	<table id="catTable">
    		<tr class="">
    			<th class="catColumn"><p class="catColumn">Category</p></th>
    			<th class="numTopic"><p class="catColumn">Topics</p></th>
    		</tr>
        <% 
			ArrayList<CategoryDB> list = CategoryDB.getCatList();
        	for(int i = 0; i < list.size(); i++){
        		CategoryDB temp = list.get(i);
        		String catname = temp.getCatName();
        		String catdesc = temp.getCatDesc();
        		int id = temp.getCatId();
        %>
        	<tr>
        		<td class="catColumn">	        		
	        		<form id="catForm" action="topic.jsp" method="GET">
		        		<a id="<%=id %>" href="javascript:formSubmit()" onClick="get_click(this.id)"><%=catname %></a>
	        			<input id="hdText" type="hidden" name="catid" value="">
	        		</form>	        		
        			<p class="desc"><%=catdesc %></p>
        		</td>
        		<td class="numTopic"><%=TopicDB.getNumTopic(id) %></td>
        	</tr>
        <%	
        	}
        %>        
        </table>
        
        <script>
		function formSubmit()
		{
			document.getElementById("catForm").submit();
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