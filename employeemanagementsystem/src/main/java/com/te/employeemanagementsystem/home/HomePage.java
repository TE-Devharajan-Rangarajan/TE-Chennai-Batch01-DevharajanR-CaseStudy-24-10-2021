package com.te.employeemanagementsystem.home;

import java.util.Scanner;

import com.te.employeemanagementsystem.exceptions.InvalidCredentialsException;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.login.Login;
import com.te.employeemanagementsystem.operations.showdetails.ShowDetails;import com.te.employeemanagementsystem.register.Ensure;
import com.te.employeemanagementsystem.register.Register;

public class HomePage {
	
	private HomePage() {}

	static Scanner sc = new Scanner(System.in);
	static int selection = 0;
	static String ch = null;
	static int flag = 0;
	public static final String CONSTANT = "+-------------------------------------------------------+";

	public static void homePage() {

		while (flag != 1) {
			System.out.println();
			System.out.println(CONSTANT);
			System.out.println("|\t\t\tHome Page\t\t\t|");
			System.out.println(CONSTANT);
			System.out.println("|\t\t\t1. Login\t\t\t|");
			System.out.println("|\t\t\t2. Register\t\t\t|");
			System.out.println("|\t\t\t3. Guest\t\t\t|");
			System.out.println(CONSTANT);
			System.out.println("\nEnter your Choice : ");
			ch = sc.next();
			if(Ensure.isNumber(ch)) {
				selection = Integer.parseInt(ch);
				try {
					switchSelection(selection);
					continueAgain();
				} catch (InvalidSelectionException e) {
					System.out.println();
					System.out.println(e.getMessage());
				}
			}else {
				System.out.println("Please enter a number from the list!!!");
			}
		}
		sc.close();
		System.out.println("\nSee you again... Have a nice day!!!");

	}

	public static void switchSelection(int selection) throws InvalidSelectionException {

		switch (selection) {
		case 1:
			try {
				Login.login(sc);
			} catch (InvalidCredentialsException e) {
				System.out.println();
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			Register.registerNewEmployee(sc);
			break;
		case 3:
			ShowDetails.showDetails(sc);
			break;
		default:
			throw new InvalidSelectionException("Invalid Selection!!!");

		}
	}

	public static void continueAgain() throws InvalidSelectionException {
		System.out.println("\nDo you wish to continue? (Y/N)");
		ch = sc.next();
		if (ch.charAt(0) == 'N' || ch.charAt(0) == 'n') {
			flag = 1;
		} else if (ch.charAt(0) == 'Y' || ch.charAt(0) == 'y') {

		} else {
			throw new InvalidSelectionException("Invalid Selection!!!");
		}
	}

}
