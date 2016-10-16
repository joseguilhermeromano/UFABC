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

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    public String novotreinamento(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/novo-treinamento.jsp";
    }
    
    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameterMap().isEmpty()){
            return "/WEB-INF/views/administrador/novo-treinamento.jsp";
        }
        Treinamento treinamento = new Treinamento();
        UseRules validation = new UseRules();
        try {
            //primeiro campo é a regra, segundo é o nome da coluna que vai aparacer para o usuarios 
            //, terceiro é o valor
            validation.addRule("required", "nome", req.getParameter("nome"));
            validation.addRule("required", "descricao", req.getParameter("descricao"));

            if (validation.executaRegras()) {
                treinamento.setNome(req.getParameter("nome"));
                treinamento.setDescricao(req.getParameter("descricao"));
                daoTreino.Cadastrar(treinamento);
                return "/WEB-INF/views/administrador/treinamentos.jsp";
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
    
    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        treinamentos = daoTreino.ConsultarTudo("");
        req.setAttribute("itens", treinamentos);
        return "/WEB-INF/views/administrador/treinamentos.jsp";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) { 
        daoTreino.Deletar(Integer.parseInt(req.getParameter("codigo")));
        return listartudo(req,resp);
    }

}
