/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pricetector.pmpdemo.dao.hibernate;

import com.pricetector.pmpdemo.dao.TrackerDao;
import com.pricetector.pmpdemo.model.Tracker;
import com.pricetector.pmpdemo.model.User;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dbukhanets
 */
@Repository("trackerDao")
public class TrackerDaoHibernate extends GenericDaoHibernate<Tracker, Long> implements TrackerDao
{
    @Autowired
    public TrackerDaoHibernate(@Qualifier("sessionFactory") SessionFactory sessionFactory)
    {
        super(Tracker.class);
        setSessionFactory(sessionFactory);
    }

	@Override
	public List<Tracker> getAllTrackers(User activeUser) {
        return this.getHibernateTemplate().find("from " + persistentClass.getName() + " obj where obj.user = ? ", activeUser);
	}

	@Override
	public List<Tracker> getActiveTrackers(User activeUser) {
		/**
		 * 1 day in timestamp is 86400000
		 */
        return this.getHibernateTemplate().find("from " + persistentClass.getName() + " obj where TIMESTAMPDIFF(DAY, obj.dateCreated, NOW()) < obj.period and obj.user = ? ", activeUser);
	}

	@Override
	public List<String> getTrackedSkuList() {
        return this.getHibernateTemplate().find("select distinct obj.sku from " + persistentClass.getName() + " obj where TIMESTAMPDIFF(DAY, obj.dateCreated, NOW()) < obj.period");
	}
}
