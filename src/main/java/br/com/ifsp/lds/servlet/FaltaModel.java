/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.dao.FaltaDAO;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eddie
 */
public class FaltaModel implements Tarefa{
     private static final String[] permAdmin = {"listartudo"};
    
    private FaltaDAO faltaDAO = new FaltaDAO();
    private ArrayList<Falta> faltas;
    
    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
       /*faltas = faltaDAO.ConsultarTudo("",req,resp);
        req.setAttribute("listaFaltas", faltas);*/
        return "/WEB-INF/views/administrador/faltas.jsp";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
       int codigo = Integer.parseInt(req.getParameter("codigo"));
       return "/WEB-INF/views/colaborador/faltas-colaborador.jsp";
    
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
