/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.services;

import com.winter.pojo.Category;
import com.winter.pojo.Choice;
import com.winter.pojo.Exercise;
import com.winter.pojo.Question;
import com.winter.pojo.Scoreboard;
import com.winter.pojo.User;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Winter
 */
public class HibernateUtils {
    private static final SessionFactory FACTORY;
    
    static {
        Configuration conf = new Configuration();
        
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Exercise.class);
        conf.addAnnotatedClass(Question.class);
        conf.addAnnotatedClass(Choice.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(Scoreboard.class);
        
        Properties props = new Properties();
        props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        props.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        props.put(Environment.URL, "jdbc:mysql://localhost/toeicdb");
        props.put(Environment.USER, "root");
        props.put(Environment.PASS, "12345678");
        props.put(Environment.SHOW_SQL, true);
        
        
        conf.setProperties(props);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                                .applySettings(conf.getProperties())
                                .build();
        
        FACTORY = conf.buildSessionFactory(registry);
    }

    /**
     * @return the FACTORY
     */
    public static SessionFactory getFACTORY() {
        return FACTORY;
    }
    
    
}
