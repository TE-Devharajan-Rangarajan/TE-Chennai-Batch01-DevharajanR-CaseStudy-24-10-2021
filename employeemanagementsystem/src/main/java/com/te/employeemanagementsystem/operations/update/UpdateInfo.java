package com.te.employeemanagementsystem.operations.update;

import java.sql.Date;

import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.NotAValidNumberException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;
import com.te.employeemanagementsystem.home.EntityClass;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.operations.showdetails.FindRecord;
import com.te.employeemanagementsystem.register.Ensure;

public class UpdateInfo extends FindRecord {

	static String ch = null;

	public void updateRecord(LoginInfo loginInfo, EntityClass ec)
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
		
		ch = ec.getSc().next();
		
		if (Ensure.isNumber(ch)) {
			
			selection = Integer.parseInt(ch);
		
		} else {
			
			throw new NotAValidNumberException("Please Enter a number from Selection!!!");
		
		}
		try {
			
			if (selection == 1) {
				
				new UpdatePassword().confirmPassowrdUpdate(loginInfo, ec);
			
			} else {
				
				findRecordFactory(loginInfo, ec);
			
			}

		} catch (InvalidSelectionException | InvalidDataEnteredException | PasswordMismatchException e) {
			
			System.out.println(e.getMessage());
		
		}
	}

	@Override
	public void printResult(LoginInfo loginInfo, EntityClass ec) {

		ec.getEt().begin();
		
		String qry = "update Info set " + columnName + " = :val where id=" + loginInfo.getId();
		Query query = ec.getEm().createQuery(qry);
		
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
		
		
		
		
		
		ec.getEt().commit();
		ec.getEm().clear();
		
		if (res > 0) {
			
			System.out.println("Record updated!!!");
		
		} else {
			
			System.out.println("Invalid Update!!!");
		
		}
		

	}

}
