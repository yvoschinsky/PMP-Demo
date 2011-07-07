package com.pricetector.pmpdemo.dao.hibernate;

import com.pricetector.pmpdemo.dao.GenericDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.io.Serializable;
import java.util.List;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use.
 *
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public class GenericDaoHibernate<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {
    protected final Log log = LogFactory.getLog(getClass());

    protected final Class<T> persistentClass;


    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }


    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return this.getHibernateTemplate().loadAll(this.persistentClass);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        T entity = this.getHibernateTemplate().get(this.persistentClass, id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        T entity = this.getHibernateTemplate().get(this.persistentClass, id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        return this.getHibernateTemplate().merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        this.getHibernateTemplate().delete(this.get(id));
    }
}
