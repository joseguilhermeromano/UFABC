/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.dao.JustificativaDAO;
import br.com.ifsp.lds.util.UseRules;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aluno
 */
public class JustificativaModel implements Tarefa {

    private UseRules validation = new UseRules();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        JustificativaDAO justificadao = new JustificativaDAO();
        try {
            validation.addRule("required", "Id da justificativa", req.getParameter("codigo"));
            validation.addRule("isInteger", "Id da justificativa", req.getParameter("codigo"));
            if (validation.executaRegras()) {
                req.setAttribute("justificativa", justificadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(JustificativaModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/WEB-INF/views/justificativa.jsp";
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
