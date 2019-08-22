package com.ha.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "remember_me")
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class RememberMe {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 64)
	private String id;
	
	@Column(name="username", nullable = false)
	private String username;
	
	@Column(name="token", nullable = false)
	private String token;

	@Column(name="last_used", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUsed;
}
