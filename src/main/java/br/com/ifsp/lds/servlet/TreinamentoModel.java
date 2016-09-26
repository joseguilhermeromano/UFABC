/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiz Felipe
 */
public class TreinamentoModel implements Tarefa {

    @Override
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return this.exibe(req, resp);
    }
    
    public String exibe(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/treinamentos";
    }
    
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/novo-treinamento";
        
    }
    
    public String deletar(HttpServletRequest req, HttpServletResponse resp) {
        return "";
    }
    
    public String treinamentosDoColaborador(HttpServletRequest req, HttpServletResponse resp) {
        return "";
    }
    
    public String listar(HttpServletRequest req, HttpServletResponse resp) {
        return "";
    }
    
    
}
