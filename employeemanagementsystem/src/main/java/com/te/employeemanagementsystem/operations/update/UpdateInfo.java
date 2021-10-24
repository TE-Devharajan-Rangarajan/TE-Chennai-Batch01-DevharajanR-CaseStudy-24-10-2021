package com.te.employeemanagementsystem.operations.update;

import java.sql.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.operations.showdetails.FindRecord;

public class UpdateInfo extends FindRecord {

	public static int id;
	

	public void updateRecord(LoginInfo loginInfo, Scanner sc) {
		
		id = loginInfo.getId();
		
		
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\tChoose data you wish to change!!!\t|");
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t1. Password\t\t\t\t|");
		System.out.println("|\t\t2. First Name\t\t\t\t|");
		System.out.println("|\t\t3. Last Name\t\t\t\t|");
		System.out.println("|\t\t4. Date Of Birth\t\t\t|");
		System.out.println("|\t\t5. Gender\t\t\t\t|");
		System.out.println("|\t\t6. Salary\t\t\t\t|");
		System.out.println("|\t\t7. Role\t\t\t\t\t|");
		System.out.println("|\t\t8. Mobile Number\t\t\t|");
		System.out.println("|\t\t9. Email Address\t\t\t|");
		System.out.println("|\t\t10. Blood Group\t\t\t\t|");
		System.out.println(HomePage.CONSTANT);

		selection = Integer.parseInt(sc.next());
		try {
			if (selection == 1) {
				new UpdatePassword().confirmPassowrdUpdate(id, sc);
			} else {
				findRecordFactory(sc);
			}

		} catch (InvalidSelectionException | InvalidDataEnteredException | PasswordMismatchException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void printResult() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String qry = "update Info set " + columnName + " = :val where id=" + id;
		Query query = em.createQuery(qry);
		switch (selection) {
		case 4:
			query.setParameter("val", Date.valueOf(value));
			break;
		case 6:
			query.setParameter("val", Double.parseDouble(value));
			break;
		case 8:
			query.setParameter("val", Long.parseLong(value));
			break;
		default:
			query.setParameter("val", value);
			break;
		}

		int res = query.executeUpdate();
		if (res > 0) {
			System.out.println("Record updated!!!");
		} else {
			System.out.println("Invalid Update!!!");
		}
		et.commit();
		em.close();
		emf.close();
	}

	

}
