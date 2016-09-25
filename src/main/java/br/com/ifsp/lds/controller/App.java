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
    private String segmento;
    
    public App(HttpServletRequest req) throws URISyntaxException {
        this.segments = new URI(req.getRequestURI()).getPath().split("/");
        this.setClasse();
        this.setMetodo();
        this.setSegmento();
    }
    
    private void setSegmento() {
        this.segmento = this.segments[1];
    }
    
    public String getSegmento() {
        return this.segmento;
    }
    
    private void setClasse() {
        this.classe = this.segments[this.segments.length-2];
        this.classe = this.formataNomeClasse();
    }
    
    private String formataNomeClasse() {
        return this.classe.substring(0,1).toUpperCase() 
                + this.classe.substring(1);
    }
    
    private void setMetodo() {
        this.metodo = this.segments[this.segments.length-1];
    }
    
    public String getMetodo() {
        return this.metodo;
    }
    
    public String getClasse() {
        return this.classe;
    }
}
