/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dbukhanets
 */
@Entity
@Table(name="user")
public class User implements Serializable {

	@Id
    @GeneratedValue
    @Column(name="id")
	private Long id;
	
    @Column(name="firstName", length=128)
	private String firstName;
	
    @Column(name="lastName", length=128)
	private String lastName;
	
    @Column(name="middleName", length=128)
	private String middleName;
	
    @Column(name="email", length=128)
	private String email;
	
    @Column(name="password", length=128)
	private String password;
	
	@OneToMany(fetch= FetchType.EAGER, mappedBy="user")
	private Collection<Tracker> trackerCollection;

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public Long getId() {
		return id;
	}

	public User setId(Long id) {
		this.id = id;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public User setMiddleName(String middleName) {
		this.middleName = middleName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public Collection getTrackerCollection() {
		return trackerCollection;
	}

	public User setTrackerCollection(Collection trackerCollection) {
		this.trackerCollection = trackerCollection;
		return this;
	}
}
