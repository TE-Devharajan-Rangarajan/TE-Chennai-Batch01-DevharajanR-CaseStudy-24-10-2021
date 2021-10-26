package com.te.employeemanagementsystem.operations.delete;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;

public class DeleteInfo {

	private DeleteInfo() {
	}


	public static void confirmDelete(LoginInfo loginInfo, EntityManager em, EntityTransaction et, Scanner sc)
			throws InvalidSelectionException {

		System.out.println("WARNING :: Please note deleting makes you lose access to the application!!!");

		System.out.println("\nAre you sure you want to delete your information? (Y/N) ");

		String c = sc.next();

		if (c.charAt(0) == 'Y' || c.charAt(0) == 'y') {

			deleteRecord(loginInfo, em, et);

			deleteLoginRecord(loginInfo, em, et);

			System.out.println("Deletion Complete!!!");

		} else if (c.charAt(0) == 'N' || c.charAt(0) == 'n') {

			System.out.println("You chose not to delete!!!");

		} else {

			throw new InvalidSelectionException("Invalid Selection!!!");

		}
	}

	public static void deleteRecord(LoginInfo loginInfo, EntityManager em, EntityTransaction et) {

		et.begin();
		String qry = "delete from Info where id=" + loginInfo.getId();
		Query query = em.createQuery(qry);
		query.executeUpdate();
		et.commit();

	}

	public static void deleteLoginRecord(LoginInfo loginInfo, EntityManager em, EntityTransaction et) {

		et.begin();
		String qry = "delete from LoginInfo where id=" + loginInfo.getId();
		Query query = em.createQuery(qry);
		query.executeUpdate();
		et.commit();

	}

}
