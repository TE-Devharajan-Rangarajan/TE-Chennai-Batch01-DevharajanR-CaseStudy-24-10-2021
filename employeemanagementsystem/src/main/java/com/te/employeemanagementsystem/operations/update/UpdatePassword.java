package com.te.employeemanagementsystem.operations.update;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;
import com.te.employeemanagementsystem.login.Login;

public class UpdatePassword {

	public void confirmPassowrdUpdate(LoginInfo loginInfo, EntityManager em, EntityTransaction et, Scanner sc)
			throws InvalidSelectionException, PasswordMismatchException {

		System.out.println("Are you sure that you want to change your password?(Y/N)");
		String c = sc.next();

		if (c.charAt(0) == 'Y' || c.charAt(0) == 'y') {

			updatePassword(loginInfo, em, et, sc);
			System.out.println("\nPassword Changed Successfully!!!");

		} else if (c.charAt(0) == 'N' || c.charAt(0) == 'n') {

			System.out.println("\nYou chose not to change the Password!!!");

		} else {

			throw new InvalidSelectionException("Invalid Selection!!!");

		}

	}

	public void updatePassword(LoginInfo loginInfo, EntityManager em, EntityTransaction et, Scanner sc)
			throws PasswordMismatchException {

		et.begin();
		
		String qry1 = "from LoginInfo where id = " + loginInfo.getId();
		Query query1 = em.createQuery(qry1);

		@SuppressWarnings("unchecked")
		List<LoginInfo> list = query1.getResultList();

		String oldPasswordDb = list.get(0).getPassword();

		System.out.println("\nEnter Old Password : ");
		String oldPassword = sc.next();
		
		if (!oldPassword.equals(oldPasswordDb)) {
			
			throw new PasswordMismatchException("Wrong Password!!!");
		
		}
		
		String qry = "update LoginInfo set password = :val where id = " + loginInfo.getId();
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
		
		String qry2 = "from LoginInfo where id = " + loginInfo.getId();
		Query query2 = em.createQuery(qry2);
		
		@SuppressWarnings("unchecked")
		List<LoginInfo> result = query2.getResultList();
		
		Login.loginInfo.setId(result.get(0).getId());
		Login.loginInfo.setPassword(result.get(0).getPassword());

	}
}
