package action;
import forumDB.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletPro
 */
@WebServlet("/ServletPro")
public class ServletPro extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		PrintWriter out = response.getWriter();		
		
		HttpSession session = request.getSession(true);
		UserDB user = (UserDB)session.getAttribute("user");
		String userName = user.getUserName();
		
		out.println("<html><head>");
		out.println("<title>Registration Confirmation</title>");
		out.println("<script src='/myJspWeb/lib/js/nav.js'></script>" +
					"<link rel='stylesheet' href='/myJspWeb/lib/CSS/nav.css'/>" + 
					"<link rel='stylesheet' href='/myJspWeb/lib/CSS/index_Style.css'/></head><body>");
		
		out.println("<script language = 'JavaScript'>");
		out.println("var timeout = 500;" + 
					"var closetimer = 0;" +
					"var ddmenuitem = 0;" +
					"function mopen(id){mcancelclosetime();" +
					"if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';" +
					"ddmenuitem = document.getElementById(id);" +
					"ddmenuitem.style.visibility = 'visible';}" +
					"function mclose(){if(ddmenuitem) ddmenuitem.style.visibility = 'hidden';}" +
					"function mclosetime(){closetimer = window.setTimeout(mclose, timeout);}" +
					"function mcancelclosetime(){if(closetimer){window.clearTimeout(closetimer);closetimer = null;}}" +
					"document.onclick = mclose;");
		out.println("</script>");
		
		//page header
		out.println("<div id='header'>");
		out.println("<div id='logo'><img src='images/npu_icon.png'/></div>");
		out.println("<div id='title1'><h1>Online Service Center</h1></div>");
		out.println("<div id='homeLink'>Hello " +userName+" "+ "<a href='login.jsp'>Logout</a></div></div>");
		
		//page list
		out.println("<div id='listing'>");
		out.println("<ul id='sddm'>");		
		out.println("<li><a href='#' onmouseover='mopen('m1')' onmouseout='mclosetime()'>Admission</a></li>");
		out.println("<li><a href='#' onmouseover='mopen('m2')' onmouseout='mclosetime()'>Learning resource</a></li>");
		out.println("<li><a href='#' onmouseover='mopen('m3')' onmouseout='mclosetime()'>Records</a>");
		out.println("<div id='m3' onmouseover='mcancelclosetime()' onmouseout='mclosetime()'>");
		out.println("<a href='myCourse.html'>My Courses</a>");
		out.println("<a href='personalInfo.html'>Personal Info</a>");
		out.println("<a href='adminInfo.html'>Admission Info</a>");
		out.println("<a href='acdamicInfo.html'>Academic Info</a>");
		out.println("<a href='attendanceInfo.html'>Attendance Records</a></div></li>");
		out.println("<li><a href='#' onmouseover='mopen('m4')' onmouseout='mclosetime()'>Service Request</a>");
		out.println("<div id='m4' onmouseover='mcancelclosetime()' onmouseout='mclosetime()'>");
		out.println("<a href='CTP.html'>CPT Application</a>");
		out.println("<a href='transcriptPage.html'>Transcripts</a></div></li>");
		out.println("<li><a href='#' onmouseover='mopen('m5')' onmouseout='mclosetime()'>Registration</a></li>");
		out.println("<li><a href='#' onmouseover='mopen('m6')' onmouseout='mclosetime()'>e-Services</a>");
		out.println("<div id='m6' onmouseover='mcancelclosetime()' onmouseout='mclosetime()'>");
		out.println("<a href='jobPage.html'>Job Board</a></div></li></ul></div>");
		
		
		try {
			ConfigDB.setConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//page content
		out.println("<div id='contents'>");
		
		out.println("<table id='catTable'><tr class=''>");
		out.println("<th class='catColumn'><p class='catColumn'>Category</p></th>");
		out.println("<th class='numTopic'><p class='catColumn'>Topics</p></th></tr>");
		ArrayList<CategoryDB> list = null;
		try {
			list = CategoryDB.getCatList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	for(int i = 0; i < list.size(); i++){
    		CategoryDB temp = list.get(i);
    		String catname = temp.getCatName();
    		String catdesc = temp.getCatDesc();
    		int id = temp.getCatId();
			out.println("<tr>");
			out.println("<td class='catColumn'>");
			out.println("<form id='catForm' action='topic.jsp' method='GET'>");
			out.println("<a id="+id+" href='javascript:formSubmit()' onClick='get_click(this.id)'>" + catname + "</a>");
			out.println("<input id='hdText' type='hidden' name='catid' value=''></form>");
			out.println("<p class='desc'>" + catdesc + "</p></td>");
			try {
				out.println("<td class='numTopic'>" +TopicDB.getNumTopic(id)+ "</td>");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("</tr>");			
    	}
    	out.println("</table>");
    	
		out.println("<script>");
		out.println("function formSubmit() { document.getElementById('catForm').submit();}");
		out.println("function get_click(id){ document.getElementById('hdText').value=id;}");
		out.println("</script>");
    	
		out.println("</div>");
		out.println("<div id='footer'><p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p></div>");
		out.println("</body></html>");
	}
}
