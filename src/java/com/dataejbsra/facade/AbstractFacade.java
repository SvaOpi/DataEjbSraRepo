/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dataejbsra.facade;

import com.dataejbsra.vo.ROb;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Usuario1
 */
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    protected void create(T entity) {
        getEntityManager().persist(entity);
    }

    protected void edit(T entity) {
        getEntityManager().merge(entity);
    }

    protected void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public ROb find(Object id) {
        ROb rob = new ROb();
        try{
            T object = getEntityManager().find(entityClass, id);
            rob.setData(object);
            rob.setSuccess(true);
            return rob;
        }catch(Exception e){
            rob.setErr_message("Failed Transaction");
            rob.setSuccess(false);
            rob.setData(null);
            return rob;
        }
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
