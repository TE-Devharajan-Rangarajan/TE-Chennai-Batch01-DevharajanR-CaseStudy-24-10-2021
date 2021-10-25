package com.te.employeemanagementsystem.login;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidCredentialsException;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.operations.Operations;
import com.te.employeemanagementsystem.register.Ensure;

public class Login {	
	
	private Login() {}
	
	static String data = null;
	
	public static void login(Scanner sc) throws InvalidCredentialsException, InvalidDataEnteredException {
		LoginInfo loginInfo = new LoginInfo();
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t\tLogin Page\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		System.out.println("\nEnter your ID : ");
		data = sc.next();
		if(Ensure.isNumber(data)) {
			
			loginInfo.setId(Integer.parseInt(data));
		}else {
			throw new InvalidDataEnteredException("Not an Employee Number!!!");
		}
		System.out.println("\nEnter your Password : ");
		loginInfo.setPassword(sc.next());
		if (checkLogin(loginInfo)) {
			System.out.println("\nLogin Successful!!!\n");
			Operations.operationsLoop(loginInfo, sc);
		} else {
			throw new InvalidCredentialsException("Wrong Credentials!!!");
		}
	}

	public static boolean checkLogin(LoginInfo loginInfo) {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from LoginInfo");
		List<LoginInfo> list = query.getResultList();
		em.close();
		emf.close();
		return list.contains(loginInfo);
	}
}
