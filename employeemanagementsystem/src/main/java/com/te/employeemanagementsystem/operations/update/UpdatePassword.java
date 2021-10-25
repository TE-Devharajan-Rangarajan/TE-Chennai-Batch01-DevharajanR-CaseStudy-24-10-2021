package com.te.employeemanagementsystem.operations.update;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;

public class UpdatePassword{
	
	
	public void confirmPassowrdUpdate(int id, Scanner sc) throws InvalidSelectionException, PasswordMismatchException {
		System.out.println("Are you sure that you want to change your password?(Y/N)");
		String c = sc.next();
		if (c.charAt(0) == 'Y' || c.charAt(0) == 'y') {
			updatePassword(id, sc);
			System.out.println("\nPassword Changed Successfully!!!");
		} else if (c.charAt(0) == 'N' || c.charAt(0) == 'n') {
			System.out.println("\nYou chose not to change the Password!!!");
		} else {
			throw new InvalidSelectionException("Invalid Selection!!!");
		}

	}

	public void updatePassword(int id, Scanner sc) throws PasswordMismatchException {
		
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String qry1 = "from LoginInfo where id = " + id;
		Query query1 = em.createQuery(qry1);
		
		List<LoginInfo> list = query1.getResultList();
		
		String oldPasswordDb = list.get(0).getPassword();
		
		System.out.println("\nEnter Old Password : ");
		String oldPassword = sc.next();
		if( ! oldPassword.equals(oldPasswordDb)) {
			throw new PasswordMismatchException("Wrong Password!!!");
		}
		String qry = "update LoginInfo set password = :val where id = " + id;
		Query query = em.createQuery(qry);
		System.out.println("\nEnter New Password : ");
		String pass = sc.next();
		System.out.println("\nRe-Enter New Password : ");
		String pass1 = sc.next();
		System.out.println(pass1);
		if (pass.equals(pass1)) {
			query.setParameter("val", pass);
			query.executeUpdate();

		} else {
			throw new PasswordMismatchException("Password Mismatch Error!!!");
		}
		et.commit();
		em.close();
		emf.close();
	}
}
