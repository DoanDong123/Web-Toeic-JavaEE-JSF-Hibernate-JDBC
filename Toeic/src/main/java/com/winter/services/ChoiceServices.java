/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.services;

import com.winter.pojo.Choice;
import com.winter.pojo.Question;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ACER
 */
public class ChoiceServices {
    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();

    public List<Choice> getChoices(String kw) {
        try ( Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Choice> root = query.from(Choice.class);
            query = query.select(root);

            if (kw != null && !kw.isEmpty()) {
                query = query.where(builder.like(root.get("content").as(String.class),
                        String.format("%%%s%%", kw)));
            }

            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }
    
    public List<Choice> getChoiceByQuestion(Question question) {
        try ( Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Choice> root = query.from(Choice.class);
            query = query.select(root);

            query = query.where(builder.equal(root.get("questionId").as(Question.class),
                    question));

            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public boolean addOrSaveChoice(Choice c) {
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
    
    public boolean deleteChoice(Choice c) {
        try ( Session session = FACTORY.openSession()) {
            try {
                System.out.println("OKE");
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

    public Choice getChoiceId(int id) {
        try ( Session session = FACTORY.openSession()) {
            return session.get(Choice.class, id);
        }
    }

    public Choice getChoiceId(String id) {
        return this.getChoiceId(Integer.parseInt(id));
    }
}
