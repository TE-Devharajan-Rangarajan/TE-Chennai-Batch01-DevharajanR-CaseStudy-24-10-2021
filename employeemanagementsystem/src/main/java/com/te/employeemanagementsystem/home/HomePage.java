package com.te.employeemanagementsystem.home;


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
	
	public static final EntityClass ec = new EntityClass();
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
			System.out.println("|\t\t\t4. Exit\t\t\t\t|");
			System.out.println(CONSTANT);
			
			System.out.println("\nEnter your Choice : ");
			ch = ec.getSc().next();
			
			if (Ensure.isNumber(ch)) {
				
				selection = Integer.parseInt(ch);
				
				try {
					
					switchSelection(ec);
					
				} catch (InvalidSelectionException e) {
					
					System.out.println();
					System.out.println(e.getMessage());
					
				}
			} else {
				
				System.out.println("Please enter a number from the list!!!");
			
			}
		
		}
		
		ec.getSc().close();
		ec.getEm().close();
		ec.getEmf().close();
		
		System.out.println("\nSee you again... Have a nice day!!!");

	}

	public static void switchSelection(EntityClass ec)
			throws InvalidSelectionException {

		switch (selection) {
		
		case 1:
			try {
				Login.login(ec);
			} catch (InvalidCredentialsException | InvalidDataEnteredException e) {
				System.out.println();
				System.out.println(e.getMessage());
			}
			break;
		
		case 2:
			Register.registerNewEmployee(ec);
			break;
		
		case 3:
			ShowDetails.showDetails(null, ec);
			break;
		
		case 4:
			System.out.println("\nSee you again... Have a nice day!!!");
			ec.getEm().close();
			ec.getEmf().close();
			System.exit(0);
			break;
		
		default:
			throw new InvalidSelectionException("Invalid Selection!!!");

		}
	
	}


}
