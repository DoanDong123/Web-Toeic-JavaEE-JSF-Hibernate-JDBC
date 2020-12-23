/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "scoreBean")
@SessionScoped
public class ScoreBean implements Serializable {

    /**
     * Creates a new instance of ScoreBean
     */
    public ScoreBean() {
    }

    @PostConstruct
    public void init() {
        if (FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("answer") == null) {
            FacesContext.getCurrentInstance()
                    .getExternalContext().getSessionMap().put("answer", new HashMap<>());

        }
    }

    public String saveAnswer(int questionId, int choiceId, Boolean isCorrect) {
        Map<Integer, Object> answer = (Map<Integer, Object>) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("answer");

        if (answer.get(choiceId) == null) {
            Map<String, Object> data = new HashMap<>();
            data.put("questionId", questionId);
            data.put("choiceId", choiceId);
            data.put("isCorrect", isCorrect);

            answer.put(questionId, data);
        } else {
            Map<String, Object> data = (Map<String, Object>) answer.get(questionId);
            data.put("choiceId", choiceId);
            data.put("isCorrect", isCorrect);
        }
        return null;
    }

}
