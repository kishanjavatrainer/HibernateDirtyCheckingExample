package com.infotech.client;

import org.hibernate.Session;

import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class DirtyCheckingClientTest {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Employee employee = session.get(Employee.class, 1);
			if(employee != null){
				session.beginTransaction();
				employee.setSalary(40000.00);
				employee.setEmployeeName("Martin Bingel2");
				session.update(employee);
				session.getTransaction().commit();
				//employee.setEmployeeName("Martin Bingel2");
			}else{
				System.out.println("Employeedoesn't exist with provided Id..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null){
				session.close();
			}
		}
	  }
}
