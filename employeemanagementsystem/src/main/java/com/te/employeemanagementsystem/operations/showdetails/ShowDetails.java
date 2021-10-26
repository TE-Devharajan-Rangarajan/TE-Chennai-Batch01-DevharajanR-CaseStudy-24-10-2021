package com.te.employeemanagementsystem.operations.showdetails;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.register.Ensure;

public class ShowDetails {

	private ShowDetails() {
	}

	static int selection;
	static String ch = null;

	public static void showDetails(LoginInfo loginInfo, EntityManager em, EntityTransaction et, Scanner sc) {

		System.out.println();
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t\tShow Details Menu\t\t|");
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t1. Display All Records\t\t\t|");
		System.out.println("|\t\t2. Filter Record(s)\t\t\t|");
		System.out.println(HomePage.CONSTANT);

		System.out.println("\nEnter your choice : ");

		ch = sc.next();

		if (Ensure.isNumber(ch)) {

			selection = Integer.parseInt(ch);
		}
		
		if (selection == 1) {
			
			DisplayAllRecords.displayAllRecords(em);
		
		} else if (selection == 2) {
			
			new FindRecord().findRecord(loginInfo, em, et, sc);
		
		} else {

			System.out.println("Invalid Selection!!!");
		
		}

	}

}