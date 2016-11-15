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
    
    /**
     * Array que guarda todos os elemetos da URL
     */
    private String segments[];
    private String classe;
    private String metodo = "index";
    
    
    public App(HttpServletRequest req) throws URISyntaxException {
        this.segments = new URI(req.getRequestURI()).getPath().substring(1).split("/");
        this.setClasse();
        this.setMetodo();
    }
    
    
    /**
     * Define qual elemento dentro do array segments 
     * deve ser considerado como nome de uma classe
     */
    private void setClasse() {
        this.classe = this.segments[1].toLowerCase();
        if(this.segments.length <= 3)
            this.classe = this.segments[1].toLowerCase();
        else if(this.segments.length > 3) {
            this.classe = this.segments[2].toLowerCase();
        }
        
        this.formataNomeClasse();
    }
    
    /**
     * Formata o nome da classe deixando o 
     * primeiro caracter da string classe em maiúsculo 
     */
    private void formataNomeClasse() {
        this.classe = this.classe.substring(0,1).toUpperCase() 
                + this.classe.substring(1);
    }
    
    /**
     * Define qual elemento presente em segments deve ser considerado como
     * o nome do método 
     */
    private void setMetodo() {
        if(this.segments.length > 3)
            this.metodo = this.segments[3];
        else if(this.segments.length == 3) {
            this.metodo = this.segments[2];
        }
    }
    
    /**
     * @return nome do método
     */
    public String getMetodo() {
        return this.metodo;
    }
    
    /**
     * 
     * @return nome da classe 
     */
    public String getClasse() {
        return this.classe;
    }
}
