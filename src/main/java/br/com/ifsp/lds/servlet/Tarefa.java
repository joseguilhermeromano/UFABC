/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiz Felipe
 */
public interface Tarefa {  	
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp);
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp);
    public String alterar(HttpServletRequest req, HttpServletResponse resp);
    public String listartudo(HttpServletRequest req, HttpServletResponse resp);
    public String buscar(HttpServletRequest req, HttpServletResponse resp);
    public String excluir(HttpServletRequest req, HttpServletResponse resp);
}
