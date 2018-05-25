/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bumblebee.ctrl;

import com.bumblebee.model.Article;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author Stefanie Langhammer
 */
@Named
@ApplicationScoped
public class ArticleCtrl extends LookUpData {
    private List<Article> articles;
    private DataBeanRemote dbr = lookUpDataBeanRemote();

    public ArticleCtrl() {
        articles = new ArrayList<>();
    }
    
    @PostConstruct
    public void fillArticles(){
    articles = lookUpDataBeanRemote().getAllArticles();
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public DataBeanRemote getDbr() {
        return dbr;
    }

    public void setDbr(DataBeanRemote dbr) {
        this.dbr = dbr;
    }
    
}
