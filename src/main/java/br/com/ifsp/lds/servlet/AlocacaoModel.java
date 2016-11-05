
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Alocacao;
import br.com.ifsp.lds.beans.Treinamento;
import br.com.ifsp.lds.dao.AlocacaoDAO;
import br.com.ifsp.lds.dao.TreinamentoDAO;
import br.com.ifsp.lds.dao.UsuarioDAO;
import br.com.ifsp.lds.util.UseRules;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        if(req.getParameterMap().isEmpty()){
            return "/WEB-INF/views/administrador/alocacao.jsp";
        }
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
               SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
               DateFormat tm = new SimpleDateFormat("HH:mm");
               aloca.setTreinamento(new TreinamentoDAO().Consultar(Integer.parseInt(req.getParameter("codTreina")), req, resp));
               aloca.setUsuarios(new UsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codCol")), req, resp));
               aloca.setDatainicio(df.parse(req.getParameter("dataIni")));
               aloca.setDatafinal(df.parse(req.getParameter("dataFin")));
               aloca.setHorainicio(new Time(tm.parse(req.getParameter("horaIni")).getTime()));
               aloca.setHorafim(new Time(tm.parse(req.getParameter("horaFin")).getTime()));
               aloca.setSegunda(Boolean.parseBoolean(req.getParameter("seg")));
               aloca.setTerca(Boolean.parseBoolean(req.getParameter("ter")));
               aloca.setQuarta(Boolean.parseBoolean(req.getParameter("qua")));
               aloca.setQuinta(Boolean.parseBoolean(req.getParameter("qui")));
               aloca.setSexta(Boolean.parseBoolean(req.getParameter("sex")));
               aloca.setSabado(Boolean.parseBoolean(req.getParameter("sab")));
               alocaDAO.Cadastrar(aloca, req, resp);
               return "/WEB-INF/views/alocacoes.jsp";
            }else {
                List<String> erros = validation.getTodosErros();
                req.setAttribute("erros", erros);
                for (String temp : erros) {
                    System.out.println(temp);
                }
            }        
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TreinamentoModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
             Logger.getLogger(AlocacaoModel.class.getName()).log(Level.SEVERE, null, ex);
         }

        return "/WEB-INF/views/administrador/alocacao.jsp";
        }
    

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/alocacoes.jsp";
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
