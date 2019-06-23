package com.sayedbaladoh.userservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Model
 * 
 * @author SayedBaladoh
 *
 */
@Table(name = "users")
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "organization_id")
	private Long organizationId;

	@Column(name = "site_id")
	private Long siteId;

	private String name;
	private String email;
	private String phone;
	private String position;

	public User() {

	}

	public User(Long organizationId, Long siteId, String name, String email, String phone, String position) {
		this.organizationId = organizationId;
		this.siteId = siteId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", organizationId=" + organizationId + ", siteId=" + siteId + ", name=" + name
				+ ", email=" + email + ", phone=" + phone + ", position=" + position + "]";
	}

}
