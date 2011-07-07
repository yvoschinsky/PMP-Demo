/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.service.impl;

import com.pricetector.pmpdemo.dao.UserDao;
import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.model.User;
import com.pricetector.pmpdemo.service.UserService;
import java.util.Collection;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dbukhanets
 */
@Repository("userService")
public class UserServiceImpl implements UserService{

    @Resource(name="userDao")
    private UserDao userDao;

	@Autowired
	protected HttpServletRequest httpServletRequest;
	
	@Override
	public User getLoggedInUser() {
		/**
		 * TODO: remove after implementing users registration and logging in
		 */
		httpServletRequest.getSession().setAttribute("loggedInUser", userDao.get(1L));

		User user = new User();
		if
		(
			httpServletRequest.getSession() != null &&
			User.class.isInstance
			(
				httpServletRequest.getSession().getAttribute("loggedInUser")
			)
		)
		{
			user = (User) httpServletRequest
				.getSession().getAttribute("loggedInUser");
		}else
		{
			user = null;
		}
		
		return user;
	}

	@Override
	public Collection<Tracker> getFulfilledUsers(String sku, Double price) {
		return userDao.getFulfilledUsers(sku, price);
	}
	
}
