/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.UsuarioDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luiz Felipe
 */
public class UsuarioModel implements Tarefa {
    
    @Override
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/index.jsp";
    }
    
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        String senha = req.getParameter("senha");
        String login = req.getParameter("username");
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.buscaUsuario(login);
        if(usuario != null && usuario.getSenha().equals(senha)) {
            HttpSession session =  req.getSession();
            session.setAttribute("sessaoUsuario", usuario);
            String segmento;
            if(usuario.getAdministrador() == 1) 
                segmento = "administrador";
            else 
                segmento = "colaborador";
            
            return "/WEB-INF/views/" + segmento +"/index.jsp";
        }
        return "/index.jsp";
    }
    
}
