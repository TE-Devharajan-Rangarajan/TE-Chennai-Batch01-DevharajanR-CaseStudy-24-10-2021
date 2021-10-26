package com.te.employeemanagementsystem.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "logininfo")
@Data
public class LoginInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginInfo() {
		
		/**
		 * This is a Entity class constructor
		 */
		
	}

	@Column
	@Id
	private Integer id;
	
	@Column
	private String password;

}
