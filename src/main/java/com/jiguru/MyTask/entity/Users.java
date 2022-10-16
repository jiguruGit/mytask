package com.jiguru.MyTask.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private long id;
	@Basic(optional = false)
	@Column(name = "name")
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "dob")
	private LocalDate dateOfBirth;
	@Basic(optional = false)
	@Column(name = "user_name")
	private String userName;
	@JsonIgnore
	@Basic(optional = false)
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_type_id")
	private Integer userTypeId;


}
