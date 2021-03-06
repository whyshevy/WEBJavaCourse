package controller.command;

import javax.servlet.http.HttpServletRequest;

import entities.Student;
import entities.User;
import enums.Roles;
import service.StudentService;
import service.UserService;
import service.implementation.StudentServiceImpl;
import service.implementation.UserServiceImpl;
import util.SessionUsing;
import util.Validation;

public class Registration implements SendRequest {
	
	private UserService userService = new UserServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		if (request.getMethod().equals("GET")) {
            return "/registration.jsp";
        }
		
		String login = (String)request.getParameter("login");
        String password = (String)request.getParameter("password");
        String surname = (String)request.getParameter("surname");
        String name = (String)request.getParameter("name");
        String studyYear = (String)request.getParameter("studyYear");
		
        String validationResult = Validation.checkStudent(login, password, surname, name, studyYear);
		if (validationResult != null) { 
			request.setAttribute("errorMessage", validationResult); 
			return "/registration.jsp";
		}
		
		try {
            User createdUser = new User();
            
            createdUser.setLogin(login);
            createdUser.setPassword(password);		
            createdUser.setRole(Roles.STUDENT);
            Long id = userService.save(createdUser);
            createdUser.setId(id);
            
            Student createdStudent = new Student();
            createdStudent.setSurname((String)request.getParameter("surname"));
            createdStudent.setName((String)request.getParameter("name"));
            createdStudent.setStudyYear(Integer.parseInt(studyYear));
            createdStudent.setId(id);
            studentService.create(createdStudent);
            
            SessionUsing.addUserSession(request.getSession(), createdUser, createdStudent);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return "/registration.jsp";
        }
        return "/student.jsp";
	}
	
}
