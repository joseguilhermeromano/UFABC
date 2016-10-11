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
    /*
    * @permAdmin É um Map estático utilizado para especificar 
    * os nomes dos métodos de classes de models que são permitidos 
    * apenas para usuários que são administradores do sistema.
    */
    private static final String[] permAdmin = {""};
    
    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp){
        return this.permAdmin;
    }
    
    public String inicio(HttpServletRequest req, HttpServletResponse resp){
        return "/WEB-INF/views/administrador/index.jsp";
    }
    
    public String perfil(HttpServletRequest req, HttpServletResponse resp){
        return "/WEB-INF/views/administrador/perfil.jsp";
    }
    
    public String novousuario(HttpServletRequest req, HttpServletResponse resp){
        return "/WEB-INF/views/administrador/novo-usuario.jsp";
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
            
            return "/WEB-INF/views/" +"administrador" +"/index.jsp";
        }
        return "/index.jsp";
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        Usuario usuario = new Usuario();
        
        return "/WEB-INF/views/administrador/usuarios.jsp";
    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/usuarios.jsp";
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
