<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entities.Course" %>	
<%@ page import="entities.Marks" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Courses</title>
<style>
	*{
	background: linear-gradient(#9A5044, #E8D9A9) fixed;
	font-weight: 400;
	}
   table {
    width: 100%;
    background: black;
    color: black;
    border-spacing: 1px;
   }
   td, th {
    background: white;
    padding: 5px;
   }
   form {
   	display: block;
   }
   corner: {
   	position: absolute; 
   	right: 0;
   }
  </style>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Log out </button>
	</form>
	
	<form action="/my_courses" method="get">
	
        <button name="content" value="1" type="Submit">Planned courses</button>
    
	
        <button name="content" value="2" type="Submit">Active courses</button>
    
	
        <button name="content" value="3" type="Submit">Finished courses</button>
    
    </form>
    
    <form action="/student" method="get">
    	<p>
        <button type="Submit">Back to Main Page</button>
    	</p>
    </form>
    
    <table>
   		<tr><th>Course Name</th><th>Duration</th><th>Theme</th><th>Teacher</th><th>Mark</th></tr>
   		
  		<%
   		  		List<Marks> results = (List<Marks>) request.getAttribute("results");
   		  		    		List<Course> courses = (List<Course>) request.getAttribute("courses");
   		  		    		if (courses != null) {
   		  		    			for (int i = 0; i < courses.size(); i++) {
   		  		    				out.print("<tr><td>" + courses.get(i).getName() +
   		  		    					"</td><td>" + courses.get(i).getHours() +
   		  		    					"</td><td>" + courses.get(i).getTopic() + 
   		  		    					"</td><td>" + courses.get(i).getInstructor().getSurname() +
   		  		    					" " + courses.get(i).getInstructor().getName() + 
   		  		    					"</td><td>" + ((results.get(i) == null) ? "No Mark" : results.get(i).getGrade()));
   		  		    				out.println("</td></tr>");
   		  		    			}
   		  		    		}
   		  		%>
  	
  	
  	</table>
</body>
</html>
