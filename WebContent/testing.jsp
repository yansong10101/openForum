<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@page import="forumDB.*" import="action.*" import="java.util.*" import="java.io.*"%>
</head>
<body>
	<%
		int flag = 0;
		ArrayList<CategoryDB> list = null;
		if(flag==1){	
			list = (ArrayList<CategoryDB>)session.getAttribute("categoryList");
			for(int i = 0; i < list.size(); i++){
				list.get(i).getCatName();
			}
		}
	%>		
	<form action="TestServlet" method="GET">
		<input type="text" name="data">
		<%flag = 1;%>
		<input type="submit" value="Go">
	</form>
	
	<c:out value="${name}"/><p>
	
</body>
</html>