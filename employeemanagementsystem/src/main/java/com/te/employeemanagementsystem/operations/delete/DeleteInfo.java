package com.te.employeemanagementsystem.operations.delete;

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

public class DeleteInfo {
	
	private DeleteInfo() {}
	
	static int id;
	
	public static void confirmDelete(LoginInfo loginInfo, Scanner sc) throws InvalidSelectionException {
		
		System.out.println("WARNING :: Please note deleting makes you lose access to the application!!!");
		System.out.println("\nAre you sure you want to delete your information? (Y/N) ");
		id = loginInfo.getId();
		String c = sc.next();
		if(c.charAt(0)=='Y' || c.charAt(0)=='y') {
			deleteRecord();	
			deleteLoginRecord();
			System.out.println("Deletion Complete!!!");
		}else if(c.charAt(0)=='N' || c.charAt(0)=='n'){
			System.out.println("You chose not to delete!!!");
		}else {
			throw new InvalidSelectionException("Invalid Selection!!!");
		}
	}
	
	public static void deleteRecord() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String qry = "delete from Info where id="+ id;
		Query query = em.createQuery(qry);
		query.executeUpdate();
		et.commit();
		em.close();
		emf.close();
	}
	
	public static void deleteLoginRecord() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		String qry = "delete from LoginInfo where id="+ id;
		Query query = em.createQuery(qry);
		query.executeUpdate();
		et.commit();
		em.close();
		emf.close();
	}
	
}
