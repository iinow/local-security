package com.ha.entity;

import java.io.Serializable;
import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "calendar_users")
public class CalendarUser implements Principal, Serializable {
	private static final long serialVersionUID = -5252002499084747573L;

	public CalendarUser() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "first_name")
    private String firstName;
	
	@Column(name = "last_name")
    private String lastName;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "password")
    private String password;

	@Override
	public String getName() {
		return getEmail();
	}
}
