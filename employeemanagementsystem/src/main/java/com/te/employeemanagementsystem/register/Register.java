package com.te.employeemanagementsystem.register;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.RegistrationFailedException;
import com.te.employeemanagementsystem.home.HomePage;

public final class Register {
	
	public static void registerNewEmployee(Scanner sc) {
		System.out.println("\n"+ HomePage.CONSTANT);
		System.out.println("|\t\tRegister Menu\t\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		try {
			registerInfo(sc);
		} catch (RegistrationFailedException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void registerInfo(Scanner sc) throws RegistrationFailedException {

		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Info info = Ensure.ensureInfo(sc);
		if (info != null) {

			et.begin();
			em.persist(info);
			et.commit();
			registerLoginInfo();
		} else {
			throw new RegistrationFailedException("Registration failed due to incorrect Inputs!!!");
		}

		System.out.println("Successfully Registered!!!");

		em.close();
		emf.close();
	}

	public static void registerLoginInfo() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		LoginInfo loginInfo = new LoginInfo();
		loginInfo.setId(Ensure.getId());
		loginInfo.setPassword(Ensure.getPassword());
		et.begin();
		em.persist(loginInfo);
		et.commit();
		em.close();
		emf.close();
	}
}
