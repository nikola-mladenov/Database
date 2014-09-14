package geeky.camp.jpa.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Faculty
 *
 */
@Entity

public class Faculty implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	private String facultyName;
	@OneToMany
	private Collection<Student> students;
	
	private static final long serialVersionUID = 1L;

	public Faculty() {
		super();
	}   
	
	
	public Faculty(String id, String facultyName) {
		super();
		this.facultyName = facultyName;
		this.id=id;
	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}   
	public String getFacultyName() {
		return this.facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}


	public Collection<Student> getStudents() {
		return students;
	}


	public void setStudents(Collection<Student> students) {
		this.students = students;
	}


	@Override
	public String toString() {
		return "Faculty [id=" + id + ", facultyName="
				+ facultyName + "]";
	}
	
	
   
}
