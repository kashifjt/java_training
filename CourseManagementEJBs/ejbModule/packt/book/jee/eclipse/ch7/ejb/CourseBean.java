package packt.book.jee.eclipse.ch7.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import packt.book.jee.eclipse.ch7.dto.CourseDTO;
import packt.book.jee.eclipse.ch7.jpa.Course;

/**
 * Session Bean implementation class CourseBean
 */
@Stateless
@LocalBean
public class CourseBean implements CourseBeanRemote {

	@PersistenceContext
	EntityManager entityManager;
    /**
     * Default constructor. 
     */
    public CourseBean() {
        // TODO Auto-generated constructor stub
    }
    
    public List<Course> getCourseEntities()
    {
    	TypedQuery<Course> courseQuery = entityManager.createNamedQuery("Course.findAll", Course.class);
    	return courseQuery.getResultList();
    }

	@Override
	public List<CourseDTO> getCourses() {
		List<Course> courseEntities = getCourseEntities();
		List<CourseDTO> courses = new ArrayList<CourseDTO>();
		for (Course courseEntity:courseEntities)
		{
			CourseDTO course = new CourseDTO();
			course.setId(courseEntity.getId());
			course.setName(courseEntity.getName());
			course.setCredits(courseEntity.getCredits());
			courses.add(course);
		}
		return courses;
	}

}
