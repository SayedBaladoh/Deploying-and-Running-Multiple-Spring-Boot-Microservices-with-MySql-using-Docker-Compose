package com.sayedbaladoh.organizationservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Site Entity
 * 
 * @author SayedBaladoh
 *
 */
public class Site {
	

	private Long id;
	
	private String name;
	
	private List<User> users = new ArrayList<>();

	public Site() {
		
	}

	public Site(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", name=" + name + "]";
	}

}
