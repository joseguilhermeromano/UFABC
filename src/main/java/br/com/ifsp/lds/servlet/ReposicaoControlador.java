/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.UsuarioDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafin
 */
public class ReposicaoControlador implements Tarefa {
    public String indicar(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session =  req.getSession();
        ArrayList<Usuario> userList = new UsuarioDAO().ConsultarTudo("");
        String users =  new UsuarioDAO().preparaUsuarioSelect(userList, (String)session.getAttribute("baseURL"));
        req.setAttribute("options", users);
        return "/WEB-INF/views/colaborador/indicar-colaborador.jsp";
    }
    private static final String[] permAdmin = {"aceitarecusa"};
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
