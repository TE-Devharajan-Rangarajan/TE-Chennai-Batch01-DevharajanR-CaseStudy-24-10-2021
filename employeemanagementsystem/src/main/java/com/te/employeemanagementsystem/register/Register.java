package com.te.employeemanagementsystem.register;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.RegistrationFailedException;
import com.te.employeemanagementsystem.home.HomePage;

public final class Register {

	private Register() {

	}

	public static void registerNewEmployee(EntityManager em, EntityTransaction et, Scanner sc) {
		
		System.out.println("\n" + HomePage.CONSTANT);
		System.out.println("|\t\tRegister Menu\t\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		
		try {
			
			registerInfo(em, et, sc);
		
		} catch (RegistrationFailedException e) {
			
			System.out.println();
			System.out.println(e.getMessage());
		
		}
	
	}

	public static void registerInfo(EntityManager em, EntityTransaction et, Scanner sc)
			throws RegistrationFailedException {

		Info info = Ensure.ensureInfo(em, sc);
		
		if (info != null) {

			et.begin();
			em.persist(info);
			et.commit();
		
			registerLoginInfo(em, et);
		
		} else {
			
			throw new RegistrationFailedException("Registration failed due to incorrect Inputs!!!");
		
		}

		System.out.println("Successfully Registered!!!");
	
	}

	public static void registerLoginInfo(EntityManager em, EntityTransaction et) {

		LoginInfo loginInfo = new LoginInfo();
		
		loginInfo.setId(Ensure.getId());
		loginInfo.setPassword(Ensure.getPassword());
		
		et.begin();
		em.persist(loginInfo);
		et.commit();

	}
}
