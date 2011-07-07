/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.dao;

import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.model.User;
import java.util.Collection;

/**
 *
 * @author dbukhanets
 */
public interface UserDao extends GenericDao<User, Long>{
	public Collection<Tracker> getFulfilledUsers(String sku, Double price);
}
