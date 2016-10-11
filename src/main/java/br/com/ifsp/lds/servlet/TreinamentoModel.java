/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Treinamento;
import br.com.ifsp.lds.beans.Usuario;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiz Felipe
 */
public class TreinamentoModel implements Tarefa {
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
    
    public String novotreinamento(HttpServletRequest req, HttpServletResponse resp){
        return "/WEB-INF/views/administrador/novo-treinamento.jsp";
    }
    
    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        Treinamento treinamento = new Treinamento();
        
        treinamento.setDescricao(req.getParameter("descricao"));
//        treinamento.setDatainicio(new Date(req.getParameter("dataTerm")));
//        treinamento.setDatafim(new Date(req.getParameter("dataIni")));
//        treinamento.setHora(new Date(req.getParameter("hora")));
        treinamento.setTurma(req.getParameter("turma"));
        Usuario usuario = new Usuario();
        usuario.setCodigo(Integer.parseInt(req.getParameter("resp")));
        treinamento.setUsuario(usuario);
        
        
        return "/WEB-INF/views/administrador/novo-treinamento.jsp";
        
    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/treinamentos.jsp";
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
