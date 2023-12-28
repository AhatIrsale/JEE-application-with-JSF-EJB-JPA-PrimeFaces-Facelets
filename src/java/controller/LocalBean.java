package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeBean")
@SessionScoped
public class LocalBean {
    
    private String locale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();

    public String getLocale() {
        return locale;
    }

     public void setLocale(String newLocale) {
        locale = newLocale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocale));
    }
     
    public String changeLocale(String newLocale) {
        locale = newLocale;
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocale));
        return null;
    }
    
}
