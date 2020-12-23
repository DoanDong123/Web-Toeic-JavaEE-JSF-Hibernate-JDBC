/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.bean;

import com.winter.pojo.Category;
import com.winter.services.CategoryServices;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ACER
 */
@ManagedBean
@Named(value = "categoryBean")
@RequestScoped
public class CategoryBean implements Serializable {
    private int categoryId;
    private String name;
    private static CategoryServices categoryService = new CategoryServices();
    private String keyword;

    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            String cId = FacesContext.getCurrentInstance().getExternalContext()
                    .getRequestParameterMap().get("category_id");
            if (cId != null && !cId.isEmpty()) {
                Category c = categoryService.getCategoryId(Integer.parseInt(cId));
                this.categoryId = c.getId();
                this.name = c.getName();
            }
        }
    }

    public List<Category> getCategories() {
        return getCategoryService().getCates(this.keyword);
    }

    public String addCategory() {
        Category c;
        if (this.categoryId > 0) {
            //update
            c = categoryService.getCategoryId(this.categoryId); // persistent
        } else {
            //add
            c = new Category(); // transient
        }
        c.setName(this.name);

        if (categoryService.addOrSaveCategory(c) == true) {
            return "/admin?faces-redirect=true";
        }

        return "addcategory";
    }
    
    public String deleteCategory(Category c) {
        
        return "successful";
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
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the categoryService
     */
    public static CategoryServices getCategoryService() {
        return categoryService;
    }

    /**
     * @param aCategoryService the categoryService to set
     */
    public static void setCategoryService(CategoryServices aCategoryService) {
        categoryService = aCategoryService;
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
