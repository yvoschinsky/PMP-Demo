/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dbukhanets
 */
@Entity
@Table(name = "tracker")
public class Tracker implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne()
    @JoinColumn(name = "retailer")
    private Retailer retailer;
    @Column(name = "productId", length = 128)
    private String sku;
    @Column(name = "period", length = 128)
    private Long period;
    @Column(name = "email", length = 128)
    private String email;
    @Column(name = "dateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @Column(name = "affordablePrice")
    private Double affordablePrice;

    public Double getAffordablePrice() {
        return affordablePrice;
    }

    public Tracker setAffordablePrice(Double affordablePrice) {
        this.affordablePrice = affordablePrice;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Tracker setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSku() {
        return sku;
    }

    public Tracker setSku(String productId) {
        this.sku = productId;
        return this;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public Tracker setRetailer(Retailer retailer) {
        this.retailer = retailer;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Tracker setUser(User user) {
        this.user = user;
        return this;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Tracker setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public Long getPeriod() {
        return period;
    }

    public Tracker setPeriod(Long period) {
        this.period = period;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Tracker setEmail(String email) {
        this.email = email;
        return this;
    }
}
