package packt.book.jee.eclipse.ch4.bean;

import packt.book.jee.eclipse.ch4.dao.*;
import java.sql.*;
import java.util.List;

public class Course {
	private int id;
	private String name;
	private int credits;
	private Teacher teacher;
	private CourseDAO courseDAO = new CourseDAO();

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
		return name != null && credits != 0;
	}
	
	public void addCourse() throws SQLException {
		courseDAO.addCourse(this);
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public List<Course> getCourses() throws SQLException {
		return courseDAO.getCourses();
	}
}