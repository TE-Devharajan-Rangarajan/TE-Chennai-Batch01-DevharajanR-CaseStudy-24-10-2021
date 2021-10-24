package com.te.employeemanagementsystem.operations;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.te.employeemanagementsystem.bean.Info;

public class PrintTable {
	
	private PrintTable() {}

	static String finalResult = null;
	static int length;

	public static void printTable(List<Info> list) {
		
		List<List<String>> tableList = new ArrayList<>();
		
		List<String> headers = Arrays.asList("id", "firstName", "lastName", "dob", "gender", "salary", "role", "mobile",
				"email", "bloodGroup");
		
		tableList.add(headers);

		for (Info info : list) {
			Date date = new Date(info.getDob().getTime());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			tableList.add(Arrays.asList(info.getId().toString(), info.getFirstName(), info.getLastName(),
					df.format(date), info.getGender(), info.getSalary().toString(), info.getRole(),
					info.getMobile().toString(), info.getEmail(), info.getBloodGroup()));
		}
		
		finalResult = formatAsTable(tableList);
		
		System.out.print("+");
		for (int i = 0; i < length - 3; i++) {
			System.out.print("-");
		}
		System.out.println("+");
		
		System.out.println(finalResult);
		
		System.out.print("+");
		
		for (int i = 0; i < length - 3; i++) {
			System.out.print("-");
		}
		System.out.println("+");
	}

	public static String formatAsTable(List<List<String>> rows) {
		
		int[] maxLengths = new int[rows.get(0).size()];
		
		for (List<String> row : rows) {
			for (int i = 0; i < row.size(); i++) {
				maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
			}
		}

		StringBuilder formatBuilder = new StringBuilder();
		
		for (int maxLength : maxLengths) {
			formatBuilder.append("| %-").append(maxLength + 2).append("s");
		}
		
		String format = formatBuilder.toString();
		
		StringBuilder result = new StringBuilder();
		
		result.append(String.format(format, rows.get(0).toArray())).append("|\n");
		
		length = result.length();
		
		result.append("+");
		
		for (int i = 0; i < length - 3; i++) {

			result.append("-");
		}
		
		result.append("+\n");
		
		for (int i = 1; i < rows.size(); i++) {
			result.append(String.format(format, rows.get(i).toArray())).append("|\n");
		}
		
		
		result.deleteCharAt(result.length() - 1);
		
		return result.toString();
	}
}
