package com.te.employeemanagementsystem.operations.showdetails;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.operations.PrintTable;
import com.te.employeemanagementsystem.register.Ensure;

public class FindRecord {

	public static String columnName = null;
	public static String value = null;
	public static int selection=0;
	public static String[] selectionName = { "0", "id", "firstName", "lastName", "dob", "gender", "salary", "role", "mobile",
			"email", "bloodGroup" };
		
	public void findRecord(Scanner sc) {
		
		System.out.println("+-------------------------------------------------------+");
		System.out.println("|\tChoose the filter for your search!!!\t\t|");
		System.out.println("+-------------------------------------------------------+");
		System.out.println("|\t\t1. Id\t\t\t\t\t|");
		System.out.println("|\t\t2. First Name\t\t\t\t|");
		System.out.println("|\t\t3. Last Name\t\t\t\t|");
		System.out.println("|\t\t4. Date Of Birth\t\t\t|");
		System.out.println("|\t\t5. Gender\t\t\t\t|");
		System.out.println("|\t\t6. Salary\t\t\t\t|");
		System.out.println("|\t\t7. Role\t\t\t\t\t|");
		System.out.println("|\t\t8. Mobile Number\t\t\t|");
		System.out.println("|\t\t9. Email Address\t\t\t|");
		System.out.println("|\t\t10. Blood Group\t\t\t\t|");
		System.out.println("+-------------------------------------------------------+");
		
		selection = Integer.parseInt(sc.next());
		try {
			findRecordFactory(sc);
		} catch (InvalidSelectionException | InvalidDataEnteredException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void findRecordFactory(Scanner sc) throws InvalidSelectionException, InvalidDataEnteredException {
			
		if(checkSelection()) {
			
			columnName = selectionName[selection];
		}else {
			throw new InvalidSelectionException("Invalid Selection!!!!");
		}

		System.out.println("Enter the value:");
		value = sc.next();
		if( ! checkValue()) {
			sc.close();
			throw new InvalidDataEnteredException("Invalid Value!!!!");
		}
		printResult();
	}
	
	public boolean checkSelection() {
		
		return !(selection<=0 || selection>10);
	}
	
	public boolean checkValue() {
		if(selection == 1) {
			try {
				Ensure.ensureId(value);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else if(selection == 4) {
			try {
				Ensure.ensureDate(value);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else if(selection == 6) {
			try {
				Ensure.ensureSalary(value);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else if(selection == 8) {
			try {
				Ensure.ensureMobile(value);
				return true;
			} catch (Exception e) {
				return false;
			}
		}else {
			return true;
		}
	}
	
	public void printResult() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();		
		String qry = "from Info where " + columnName + " = :val";
		Query query = em.createQuery(qry);
		switch(selection) {
		case 1:
			query.setParameter("val", Integer.parseInt(value));
			break;
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
		
		List<Info> list = query.getResultList();
		if(!list.isEmpty()) {
			PrintTable.printTable(list);			
		}else {
			System.out.println("No Matching Records!!!");
		}
		em.close();
		emf.close();
	}

}
