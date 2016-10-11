/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Treinamento;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.TreinamentoDAO;
import br.com.ifsp.lds.dao.UsuarioDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
         SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
        Treinamento treinamento = new Treinamento();
        try {
            Date dataIni = formato.parse(req.getParameter("dataTerm"));
        } catch (ParseException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            treinamento.setDatafim((Date) formato.parse(req.getParameter("dataTerm")));
        } catch (ParseException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            treinamento.setDatainicio((Date) formato.parse(req.getParameter("dataIni")));
        } catch (ParseException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            treinamento.setHora((Date) horaFormato.parse(req.getParameter("hora")));
        } catch (ParseException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        treinamento.setDescricao(req.getParameter("descricao"));
        treinamento.setTurma(req.getParameter("turma"));
        treinamento.setLaboratorio(req.getParameter("lab"));
        Usuario usuario = new UsuarioDAO().Consultar(2);
        System.out.println("email:" + usuario.getEmail());
        treinamento.setUsuario(usuario);
        TreinamentoDAO daoTreino = new TreinamentoDAO();
        daoTreino.Cadastrar(treinamento);
        return "/WEB-INF/views/administrador/treinamentos.jsp";
        
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
