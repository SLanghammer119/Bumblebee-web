/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.ctrl;

import com.bumblebee.model.Customer;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Stefanie Langhammer
 */
@Named
@SessionScoped
public class LogCtrl extends LookUpData {
    
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    private boolean valid;
    private Customer customer;
    

    public LogCtrl() {
        customer = new Customer();
    }

   
//    @PostConstruct
//    public void init(){
//    customer = new Customer();
//}

    
    public Customer getCustomer() {
        return customer;
    }

    
    public void setCustomer(Customer c) {
        this.customer = c;
    }

    
    public boolean isValid() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if (ec.isUserInRole("customer") || ec.isUserInRole("staff") || ec.isUserInRole("manager")) {
            valid = true;
        }
        valid = false;
        return valid;
    }
    
    
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    
    
    public boolean isManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if (ec.isUserInRole("manager")) {
            return true;
        }
        return false;
    }

    public boolean isCust() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if (ec.isUserInRole("customer")) {
            return true;
        }
        return false;
    }

    public boolean isStaff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if (ec.isUserInRole("staff")) {
            return true;
        }
        return false;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(customer.getEmail(), customer.getPassword());
            customer = lookUpDataBeanRemote().getCustomer(customer.getEmail());
            valid = true;
        } catch (ServletException e) {
            valid = false;
            return FAILURE;
        }
        return SUCCESS;
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout fehlgeschlagen."));
        } finally {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        }
        return "/login.xhtml";
    }

}

    
    

