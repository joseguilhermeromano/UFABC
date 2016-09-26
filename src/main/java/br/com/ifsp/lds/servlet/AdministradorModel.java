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
public class AdministradorModel implements Tarefa {

    @Override
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/index.jsp";
    }
    
    public String perfil(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/perfil.jsp";
    }
    
}
