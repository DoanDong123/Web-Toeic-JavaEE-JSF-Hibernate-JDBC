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
@Table(name = "question")
public class Question implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    private String image;
    private String audio;
    
    @ManyToOne
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    private Exercise exerciseId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionId", fetch = FetchType.EAGER)
    private Collection<Choice> choiceCollection;

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        Question q = (Question) obj;
        
        return this.id == q.id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
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
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the audio
     */
    public String getAudio() {
        return audio;
    }

    /**
     * @param audio the audio to set
     */
    public void setAudio(String audio) {
        this.audio = audio;
    }

    /**
     * @return the exerciseId
     */
    public Exercise getExerciseId() {
        return exerciseId;
    }

    /**
     * @param exerciseId the exerciseId to set
     */
    public void setExerciseId(Exercise exerciseId) {
        this.exerciseId = exerciseId;
    }

    /**
     * @return the choiceCollection
     */
    public Collection<Choice> getChoiceCollection() {
        return choiceCollection;
    }

    /**
     * @param choiceCollection the choiceCollection to set
     */
    public void setChoiceCollection(Collection<Choice> choiceCollection) {
        this.choiceCollection = choiceCollection;
    }
}
