/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import com.winter.pojo.User;
import com.winter.services.UserServices;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Transient;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "userBean")
@RequestScoped
public class UserBean {

    private String lastName;
    private String firstName;
    private String email;
    private String username;
    private String password;
    private String userRole;

    @Transient
    private String confirmPassword;

    private static UserServices userServies = new UserServices();

    public String register() {
        if (!this.password.isEmpty() && this.password.equals(this.confirmPassword)) {
            User u = new User();
            u.setLast_name(lastName);
            u.setFirst_name(firstName);
            u.setEmail(email);
            u.setUsername(username);
            u.setPassword(password);
            u.setActive(Boolean.TRUE);
            u.setUser_role("USER");

            if (userServies.addUser(u) == true) {
                return "login?faces-redirect=true";
            }
        }
        return "register";
    }

    public String login() {
        User u = userServies.login(username, password);
        if (u != null) {
//            System.out.println(u.getUser_role());
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("user", u);
            if (u.getUser_role().equals("ADMIN")) {
                return "admin?faces-redirect=true";
            } else {
                return "index?faces-redirect=true";
            }
        }
        return "login";
    }

    public String checkLogin() {
//        Object x = FacesContext.getCurrentInstance()
//                .getExternalContext().getSessionMap().get("user");
        if (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("user") != null) {
            return "/index?faces-redirect=true";
        }

        return "login";
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().remove("user");
        return "/login?faces-redirect=true";
    }

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmPassword
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @param confirmPassword the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

}
