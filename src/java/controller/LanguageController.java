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
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;

@Named
@SessionScoped
public class LanguageController implements Serializable {
    
     private static final List<Locale> languages = Arrays.asList(Locale.ENGLISH, Locale.FRENCH);
    private Locale selectedLanguage;

    public List<Locale> getLanguages() {
        return languages;
    }

    public Locale getSelectedLanguage() {
        return selectedLanguage;
    }

    public void setSelectedLanguage(Locale selectedLanguage) {
        this.selectedLanguage = selectedLanguage;
    }

    public void changeLanguage() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(selectedLanguage);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Language Changed", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    
    
}
