/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.dao.hibernate;

import com.pricetector.pmpdemo.dao.UserDao;
import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.model.User;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dbukhanets
 */
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao
{
    @Autowired
    public UserDaoHibernate(@Qualifier("sessionFactory") SessionFactory sessionFactory)
    {
        super(User.class);
        setSessionFactory(sessionFactory);
    }

	@Override
	public Collection<Tracker> getFulfilledUsers(String sku, Double price) {
        return this.getHibernateTemplate().find("from " + Tracker.class.getName() + " obj where obj.affordablePrice >= ? and obj.sku = ? ", price, sku);
	}
}
