package com.te.employeemanagementsystem.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "info")
@Data
public class Info implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Info() {
		
		/**
		 * This is a Entity class constructor
		 */
	}

	@Column
	@Id
	private Integer id;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column
	private Date dob;
	
	@Column
	private String gender;
	
	@Column
	private Double salary;
	
	@Column
	private String role;
	
	@Column
	private Long mobile;
	
	@Column
	private String email;
	
	@Column(name = "bloodgroup")
	private String bloodGroup;

}
