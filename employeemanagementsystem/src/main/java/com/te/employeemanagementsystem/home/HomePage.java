package com.te.employeemanagementsystem.home;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.employeemanagementsystem.exceptions.InvalidCredentialsException;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.login.Login;
import com.te.employeemanagementsystem.operations.showdetails.ShowDetails;
import com.te.employeemanagementsystem.register.Ensure;
import com.te.employeemanagementsystem.register.Register;

public class HomePage {

	private HomePage() {
	}

	static Scanner sc = new Scanner(System.in);
	static int selection = 0;
	static String ch = null;
	static int flag = 0;
	public static final String CONSTANT = "+-------------------------------------------------------+";

	public static void homePage() {

		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		while (flag != 1) {
			
			System.out.println();
			System.out.println(CONSTANT);
			System.out.println("|\t\t\tHome Page\t\t\t|");
			System.out.println(CONSTANT);
			System.out.println("|\t\t\t1. Login\t\t\t|");
			System.out.println("|\t\t\t2. Register\t\t\t|");
			System.out.println("|\t\t\t3. Guest\t\t\t|");
			System.out.println("|\t\t\t4. Exit\t\t\t\t|");
			System.out.println(CONSTANT);
			
			System.out.println("\nEnter your Choice : ");
			ch = sc.next();
			
			if (Ensure.isNumber(ch)) {
				
				selection = Integer.parseInt(ch);
				
				try {
					
					switchSelection(emf, em, et);
					
				} catch (InvalidSelectionException e) {
					
					System.out.println();
					System.out.println(e.getMessage());
					
				}
			} else {
				
				System.out.println("Please enter a number from the list!!!");
			
			}
		
		}
		
		sc.close();
		em.close();
		emf.close();
		
		System.out.println("\nSee you again... Have a nice day!!!");

	}

	public static void switchSelection(EntityManagerFactory emf, EntityManager em, EntityTransaction et)
			throws InvalidSelectionException {

		switch (selection) {
		
		case 1:
			try {
				Login.login(em, et, sc);
			} catch (InvalidCredentialsException | InvalidDataEnteredException e) {
				System.out.println();
				System.out.println(e.getMessage());
			}
			break;
		
		case 2:
			Register.registerNewEmployee(em, et, sc);
			break;
		
		case 3:
			ShowDetails.showDetails(null, em, et, sc);
			break;
		
		case 4:
			System.out.println("\nSee you again... Have a nice day!!!");
			em.close();
			emf.close();
			System.exit(0);
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
