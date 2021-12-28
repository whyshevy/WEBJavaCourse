package controller.command.student;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import domain.Course;
import service.CourseService;
import service.ServiceException;
import service.implementation.CourseServiceImpl;

public class CourseRegistrationPage implements Command{

	private CourseService courseService = new CourseServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long courseChoose = Long.parseLong(request.getParameter("choose"));
		Course course;
		try {
			course = courseService.findById(courseChoose);
		} catch (ServiceException e) {
			e.printStackTrace();
			return "/other_courses.jsp";
		}
		
		request.setAttribute("course", course);
				
		return "/course_registration.jsp";
	}
}
