/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.services;

import com.winter.pojo.Category;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Winter
 */
public class CategoryServices {

    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();

    public List<Category> getCates(String kw) {
        try ( Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Category> root = query.from(Category.class);

            query = query.select(root);
            
            if (kw != null && !kw.isEmpty()) {
                query = query.where(builder.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw)));
            }

            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public boolean addOrSaveCategory(Category c) {
        try ( Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(c);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }
    
    public boolean deleteCategory(Category c) {
        try ( Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(c);
                session.getTransaction().commit();
                
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }

    public Category getCategoryId(int id) {
        try ( Session session = FACTORY.openSession()) {
            return session.get(Category.class, id);
        }
    }

    public Category getCategoryId(String id) {
        return this.getCategoryId(Integer.parseInt(id));
    }
}
