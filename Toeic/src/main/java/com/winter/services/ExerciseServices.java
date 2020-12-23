/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.services;

import com.winter.pojo.Category;
import com.winter.pojo.Exercise;
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
public class ExerciseServices {

    private final static SessionFactory FACTORY = HibernateUtils.getFACTORY();

    public List<Exercise> getExercise(String kw) {
        try ( Session session = FACTORY.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery();
            Root<Exercise> root = query.from(Exercise.class);
            query = query.select(root);

            if (kw != null && !kw.isEmpty()) {
                query = query.where(builder.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw)));
            }

            Query q = session.createQuery(query);
            return q.getResultList();
        }
    }

    public boolean addOrSaveExercise(Exercise e) {
        try ( Session session = FACTORY.openSession()) {
            try {
                session.getTransaction().begin();
                session.saveOrUpdate(e);
                session.getTransaction().commit();
            } catch (Exception ex) {
                session.getTransaction().rollback();
                return false;
            }
        }
        return true;
    }

    public Exercise getExerciseId(int id) {
        try ( Session session = FACTORY.openSession()) {
            Exercise x = session.get(Exercise.class, id);
            return x;
        }
    }

    public Exercise getExerciseId(String id) {
        return this.getExerciseId(Integer.parseInt(id));
    }
}
