/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.service.impl;

import com.pricetector.pmpdemo.dao.TrackerDao;
import com.pricetector.pmpdemo.service.TrackerService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dbukhanets
 */
@Repository("trackerService")
public class TrackerServiceImpl implements TrackerService{

	@Resource(name = "trackerDao")
	private TrackerDao trackerDao;

	@Override
	public List<String> getTrackedSkuCollection() 
	{
		return trackerDao.getTrackedSkuList();
	}
	
}
