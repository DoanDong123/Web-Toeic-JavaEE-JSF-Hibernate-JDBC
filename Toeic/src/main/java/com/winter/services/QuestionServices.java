/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.services;

import com.winter.pojo.Exercise;
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
public class QuestionServices {

    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();

    public List<Question> getQuestions(String kw) {
        try ( Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Question> root = query.from(Question.class);
            query = query.select(root);

            if (kw != null && !kw.isEmpty()) {
                query = query.where(builder.like(root.get("content").as(String.class),
                        String.format("%%%s%%", kw)));
            }

            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public List<Question> getQuestionByExercise(Exercise e) {
        try ( Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Question> root = query.from(Question.class);
            query = query.select(root);

            query = query.where(builder.equal(root.get("exerciseId").as(Exercise.class),
                    e));

            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public boolean addOrSaveQuestion(Question q) {
        try ( Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(q);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }

    public boolean deleteQuestion(Question q) {
        try ( Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.delete(q);
                session.getTransaction().commit();

            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }

    public Question getQuestionId(int id) {
        try ( Session session = FACTORY.openSession()) {
            return session.get(Question.class, id);
        }
    }

    public Question getQuestionId(String id) {
        return this.getQuestionId(Integer.parseInt(id));
    }
}
