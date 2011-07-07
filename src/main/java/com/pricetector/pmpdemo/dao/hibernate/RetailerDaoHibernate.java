/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.dao.hibernate;

import com.pricetector.pmpdemo.dao.RetailerDao;
import com.pricetector.pmpdemo.model.Retailer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dbukhanets
 */
@Repository("retailerDao")
public class RetailerDaoHibernate extends GenericDaoHibernate<Retailer, Long> implements RetailerDao
{
    @Autowired
    public RetailerDaoHibernate(@Qualifier("sessionFactory") SessionFactory sessionFactory)
    {
        super(Retailer.class);
        setSessionFactory(sessionFactory);
    }
}
