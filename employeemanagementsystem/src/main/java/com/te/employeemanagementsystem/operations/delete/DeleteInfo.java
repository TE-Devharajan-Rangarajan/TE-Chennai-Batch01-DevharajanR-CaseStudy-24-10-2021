package com.te.employeemanagementsystem.operations.delete;

import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.InvalidSelectionException;
import com.te.employeemanagementsystem.home.EntityClass;

public class DeleteInfo {

	private DeleteInfo() {
	}


	public static void confirmDelete(LoginInfo loginInfo, EntityClass ec)
			throws InvalidSelectionException {

		System.out.println("WARNING :: Please note deleting makes you lose access to the application!!!");

		System.out.println("\nAre you sure you want to delete your information? (Y/N) ");

		String c = ec.getSc().next();

		if (c.charAt(0) == 'Y' || c.charAt(0) == 'y') {

			deleteRecord(loginInfo, ec);

			deleteLoginRecord(loginInfo, ec);

			System.out.println("Deletion Complete!!!");

		} else if (c.charAt(0) == 'N' || c.charAt(0) == 'n') {

			System.out.println("You chose not to delete!!!");

		} else {

			throw new InvalidSelectionException("Invalid Selection!!!");

		}
	}

	public static void deleteRecord(LoginInfo loginInfo, EntityClass ec) {

		ec.getEt().begin();
		String qry = "delete from Info where id=" + loginInfo.getId();
		Query query = ec.getEm().createQuery(qry);
		query.executeUpdate();
		ec.getEt().commit();

	}

	public static void deleteLoginRecord(LoginInfo loginInfo, EntityClass ec) {

		ec.getEt().begin();
		String qry = "delete from LoginInfo where id=" + loginInfo.getId();
		Query query = ec.getEm().createQuery(qry);
		query.executeUpdate();
		ec.getEt().commit();

	}

}
