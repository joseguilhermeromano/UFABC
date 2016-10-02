/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.controller;

import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Luiz Felipe
 */
public class App {
    
    private String segments[];
    private String classe;
    private String metodo = "index";
    
    
    public App(HttpServletRequest req) throws URISyntaxException {
        this.segments = new URI(req.getRequestURI()).getPath().substring(1).split("/");
        this.setClasse();
        this.setMetodo();
    }
    
    private void setClasse() {
        this.classe = this.segments[1].toLowerCase();
        if(this.segments.length <= 3)
            this.classe = this.segments[1].toLowerCase();
        else if(this.segments.length > 3) {
            this.classe = this.segments[2].toLowerCase();
        }
        
        this.formataNomeClasse();
    }
    
    private void formataNomeClasse() {
        this.classe = this.classe.substring(0,1).toUpperCase() 
                + this.classe.substring(1);
    }
    
    private void setMetodo() {
        if(this.segments.length > 3)
            this.metodo = this.segments[3];
        else if(this.segments.length == 3) {
            this.metodo = this.segments[2];
        }
    }
    
    public String getMetodo() {
        return this.metodo;
    }
    
    public String getClasse() {
        return this.classe;
    }
}
