package com.te.employeemanagementsystem.login;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidCredentialsException;
import com.te.employeemanagementsystem.exceptions.InvalidDataEnteredException;
import com.te.employeemanagementsystem.home.HomePage;
import com.te.employeemanagementsystem.operations.Operations;
import com.te.employeemanagementsystem.register.Ensure;

public class Login {

	private Login() {
	}

	static String data = null;
	public static LoginInfo loginInfo = new LoginInfo();

	public static void login(EntityManager em, EntityTransaction et, Scanner sc)
			throws InvalidCredentialsException, InvalidDataEnteredException {
		
		
		
		System.out.println(HomePage.CONSTANT);
		System.out.println("|\t\t\tLogin Page\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		
		System.out.println("\nEnter your ID : ");
		data = sc.next();
		
		if (Ensure.isNumber(data)) {

			loginInfo.setId(Integer.parseInt(data));
		
		} else {
			
			throw new InvalidDataEnteredException("Not an Employee Number!!!");
		
		}
		
		System.out.println("\nEnter your Password : ");
		loginInfo.setPassword(sc.next());
		
		if (checkLogin(loginInfo, em)) {
			
			System.out.println("\nLogin Successful!!!\n");
			
			Operations.operationsLoop(loginInfo, em, et, sc);
		
		} else {
			
			throw new InvalidCredentialsException("Wrong Credentials!!!");
		}
	}

	public static boolean checkLogin(LoginInfo loginInfo, EntityManager em) {

		Query query = em.createQuery("from LoginInfo");
		@SuppressWarnings("unchecked")
		List<LoginInfo> list = query.getResultList();

		return list.contains(loginInfo);
	}
}
