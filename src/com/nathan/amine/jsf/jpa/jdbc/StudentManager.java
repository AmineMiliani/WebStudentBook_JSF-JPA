package com.nathan.amine.jsf.jpa.jdbc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;


@ManagedBean (name="StudentManager")
@SessionScoped
public class StudentManager {
	
	private Student student;
	private List<Student> students;
	private StudentDBUtil db;	
	private StudentEntity editStudent;
	public StudentManager() {
		super();
		db = new StudentDBUtil();
		student = new Student();
	}



	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public List<Student> LoadStudents() {
		try {
			students = db.getStudents();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return students;
	}
	
	public String AddStudent() {
		db.addStudent(student);
		student = new Student();
		return "list-students";
		
	}
	
	public String LoadStudents(int id) {
		editStudent = db.fetchStudent(id);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("student", editStudent);
		return"edit-student";
		
	}
	
	public String UpdateStudent() {
		db.updateStudent(editStudent);
		editStudent = new StudentEntity();
		return"list-students";
	}
	
	public String DeleteStudent(int id) {
		db.deleteStudent(id);
		return "list-students";
		
	}

	public Student getStudent() {
		return student;
	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public StudentEntity getEditStudent() {
		return editStudent;
	}



	public void setEditStudent(StudentEntity editStudent) {
		this.editStudent = editStudent;
	}



}
