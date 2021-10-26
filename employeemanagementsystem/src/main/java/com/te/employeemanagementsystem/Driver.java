package com.te.employeemanagementsystem;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.te.employeemanagementsystem.home.HomePage;

public class Driver {
	
	public static void main(String[] args) {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
		HomePage.homePage();
	
	}
	
}
