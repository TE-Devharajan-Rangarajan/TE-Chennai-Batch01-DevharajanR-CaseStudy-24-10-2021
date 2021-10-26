package com.te.employeemanagementsystem.operations;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.NotAValidNumberException;
import com.te.employeemanagementsystem.home.EntityClass;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.operations.delete.DeleteInfo;
import com.te.employeemanagementsystem.operations.showdetails.ShowDetails;
import com.te.employeemanagementsystem.operations.update.UpdateInfo;
import com.te.employeemanagementsystem.register.Ensure;

public class Operations {

	private Operations() {

	}

	static String ch = null;
	static int flagDelete = 0;
	static int flag = 0;
	static int selection = 0;

	public static void operationsLoop(LoginInfo loginInfo, EntityClass ec) {
		
		flag = 0;
		flagDelete = 0;
		
		while (flag != 1) {
			
			try {
				
				operations(loginInfo, ec);
				
				if (flagDelete == 1) {
					
					flag = 1;
					continue;
				
				}
				
				System.out.println("\nDo you wish to perform another operation? (Y/N)");
				
				ch = ec.getSc().next();
				
				if (ch.charAt(0) == 'N' || ch.charAt(0) == 'n') {
					
					flag = 1;
				
				} else if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {

				} else {
					
					throw new InvalidSelectionException("Invalid Selection!!!");
				
				}
			} catch (InvalidSelectionException | NotAValidNumberException e) {
				
				System.out.println();
				System.out.println(e.getMessage());
			
			}

		}

	}

	public static void operations(LoginInfo loginInfo, EntityClass ec)
			throws InvalidSelectionException, NotAValidNumberException {
		
		System.out.println();
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t\tOperations Page\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t1. Search employee information\t\t|");
		System.out.println("|\t\t2. Update your information\t\t|");
		System.out.println("|\t\t3. Delete your information\t\t|");
		System.out.println("|\t\t4. Exit\t\t\t\t\t|");
		System.out.println(HomePage.CONSTANT);

		System.out.println("\nEnter your Choice : ");
		ch = ec.getSc().next();
		
		if (Ensure.isNumber(ch)) {
			
			selection = Integer.parseInt(ch);

		} else {
			
			throw new NotAValidNumberException("Not a valid Number");
		
		}
		
		switch (selection) {
		
		case 1:
			ShowDetails.showDetails(loginInfo, ec);
			break;
		
		case 2:
			new UpdateInfo().updateRecord(loginInfo, ec);
			break;
		
		case 3:
			DeleteInfo.confirmDelete(loginInfo, ec);
			flagDelete = 1;
			break;
		
		case 4:
			System.out.println("\nSee you again... Have a nice day!!!");
			System.exit(0);
			break;
		
		default:
			throw new InvalidSelectionException("Invalid Selection!!!");
		}
	}
}
