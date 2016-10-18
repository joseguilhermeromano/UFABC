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
import java.util.ArrayList;
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
    private TreinamentoDAO daoTreino = new TreinamentoDAO();
    private  ArrayList<Treinamento> treinamentos;
    private UseRules validation = new UseRules();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }
    
    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameterMap().isEmpty()){
            return "/WEB-INF/views/administrador/novo-treinamento.jsp";
        }
        Treinamento treinamento = new Treinamento();
        
        try {
            //primeiro campo é a regra, segundo é o nome da coluna que vai aparacer para o usuarios 
            //, terceiro é o valor
            validation.addRule("required", "nome", req.getParameter("nome"));
            validation.addRule("required", "descricao", req.getParameter("descricao"));

            if (validation.executaRegras()) {
                treinamento.setNome(req.getParameter("nome"));
                treinamento.setDescricao(req.getParameter("descricao"));
                daoTreino.Cadastrar(treinamento,req,resp);
                return "/WEB-INF/views/administrador/novo-treinamento.jsp";
            } else {
                List<String> erros = validation.getTodosErros();
                req.setAttribute("erros", erros);
                for (String temp : erros) {
                    System.out.println(temp);
                }
            }
        
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/WEB-INF/views/administrador/novo-treinamento.jsp";
    }
    
    public String alocacao(HttpServletRequest req, HttpServletResponse resp){
        return "/WEB-INF/views/administrador/edita-alocacao.jsp";
    }
    
    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("codigo")!=null&&req.getParameterMap().size()==1){
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            req.setAttribute("treinamento",daoTreino.Consultar(codigo,req,resp));
            return "/WEB-INF/views/administrador/edita-treinamento.jsp";
        }
        Treinamento treinamento = (Treinamento) daoTreino.Consultar(Integer.parseInt(req.getParameter("codigo")),req,resp);

        try {
            //primeiro campo é a regra, segundo é o nome da coluna que vai aparacer para o usuarios 
            //, terceiro é o valor
            validation.addRule("required", "nome", req.getParameter("nome"));
            validation.addRule("required", "descricao", req.getParameter("descricao"));

            if (validation.executaRegras()) {
                treinamento.setNome(req.getParameter("nome"));
                treinamento.setDescricao(req.getParameter("descricao"));
                daoTreino.Alterar(treinamento,req,resp);
                req.setAttribute("treinamento", treinamento);
                return "/WEB-INF/views/administrador/edita-treinamento.jsp";
            } else {
                List<String> erros = validation.getTodosErros();
                req.setAttribute("erros", erros);
                for (String temp : erros) {
                    System.out.println(temp);
                }
            }
        
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/WEB-INF/views/administrador/edita-treinamento.jsp";
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        treinamentos = daoTreino.ConsultarTudo("",req,resp);
        req.setAttribute("itens", treinamentos);
        return "/WEB-INF/views/administrador/treinamentos.jsp";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("itens",daoTreino.ConsultarTudo(req.getParameter("busca"),req,resp));
        return "/WEB-INF/views/administrador/treinamentos.jsp";
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) { 
        daoTreino.Deletar(Integer.parseInt(req.getParameter("codigo")),req,resp);
        return listartudo(req,resp);
    }

}
