/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import com.winter.pojo.Choice;
import com.winter.pojo.Question;
import com.winter.services.ChoiceServices;
import com.winter.services.QuestionServices;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "choiceBean")
@RequestScoped
public class ChoiceBean implements Serializable {

    private String keyword;
    private static ChoiceServices choiceService = new ChoiceServices();
    private int choiceId;
    private String content;
    private Boolean is_correct;
    private Question question;

    /**
     * Creates a new instance of ChoiceBean
     */
    public ChoiceBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String cId = FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("choice_id");
            if (cId != null && !cId.isEmpty()) {
                Choice c = choiceService.getChoiceId(Integer.parseInt(cId));
                this.choiceId = c.getId();
                this.content = c.getContent();
                this.is_correct = c.getIs_correct();
                this.question = c.getQuestionId();
            }
        }
    }

    public List<Choice> getChoices() {
        return getChoiceService().getChoices(this.keyword);
    }

    public List<Choice> getChoiceByQuestion(int id) {
//         String eId = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap().get("exerciseId");
//        
//        if (eId != null && !eId.isEmpty()) {
//            Exercise e = new ExerciseServices().getExerciseId(eId);
//
//           return questionService.getQuestionByExercise(e);
//        }
        Question q = new QuestionServices().getQuestionId(id);
        return choiceService.getChoiceByQuestion(q);
    }

    public String addChoice() {
        Choice c;
        if (this.choiceId > 0) {
            //update 
            c = choiceService.getChoiceId(this.choiceId); // persistent
        } else {
            //add
            c = new Choice(); // transient
        }
        c.setContent(this.content);
        c.setIs_correct(this.is_correct);
        c.setQuestionId(this.question);

        if (choiceService.addOrSaveChoice(c) == true) {
            return "choice?faces-redirect=true";
        }

        return "addchoice";
    }

    /**
     * @return the choiceService
     */
    public static ChoiceServices getChoiceService() {
        return choiceService;
    }

    /**
     * @param aChoiceService the choiceService to set
     */
    public static void setChoiceService(ChoiceServices aChoiceService) {
        choiceService = aChoiceService;
    }

    /**
     * @return the choiceId
     */
    public int getChoiceId() {
        return choiceId;
    }

    /**
     * @param choiceId the choiceId to set
     */
    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
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
     * @return the question
     */
    public Question getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(Question question) {
        this.question = question;
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

}
