package com.te.employeemanagementsystem.operations.showdetails;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.operations.PrintTable;

public class DisplayAllRecords {
	private DisplayAllRecords() {}
	
	public static void displayAllRecords() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("logininfo");
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("from Info");
		List<Info> list = query.getResultList();
		if(!list.isEmpty()) {
			PrintTable.printTable(list);			
		}else {
			System.out.println("\nEmpty Set - No Records to display!!!");
		}
		em.close();
		emf.close();
	}
}
