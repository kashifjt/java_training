package packt.book.jee.eclipse.ch4.bean;

import java.sql.SQLException;
import java.util.List;

import org.mockito.Mockito;

import packt.book.jee.eclipse.ch4.dao.CourseDAO;

public class Course {
	private int id;
	private String name;
	private int credits;
	private Teacher teacher;
	private int teacherId;
	private CourseDAO courseDAO;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public boolean isValidCourse() {
		return name != null && credits != 0 && name.trim().length() > 0;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}	
	public void addCourse() throws SQLException {
		CourseDAO.addCourse(this);
	}
	public List<Course> getCourses() throws SQLException {
		return CourseDAO.getCourses();
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}
	public void addStudent(Student student) throws EnrolmentFullException, SQLException {
		int currentEnrolment = courseDAO.getNumStudentsInCourse(id);
		if (currentEnrolment >= getMaxStudents())
			throw new EnrolmentFullException("Course if full. Enrolment closed");
		courseDAO.enrollStudentInCourse(id, student.getId());
	}
}
