/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Alocacao;
import br.com.ifsp.lds.dao.AlocacaoDAO;
import br.com.ifsp.lds.util.UseRules;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eddie
 */
public class AlocacaoModel implements Tarefa {
     private static final String[] permAdmin = {"cadastrar"};
     private UseRules validation = new UseRules();
     private AlocacaoDAO alocaDAO = new AlocacaoDAO();
     
    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        try {
            validation.addRule("required", "dataIni", req.getParameter("dataIni"));
            validation.addRule("required", "dataFin", req.getParameter("dataFin"));
            validation.addRule("required", "horaIni", req.getParameter("horaTerm"));
            validation.addRule("required", "codTreina", req.getParameter("codTreina"));
            validation.addRule("required", "codCol", req.getParameter("codCol"));
            validation.addRule("required", "dataIni", req.getParameter("dataIni"));
           // validation.addRule("required", "seg", req.getParameter("diaSemana"));
            if (validation.executaRegras()) {
               Alocacao aloca = new Alocacao(); 
               aloca.setTreinamento(Integer.parseInt(req.getParameter("codTreina")));
               aloca.setUsuarios(req.getParameter("codCol"));
               aloca.setDatainicio(req.getParameter("dataIni"));
               aloca.setDatafinal(req.getParameter("dataFin"));
               aloca.setHorainicio(req.getParameter("horaIni"));
               aloca.setHorafim(req.getParameter("horaFin"));
               aloca.setSegunda(req.getParameter("seg"));
               aloca.setTerca(req.getParameter("ter"));
               aloca.setQuarta(req.getParameter("qua"));
               aloca.setQuinta(req.getParameter("qui"));
               aloca.setSexta(req.getParameter("sex"));
               aloca.setSabado(req.getParameter("sab"));
               alocaDAO.Cadastrar(aloca, req, resp);
               return "/WEB-INF/views/administrador/alocacao.jsp";
            }else {
                List<String> erros = validation.getTodosErros();
                req.setAttribute("erros", erros);
                for (String temp : erros) {
                    System.out.println(temp);
                }
            }        
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/WEB-INF/views/administrador/alocacao.jsp";
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
