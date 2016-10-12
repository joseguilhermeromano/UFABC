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
import br.com.ifsp.lds.util.FormValidation;
import br.com.ifsp.lds.util.UseRules;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    public String novotreinamento(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/novo-treinamento.jsp";
    }
    
    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat horaFormato = new SimpleDateFormat("HH:mm:ss");
        Treinamento treinamento = new Treinamento();
        UseRules validation = new UseRules();
        try {
            //primeiro campo é a regra, segundo é o nome da coluna que vai aparacer para o usuarios 
            //, terceiro é o valor
            validation.addRule("isValidDate", "data de término", req.getParameter("dataTerm"));
            validation.addRule("isValidDate", "data de inicio", req.getParameter("dataIni"));
            validation.addRule("isValidDate", "data de teste", "24/11/1996");
            validation.addRule("isInteger", "responsavel", req.getParameter("resp"));
            validation.addRule("isValidEmail", "", "rafael@hotmail.com.br");
            if (validation.executaRegras()) {
                treinamento.setDatafim((Date) formato.parse(req.getParameter("dataTerm")));
                treinamento.setDatainicio((Date) formato.parse(req.getParameter("dataIni")));
                treinamento.setHora((Date) horaFormato.parse(req.getParameter("hora")));
                treinamento.setDescricao(req.getParameter("descricao"));
                treinamento.setTurma(req.getParameter("turma"));
                treinamento.setLaboratorio(req.getParameter("lab"));
                Usuario usuario = new UsuarioDAO().Consultar(Integer.parseInt(req.getParameter("resp")));
                treinamento.setUsuario(usuario);
                TreinamentoDAO daoTreino = new TreinamentoDAO();
                daoTreino.Cadastrar(treinamento);
                return "/WEB-INF/views/administrador/treinamentos.jsp";
            } else {
                List<String> erros = validation.getTodosErros();
                for (String temp : erros) {
                    System.out.println(temp);
                }
                return "/WEB-INF/views/administrador/treinamentos.jsp";
            }
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ParseException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }

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
