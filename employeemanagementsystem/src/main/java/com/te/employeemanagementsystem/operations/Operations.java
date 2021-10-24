package com.te.employeemanagementsystem.operations;

import java.util.Scanner;


import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.operations.delete.DeleteInfo;
import com.te.employeemanagementsystem.operations.showdetails.ShowDetails;
import com.te.employeemanagementsystem.operations.update.UpdateInfo;

public class Operations {
	static String ch = null;
	static int flagDelete = 0;
	static int flag = 0;

	public static void operationsLoop(LoginInfo loginInfo, Scanner sc) {
		flag = 0;
		flagDelete = 0;
		while (flag != 1) {
			try {
				operations(loginInfo, sc);
				if(flagDelete == 1) {
					flag = 1;
					continue;
				}
				System.out.println("\nDo you wish to perform another operation? (Y/N)");
				ch = sc.next();
				if (ch.charAt(0) == 'N' || ch.charAt(0) == 'n') {
					flag = 1;
				} else if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {

				} else {
					throw new InvalidSelectionException("Invalid Selection!!!");
				}
			} catch (InvalidSelectionException e) {
				System.out.println();
				System.out.println(e.getMessage());
			}

		}

	}

	public static void operations(LoginInfo loginInfo, Scanner sc) throws InvalidSelectionException {
		System.out.println();
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t\tOperations Page\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t1. Search employee information\t\t|");
		System.out.println("|\t\t2. Update your information\t\t|");
		System.out.println("|\t\t3. Delete your information\t\t|");
		System.out.println(HomePage.CONSTANT);
		
		System.out.println("\nEnter your Choice : ");

		int selection = Integer.parseInt(sc.next());
		switch (selection) {
		case 1:
			ShowDetails.showDetails(sc);
			break;
		case 2:
			new UpdateInfo().updateRecord(loginInfo, sc);
			break;
		case 3:
			DeleteInfo.confirmDelete(loginInfo, sc);
			flagDelete = 1;
			break;
		default:
			throw new InvalidSelectionException("Invalid Selection!!!");
		}
	}
}
