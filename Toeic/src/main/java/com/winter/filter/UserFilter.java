/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.winter.filter;

import com.winter.pojo.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ACER
 */
@WebFilter(urlPatterns = {"/faces/admin.xhtml", "/faces/category/addcategory.xhtml",
    "/faces/question/question.xhtml", "/faces/question/addquestion.xhtml",
    "/faces/exercise/exercise.xhtml", "/faces/exercise/addexercise.xhtml",
    "/faces/choice/choice.xhtml", "/faces/choice/addchoice.xhtml"})
public class UserFilter implements Filter {

    private HttpServletRequest httpRequest;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession();
        User u = (User) session.getAttribute("user");
        if (u != null) {
//            boolean x = u.getUser_role().equals("ADMIN");
//            boolean y = httpRequest.getRequestURI().contains("/admin/");
            if (!u.getUser_role().equals("ADMIN")) {
                String home = "/faces/index.xhtml";
                RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(home);
                dispatcher.forward(request, response);

            } else {
                chain.doFilter(request, response);
            }
        } else {
            String loginPage = "/faces/login.xhtml";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
