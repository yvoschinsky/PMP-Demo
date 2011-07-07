/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.dao;

import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.model.User;
import java.util.List;

/**
 *
 * @author dbukhanets
 */
public interface TrackerDao extends GenericDao<Tracker, Long>
{
	public List<Tracker> getAllTrackers(User activeUser);
	public List<Tracker> getActiveTrackers(User activeUser);
	public List<String> getTrackedSkuList();
}
