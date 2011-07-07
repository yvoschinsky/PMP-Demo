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
@Table(name="retailer")
public class Retailer implements Serializable {
	
	@Id
    @GeneratedValue
    @Column(name="id")
	private Long id;
	
    @Column(name="name", length=128)
	private String name;
	
	@OneToMany(fetch= FetchType.EAGER, mappedBy="retailer")
	private Collection<Tracker> trackerCollection;

	public Long getId() {
		return id;
	}

	public Retailer setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Retailer setName(String name) {
		this.name = name;
		return this;
	}

	public Collection getTrackerCollection() {
		return trackerCollection;
	}

	public Retailer setTrackerCollection(Collection trackerCollection) {
		this.trackerCollection = trackerCollection;
		return this;
	}
	
}
