<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="entities.Course" %>

<!DOCTYPE html>
<html>
<head>
<style>
	*{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;
	}
</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div style = "margin-left: 480px; margin-top: 150px">
	<form action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	<div>
		<p>Course Name:
			<%
				Course course = (Course)request
				.getAttribute("course");
				if (course == null) {
					request.getRequestDispatcher("/success_registration.jsp").forward(request, response);
				} else {
					out.println(course.getName());
				}
			%>
		</p>
	</div>
	<div>
		<p>Duration:
			<% 
				if (course != null)
					out.println(course.getHours());
			%>
		</p>
	</div>
	<div>
		<p>Theme:
			<%
				if (course != null)
					out.println(course.getTopic());
			%>
		</p>
	</div>
	<div>
		<p>Teacher:
			<%
				if (course != null)
					out.println(course.getInstructor().getFullName());
			%>
		</p>
	</div>
	<div>
		<p>Course start date:
			<%
				if (course != null)
					out.println(course.getBeginDate());
			%>
		</p>
	</div>
	
	<% 
	if ((Long) request.getSession().getAttribute("currentRole") == 2) {
		out.print("<form action=\"/success_registration\" method=\"get\">" + 
        "<button type=\"Submit\" name=\"course\" value=\"" + course.getId() + "\">Register on course</button>" +
    	"</form>");
    }
	%>
	
	<div>
		<p>
			<form action="/other_courses" method="get">
        		<button type="Submit">Get back to courses</button>
    		</form>
		</p>
	</div>
</div>
</body>
</html>