/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import com.winter.pojo.Exercise;
import com.winter.pojo.Question;
import com.winter.services.ExerciseServices;
import com.winter.services.QuestionServices;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "questionBean")
@RequestScoped
public class QuestionBean implements Serializable {

    private static QuestionServices questionService = new QuestionServices();
    private String keyword;
    private int questionId;

    private String content;
    private Part image;
    private Part audio;
    private Exercise exercise;

    /**
     * Creates a new instance of QuestionBean
     */
    public QuestionBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String quesId = FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("question_id");
            if (quesId != null && !quesId.isEmpty()) {
                Question q = questionService.getQuestionId(Integer.parseInt(quesId));
                this.questionId = q.getId();
                this.content = q.getContent();
                this.exercise = q.getExerciseId();
            }
        }
    }

    public List<Question> getQuestions() {
        String eId = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("exerciseId");
        
        if (eId != null && !eId.isEmpty()) {
            Exercise e = new ExerciseServices().getExerciseId(eId);

           return questionService.getQuestionByExercise(e);
        }
        
        return questionService.getQuestions(this.keyword);
    }

    public String addQuestion() {
//        String quesId = FacesContext.getCurrentInstance().getExternalContext()
//                .getRequestParameterMap().get("question_id");
        Question q;
        if (this.questionId > 0) {
            //update Question
            q = questionService.getQuestionId(this.questionId); // persistent
        } else {
            //add Question
            q = new Question(); // transient
        }
        q.setContent(this.content);
        q.setExerciseId(this.exercise);

        try {
            if (this.image != null) {
                this.uploadImage();
                q.setImage("upload/" + this.image.getSubmittedFileName());
            }
            
            if (this.audio != null) {
                this.uploadAudio();
                q.setAudio("upload/" + this.audio.getSubmittedFileName());
            }

            if (questionService.addOrSaveQuestion(q) == true) {
                return "question?faces-redirect=true";
            }
        } catch (IOException ex) {
            Logger.getLogger(QuestionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "addquestion";
    }

    private void uploadImage() throws IOException {
//        String path = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRealPath("/resources/images/upload")
//                + "/" + this.image.getSubmittedFileName();
//        String path = "F:\\Dong\\LapTrinhJava\\Toeic\\Toeic\\src\\main\\webapp\\resources\\images\\upload\\"
//                + this.image.getSubmittedFileName();
        String path = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getInitParameter("com.winter.uploadImage")
                + this.image.getSubmittedFileName();
        try ( InputStream input = this.image.getInputStream();  FileOutputStream output = new FileOutputStream(path)) {
            byte[] b = new byte[1024];
            int byteRead;
            while ((byteRead = input.read(b)) != -1) {
                output.write(b, 0, byteRead);
            }
        }

    }
    
    private void uploadAudio() throws IOException {
        String path = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getInitParameter("com.winter.uploadAudio")
                + this.audio.getSubmittedFileName();
        try ( InputStream input = this.audio.getInputStream();  FileOutputStream output = new FileOutputStream(path)) {
            byte[] b = new byte[1024];
            int byteRead;
            while ((byteRead = input.read(b)) != -1) {
                output.write(b, 0, byteRead);
            }
        }

    }

    
    public String deleteQuestion(Question q) throws Exception {
        if (questionService.deleteQuestion(q) == true) {
            return "successfull";
        }

        throw new Exception("Delete failed");
    }

//    public List<Question> getQuestions() {
//        String exerId = FacesContext.getCurrentInstance()
//                .getExternalContext()
//                .getRequestParameterMap().get("exerId");
//
//        if (exerId != null && !exerId.isEmpty()) {
//            Exercise c = new ExerciseServices().getExerciseId(exerId);
//
//            return (List<Question>) c.getQuestionCollection();
//        }
//
//        return new QuestionServices().getQuestions(this.keyword);
//    }
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
     * @return the questionService
     */
    public static QuestionServices getQuestionService() {
        return questionService;
    }

    /**
     * @param aQuestionService the questionService to set
     */
    public static void setQuestionService(QuestionServices aQuestionService) {
        questionService = aQuestionService;
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
     * @return the exercise
     */
    public Exercise getExercise() {
        return exercise;
    }

    /**
     * @param exercise the exercise to set
     */
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    /**
     * @return the image
     */
    public Part getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Part image) {
        this.image = image;
    }

    /**
     * @return the audio
     */
    public Part getAudio() {
        return audio;
    }

    /**
     * @param audio the audio to set
     */
    public void setAudio(Part audio) {
        this.audio = audio;
    }

    /**
     * @return the questionId
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

}
