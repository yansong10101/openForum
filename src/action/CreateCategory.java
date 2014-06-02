package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import forumDB.CategoryDB;
import forumDB.ConfigDB;
import forumDB.UserDB;

/**
 * Servlet implementation class CreateCategory
 */
@WebServlet("/CreateCategory")
public class CreateCategory extends HttpServlet {	
	
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
		
		String name = request.getParameter("catname");
		String desc = request.getParameter("description");
		
		out.println("<div id='contents'>");
		
//		out.println("<p>"+name+"</p>");
//		out.println("<p>"+desc+"</p>");
		CategoryDB cat = new CategoryDB(name, desc);
   		try {
			cat.updateAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("===========update error==========");
		}
   		out.println("<p>Successful create a category</p>");
   		out.println("<a href='ServletPro' style='margin-left:20px;'>Go to Category</a>");
   		
   		out.println("</div>");
		out.println("<div id='footer'><p>&copy; 2013 Northwestern Polytechnic University. All rights reserved.</p></div>");
		out.println("</body></html>");
	}
}
