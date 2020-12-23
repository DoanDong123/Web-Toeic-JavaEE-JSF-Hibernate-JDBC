/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "homeBean")
@RequestScoped
public class HomeBean {

    /**
     * Creates a new instance of HomeBean
     */
    public HomeBean() {
    }

}
