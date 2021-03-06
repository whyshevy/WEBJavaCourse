package service;

import java.util.List;

import entities.Assignment;
import entities.Course;
import entities.Student;


public interface AssignmentService {
    List<Assignment> findAll() throws ServiceException;

    Assignment findById(Long id) throws ServiceException;

    List<Assignment> findByStudent(Long studentId) throws ServiceException;

    List<Assignment> findInstructorAssignment(Long instructorId) throws ServiceException;

    Long save(Assignment assignment) throws ServiceException;

    void delete(List<Long> ids) throws ServiceException;

    void fillAssignments(Assignment assignment) throws ServiceException;

	int checkAssign(Long id, Long id2);

	List<Student> findStudentsByCourse(Long courseId);

	Assignment findByStudentAndCourse(Long studentId, Long courseId) throws ServiceException;
}
