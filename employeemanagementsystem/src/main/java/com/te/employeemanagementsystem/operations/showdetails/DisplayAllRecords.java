package com.te.employeemanagementsystem.operations.showdetails;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.operations.PrintTable;

public class DisplayAllRecords {
	
	private DisplayAllRecords() {
	}

	public static void displayAllRecords(EntityManager em) {
		
		Query query = em.createQuery("from Info");
		
		@SuppressWarnings("unchecked")
		List<Info> list = query.getResultList();
		
		if (!list.isEmpty()) {
			
			PrintTable.printTable(list);
		
		} else {
			
			System.out.println("\nEmpty Set - No Records to display!!!");
		
		}
		
	}

}
