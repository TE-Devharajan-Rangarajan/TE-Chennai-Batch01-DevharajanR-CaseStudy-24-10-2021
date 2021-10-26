package com.te.employeemanagementsystem.operations.update;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.NotAValidNumberException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.login.Login;
import com.te.employeemanagementsystem.operations.showdetails.FindRecord;
import com.te.employeemanagementsystem.register.Ensure;

public class UpdateInfo extends FindRecord {

	static String ch = null;

	public void updateRecord(LoginInfo loginInfo, EntityManager em, EntityTransaction et, Scanner sc)
			throws NotAValidNumberException {

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

		System.out.println("\nEnter your choice : ");
		
		ch = sc.next();
		
		if (Ensure.isNumber(ch)) {
			
			selection = Integer.parseInt(ch);
		
		} else {
			
			throw new NotAValidNumberException("Please Enter a number from Selection!!!");
		
		}
		try {
			
			if (selection == 1) {
				
				new UpdatePassword().confirmPassowrdUpdate(loginInfo, em, et, sc);
			
			} else {
				
				findRecordFactory(loginInfo, em, et, sc);
			
			}

		} catch (InvalidSelectionException | InvalidDataEnteredException | PasswordMismatchException e) {
			
			System.out.println(e.getMessage());
		
		}
	}

	@Override
	public void printResult(LoginInfo loginInfo, EntityManager em, EntityTransaction et) {

		et.begin();
		
		String qry = "update Info set " + columnName + " = :val where id=" + loginInfo.getId();
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
		
		et.commit();
		
		String qry1 = "from LoginInfo where id = " + loginInfo.getId();
		Query query1 = em.createQuery(qry1);
		
		@SuppressWarnings("unchecked")
		List<LoginInfo> result = query1.getResultList();
		
		Login.loginInfo.setId(result.get(0).getId());
		Login.loginInfo.setPassword(result.get(0).getPassword());
		
		if (res > 0) {
			
			System.out.println("Record updated!!!");
		
		} else {
			
			System.out.println("Invalid Update!!!");
		
		}
		

	}

}
