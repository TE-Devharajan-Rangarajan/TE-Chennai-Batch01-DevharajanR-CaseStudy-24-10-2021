package com.te.employeemanagementsystem.register;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import com.te.employeemanagementsystem.bean.Info;
import com.te.employeemanagementsystem.bean.LoginInfo;
import com.te.employeemanagementsystem.exceptions.ColumnConditionFailedException;
import com.te.employeemanagementsystem.exceptions.EmployeeAlreadyExistsException;
import com.te.employeemanagementsystem.exceptions.IllegalDateException;
import com.te.employeemanagementsystem.exceptions.NotAValidNumberException;
import com.te.employeemanagementsystem.exceptions.NotLegalToWorkException;
import com.te.employeemanagementsystem.exceptions.PasswordMismatchException;
import com.te.employeemanagementsystem.home.EntityClass;

public final class Ensure {

	private Ensure() {
	}

	static final String EXCEPTIONMESSAGE = "Not a Numeric Value!!!";
	static String str = null;
	static Info info = new Info();
	private static int id;
	private static String password;
	

	public static Info ensureInfo(EntityClass ec) {

		System.out.println("\nEnter Id: ");
		str = ec.getSc().next();
		
		try {
			
			ensureId(str, ec);
			info.setId(Integer.parseInt(str));

		} catch (ColumnConditionFailedException | NotAValidNumberException | EmployeeAlreadyExistsException e) {
			
			System.out.println();
			System.out.println(e.getMessage());
			return null;
		
		}

		System.out.println("\nEnter First Name: ");
		
		str = ec.getSc().next();
		
		try {
			ensureName(str);
			info.setFirstName(str);
		} catch (ColumnConditionFailedException e) {
			System.out.println();
			System.out.println(e.getMessage());
			return null;
		}
		

		System.out.println("\nEnter Last Name: ");

		str = ec.getSc().next();
		
		try {
			ensureName(str);
			info.setLastName(str);
		} catch (ColumnConditionFailedException e) {
			System.out.println();
			System.out.println(e.getMessage());
			return null;
		}
		
		System.out.println("\nEnter Date Of Birth (YYYY-MM-DD): ");
		str = ec.getSc().next();
		
		try {
			
			ensureDate(str);
			info.setDob(Date.valueOf(str));
		
		} catch (ColumnConditionFailedException | NotLegalToWorkException | IllegalDateException e) {
			
			System.out.println(e.getMessage());
			return null;
		
		}

		System.out.println("\nEnter Gender: ");
		info.setGender(ec.getSc().next());

		System.out.println("\nEnter Salary:");
		str = ec.getSc().next();
		
		try {
			
			ensureSalary(str);
			info.setSalary(Double.parseDouble(str));
		
		} catch (ColumnConditionFailedException | NotAValidNumberException e) {
			
			System.out.println(e.getMessage());
			return null;
		
		}

		System.out.println("\nEnter Role: ");
		info.setRole(ec.getSc().next());

		System.out.println("\nEnter Mobile Number: ");
		str = ec.getSc().next();
		
		try {
			
			ensureMobile(str);
			info.setMobile(Long.parseLong(str));
		
		} catch (ColumnConditionFailedException | NotAValidNumberException e) {
			
			System.out.println(e.getMessage());
			return null;
		
		}

		System.out.println("\nEnter Email Address: ");
		info.setEmail(ec.getSc().next());

		System.out.println("\nEnter Blood Group: ");
		info.setBloodGroup(ec.getSc().next());

		try {
			
			ensurePassword(ec);
		
		} catch (PasswordMismatchException e) {
			
			System.out.println(e.getMessage());
			return null;
		}

		return info;
	}

	public static void ensureId(String str, EntityClass ec)
			throws ColumnConditionFailedException, NotAValidNumberException, EmployeeAlreadyExistsException {
		if (isNumber(str)) {

			if (Integer.parseInt(str) > 0) {
				
				String query = "from LoginInfo where id = " + id;
				Query qry = ec.getEm().createQuery(query);
				
				@SuppressWarnings("unchecked")
				List<LoginInfo> list = qry.getResultList();
				
				if (list.isEmpty()) {

				} else {
					
					throw new EmployeeAlreadyExistsException("Employee Already Exists for the ID entered!!!");
				
				}

			} else {
				
				throw new ColumnConditionFailedException("Invalid ID!!!");
			
			}
		} else {
			
			throw new NotAValidNumberException(EXCEPTIONMESSAGE);
		
		}
	
	}

	public static void ensureDate(String str)
			throws ColumnConditionFailedException, NotLegalToWorkException, IllegalDateException {

		if (isLegalDate(str)) {
		
		} else {
			
			throw new ColumnConditionFailedException("Invalid Date!!!");
		
		}

	}

	public static void ensureSalary(String str) throws ColumnConditionFailedException, NotAValidNumberException {

		if (isNumber(str)) {
			
			if (Double.parseDouble(str) > 0) {

			} else {
				
				throw new ColumnConditionFailedException("Salary must be greater than 0!!!");
			
			}
		} else {
			
			throw new NotAValidNumberException(EXCEPTIONMESSAGE);
		
		}
	
	}

	public static void ensureMobile(String str) throws ColumnConditionFailedException, NotAValidNumberException {

		if (isNumber(str)) {
			
			if (str.length() == 10 && Long.parseLong(str) > 0) {

			} else {
				
				throw new ColumnConditionFailedException("Invalid Mobile Number!!!");
			
			}
		} else {
			
			throw new NotAValidNumberException(EXCEPTIONMESSAGE);
		
		}
	
	}

	public static void ensurePassword(EntityClass ec) throws PasswordMismatchException {
		
		System.out.println("Enter Password:");
		String pwd = ec.getSc().next();
		
		System.out.println("Re-Enter Password: ");
		
		if (pwd.equals(ec.getSc().next())) {
			
			setPassword(pwd);
		
		} else {
			
			throw new PasswordMismatchException("Password Mismatch Error!!!");
		}
	
	}

	public static boolean isNumber(String str) {
		
		try {
			
			Double.parseDouble(str);
			return true;
		
		} catch (NumberFormatException e) {
			
			return false;
		
		}
	
	}

	public static boolean isLegalDate(String str) throws NotLegalToWorkException, IllegalDateException {
		
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

		try {
			
			LocalDate today = LocalDate.now(ZoneId.systemDefault());
			LocalDate ld = LocalDate.parse(str, fomatter);
			
			if (ld.isAfter(today)) {
				
				throw new IllegalDateException("WOW!!! How does the future look???? Also Ineligible Date Mate!!!");
			}

			if (ld.isBefore(today.minusYears(18))) {
				
				String result = ld.format(fomatter);
				return result.equals(str);
			
			} else {
				
				throw new NotLegalToWorkException("Child Labour Not Permitted!!!");
			
			}
		} catch (DateTimeParseException exp) {
			
			return false;
		}

	}
	
	public static void ensureName(String str) throws ColumnConditionFailedException {
		
		if(isName(str)) {
			
		}else {
			
			throw new ColumnConditionFailedException("Cannot contain number(s) in Name!!!");
			
		}
		
	}
	
	public static boolean isName(String str) {
		
		return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z]*$")));
		
	}

	public static String getPassword() {
		
		return password;
	
	}

	public static void setPassword(String password) {
		
		Ensure.password = password;
	
	}

	public static int getId() {
		
		return id;
	
	}

	public static void setId(int id) {
		
		Ensure.id = id;
	
	}

}
