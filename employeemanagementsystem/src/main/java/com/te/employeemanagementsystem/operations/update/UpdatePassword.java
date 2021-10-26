package com.te.employeemanagementsystem.operations.update;

import java.util.List;

import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;
import com.te.employeemanagementsystem.home.EntityClass;

public class UpdatePassword {

	public void confirmPassowrdUpdate(LoginInfo loginInfo, EntityClass ec)
			throws InvalidSelectionException, PasswordMismatchException {

		System.out.println("Are you sure that you want to change your password?(Y/N)");
		String c = ec.getSc().next();

		if (c.charAt(0) == 'Y' || c.charAt(0) == 'y') {

			updatePassword(loginInfo, ec);
			System.out.println("\nPassword Changed Successfully!!!");

		} else if (c.charAt(0) == 'N' || c.charAt(0) == 'n') {

			System.out.println("\nYou chose not to change the Password!!!");

		} else {

			throw new InvalidSelectionException("Invalid Selection!!!");

		}

	}

	public void updatePassword(LoginInfo loginInfo, EntityClass ec)
			throws PasswordMismatchException {

		ec.getEt().begin();
		
		String qry1 = "from LoginInfo where id = " + loginInfo.getId();
		Query query1 = ec.getEm().createQuery(qry1);

		@SuppressWarnings("unchecked")
		List<LoginInfo> list = query1.getResultList();

		String oldPasswordDb = list.get(0).getPassword();

		System.out.println("\nEnter Old Password : ");
		String oldPassword = ec.getSc().next();
		
		if (!oldPassword.equals(oldPasswordDb)) {
			
			throw new PasswordMismatchException("Wrong Password!!!");
		
		}
		
		String qry = "update LoginInfo set password = :val where id = " + loginInfo.getId();
		Query query = ec.getEm().createQuery(qry);
		
		System.out.println("\nEnter New Password : ");
		String pass = ec.getSc().next();
		
		System.out.println("\nRe-Enter New Password : ");
		String pass1 = ec.getSc().next();
		
		System.out.println(pass1);
		
		if (pass.equals(pass1)) {
			
			query.setParameter("val", pass);
			query.executeUpdate();

		} else {
			
			throw new PasswordMismatchException("Password Mismatch Error!!!");
		
		}
		
		ec.getEt().commit();
		ec.getEm().clear();
		
		
		
		/**
		 * Below Lies my failed attempts to refresh entities with updated data from database
		 * before figuring out that clearing the entityManager will do the job
		 */
		
		/*
		 * for (EntityType<?> entity : ec.getEm().getMetamodel().getEntities()) { try {
		 * ec.getEm().refresh(entity); System.out.println("Success!!!"); } catch
		 * (IllegalArgumentException e) { System.out.println("failed"); } }
		 */
        		
		/*
		 * String qry2 = "from LoginInfo where id = " + loginInfo.getId(); Query query2
		 * = ec.getEm().createQuery(qry2);
		 * 
		 * @SuppressWarnings("unchecked") List<LoginInfo> result =
		 * query2.getResultList();
		 * 
		 * Login.loginInfo.setId(result.get(0).getId());
		 * Login.loginInfo.setPassword(result.get(0).getPassword());
		 * 
		 * HomePage.ec.setSc(ec.getSc()); HomePage.ec.setEt(ec.getEt());
		 * HomePage.ec.setEm(ec.getEm()); HomePage.ec.setEmf(ec.getEmf());
		 */

	}
}
