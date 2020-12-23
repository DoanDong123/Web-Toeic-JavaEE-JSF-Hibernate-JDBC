/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.pojo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "exercise")
public class Exercise implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category categoryId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId", fetch = FetchType.EAGER)
    private Collection<Question> questionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Collection<Scoreboard> scoreboardCollection;

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        Exercise e = (Exercise) obj;
        
        return this.id == e.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        return hash;
    }
    
    
    
    

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the categoryId
     */
    public Category getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the questionCollection
     */
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    /**
     * @param questionCollection the questionCollection to set
     */
    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    /**
     * @return the scoreboardCollection
     */
    public Collection<Scoreboard> getScoreboardCollection() {
        return scoreboardCollection;
    }

    /**
     * @param scoreboardCollection the scoreboardCollection to set
     */
    public void setScoreboardCollection(Collection<Scoreboard> scoreboardCollection) {
        this.scoreboardCollection = scoreboardCollection;
    }
}
