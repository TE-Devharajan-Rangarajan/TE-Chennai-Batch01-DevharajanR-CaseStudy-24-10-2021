package com.te.employeemanagementsystem.operations.showdetails;

import java.util.List;

import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.home.EntityClass;
import com.te.employeemanagementsystem.operations.PrintTable;

public class DisplayAllRecords {
	
	private DisplayAllRecords() {
	}

	public static void displayAllRecords(EntityClass ec) {
		
		Query query = ec.getEm().createQuery("from Info");
		
		@SuppressWarnings("unchecked")
		List<Info> list = query.getResultList();
		
		if (!list.isEmpty()) {
			
			PrintTable.printTable(list);
		
		} else {
			
			System.out.println("\nEmpty Set - No Records to display!!!");
		
		}
		
	}

}
