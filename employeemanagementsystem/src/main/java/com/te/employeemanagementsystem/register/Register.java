package com.te.employeemanagementsystem.register;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.RegistrationFailedException;
import com.te.employeemanagementsystem.home.EntityClass;
import com.te.employeemanagementsystem.home.HomePage;

public final class Register {

	private Register() {

	}

	public static void registerNewEmployee(EntityClass ec) {
		
		System.out.println("\n" + HomePage.CONSTANT);
		System.out.println("|\t\tRegister Menu\t\t\t\t|");
		System.out.println(HomePage.CONSTANT);
		
		try {
			
			registerInfo(ec);
		
		} catch (RegistrationFailedException e) {
			
			System.out.println();
			System.out.println(e.getMessage());
		
		}
	
	}

	public static void registerInfo(EntityClass ec)
			throws RegistrationFailedException {

		Info info = Ensure.ensureInfo(ec);
		
		if (info != null) {

			ec.getEt().begin();
			ec.getEm().persist(info);
			ec.getEt().commit();
		
			registerLoginInfo(ec);
		
		} else {
			
			throw new RegistrationFailedException("Registration failed due to incorrect Inputs!!!");
		
		}

		System.out.println("Successfully Registered!!!");
	
	}

	public static void registerLoginInfo(EntityClass ec) {

		LoginInfo loginInfo = new LoginInfo();
		
		loginInfo.setId(Ensure.getId());
		loginInfo.setPassword(Ensure.getPassword());
		
		ec.getEt().begin();
		ec.getEm().persist(loginInfo);
		ec.getEt().commit();

	}
}
