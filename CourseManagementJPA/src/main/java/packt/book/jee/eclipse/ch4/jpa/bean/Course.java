package packt.book.jee.eclipse.ch4.jpa.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.REFRESH;

@ManagedBean(name = "course")
@RequestScoped
@Entity
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id; 
	@NotNull
	@Column(name = "name")
	private String name;
	@Min(1)
	@Column(name = "credits")
	private int credits;
	@ManyToOne(cascade = { MERGE, REFRESH })
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;
	@ManyToMany(cascade = { MERGE, REFRESH }, mappedBy = "courses")
	private List<Student> students;

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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}