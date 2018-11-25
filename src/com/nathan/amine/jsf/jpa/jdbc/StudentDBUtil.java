package com.nathan.amine.jsf.jpa.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.DataSource;

public class StudentDBUtil {
	private static final String PERSIS_NAME = "JSFJPA";
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSIS_NAME);
	private static EntityManager em = factory.createEntityManager();
	private static EntityTransaction et = em.getTransaction();

	@SuppressWarnings("unchecked")
	public static List<Student> getStudents() {
		if(!et.isActive()) {
			et.begin();
		}
		List<Student> students = em.createQuery("SELECT s FROM StudentEntity s order by s.id").getResultList();
		return students;
	}
	public static StudentEntity fetchStudent(int id) {
		Query query = em.createQuery("SELECT s FROM StudentEntity s WHERE s.id = :id");
		query.setParameter("id", id);
		StudentEntity student = (StudentEntity) query.getSingleResult();
		return student;
	}

	public static void updateStudent(StudentEntity student) {
		em.merge(student);
		et.commit();
	}
	public static void addStudent(Student student) {
		StudentEntity studententity = new StudentEntity();
		studententity.setFirst_name(student.getFirst_name());
		studententity.setLast_name(student.getLast_name());
		studententity.setEmail(student.getEmail());
		em.persist(studententity);
		et.commit();
	}
	public static void deleteStudent(int id) {
		StudentEntity student = new StudentEntity();
		student.setId(id);
		em.remove(em.merge(student));
		et.commit();
	}


}

