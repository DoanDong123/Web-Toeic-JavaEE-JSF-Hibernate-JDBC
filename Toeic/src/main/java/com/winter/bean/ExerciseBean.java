/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import com.winter.pojo.Category;
import com.winter.pojo.Exercise;
import com.winter.services.ExerciseServices;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "exerciseBean")
@RequestScoped
public class ExerciseBean implements Serializable {

    private static ExerciseServices exerciseService = new ExerciseServices();
    private String keyword;
    private int exerciseId;
    private String name;
    private Category category;

    /**
     * Creates a new instance of ExerciseBean
     */
    public ExerciseBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String eId = FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("exercise_id");
            if (eId != null && !eId.isEmpty()) {
                Exercise e = exerciseService.getExerciseId(Integer.parseInt(eId));
                this.exerciseId = e.getId();
                this.name = e.getName();
                this.category = e.getCategoryId();
            }
        }
    }

    public List<Exercise> getExercises() {
        return getExerciseService().getExercise(this.keyword);
    }
    
    public List<Exercise> getExercisesByName(String name){
        return getExerciseService().getExercise(name);
    }

    public String addExercise() {
        Exercise e;
        if (this.exerciseId > 0) {
            //update
            e = exerciseService.getExerciseId(this.exerciseId); // persistent
        } else {
            //add
            e = new Exercise(); // transient
        }
        e.setName(this.name);
        e.setCategoryId(this.category);

        if (exerciseService.addOrSaveExercise(e) == true) {
            return "exercise?faces-redirect=true";
        }

        return "addexercise";
    }

    public String deleteExercise(Exercise e) {

        return "successful";
    }

    /**
     * @return the keyword
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword the keyword to set
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
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
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return the exerciseService
     */
    public static ExerciseServices getExerciseService() {
        return exerciseService;
    }

    /**
     * @param aExerciseService the exerciseService to set
     */
    public static void setExerciseService(ExerciseServices aExerciseService) {
        exerciseService = aExerciseService;
    }

    /**
     * @return the exerciseId
     */
    public int getExerciseId() {
        return exerciseId;
    }

    /**
     * @param exerciseId the exerciseId to set
     */
    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
