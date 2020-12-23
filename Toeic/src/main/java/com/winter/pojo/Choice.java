/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "choice")
public class Choice implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private Boolean is_correct;
    
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question questionId;
    
    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        Choice c = (Choice) obj;
        
        return this.id == c.id;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the is_correct
     */
    public Boolean getIs_correct() {
        return is_correct;
    }

    /**
     * @param is_correct the is_correct to set
     */
    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    /**
     * @return the questionId
     */
    public Question getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }
    
}
