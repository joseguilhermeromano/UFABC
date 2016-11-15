
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eddie
 */
public class AlocacaoControlador implements Tarefa {
     private static final String[] permAdmin = {"cadastrar"};
     private UseRules validation = new UseRules();
     private AlocacaoDAO alocaDAO = new AlocacaoDAO();
     
    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        buscacolabtreino(req,resp);
        if(req.getParameterMap().isEmpty()){
            return "/WEB-INF/views/administrador/nova-alocacao.jsp";
        }
        try {
            validation.addRule("required", "dataIni", req.getParameter("dataIni"));
            validation.addRule("required", "dataFin", req.getParameter("dataFin"));
            validation.addRule("required", "horaIni", req.getParameter("horaIni"));
            validation.addRule("required", "horaTerm", req.getParameter("horaTerm"));
            validation.addRule("required", "codTreina", req.getParameter("codTreina"));
            validation.addRule("required", "codCol", req.getParameter("codCol"));
            validation.addRule("required", "dataIni", req.getParameter("dataIni"));
           // validation.addRule("required", "seg", req.getParameter("diaSemana"));
            if (validation.executaRegras()) {
               Alocacao aloca = new Alocacao();
               SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
               SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
               Time timeini = new Time(tm.parse(req.getParameter("horaIni")).getTime());
               Time timefin = new Time(tm.parse(req.getParameter("horaTerm")).getTime());
               aloca.setTreinamento(new TreinamentoDAO().Consultar(Integer.parseInt(req.getParameter("codTreina"))));
               aloca.setUsuarios(new UsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codCol"))));
               aloca.setDatainicio(data.parse(req.getParameter("dataIni")));
               aloca.setDatafinal(data.parse(req.getParameter("dataFin")));
               aloca.setHorainicio(timeini);
               aloca.setHorafim(timefin);
               aloca.setSegunda(req.getParameter("seg")!= null ? true : false);
               aloca.setTerca(req.getParameter("ter")!= null ? true : false);
               aloca.setQuarta(req.getParameter("qua")!= null ? true : false);
               aloca.setQuinta(req.getParameter("qui")!= null ? true : false);
               aloca.setSexta(req.getParameter("sex")!= null ? true : false);
               aloca.setSabado(req.getParameter("sab")!= null ? true : false);
               if(alocaDAO.Cadastrar(aloca)==true){
                   req.setAttribute("sucesso", "A Alocação foi realizada com sucesso!");
               }else{
                   req.setAttribute("erro", "Não foi possível realizar a Alocação!");
               }
               listartudo(req,resp);
               return "/WEB-INF/views/alocacoes.jsp";
            }else {
                List<String> erros = validation.getTodosErros();
                req.setAttribute("erros", erros);
                for (String temp : erros) {
                    System.out.println(temp);
                }
            }        
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TreinamentoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }         catch (ParseException ex) {
             Logger.getLogger(AlocacaoControlador.class.getName()).log(Level.SEVERE, null, ex);
         }

        return "/WEB-INF/views/administrador/nova-alocacao.jsp";
        }
    

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        buscacolabtreino(req,resp);
        if(req.getParameter("codigo")!=null && req.getParameterMap().size()==1){
            int codigo = Integer.parseInt(req.getParameter("codigo"));
            req.setAttribute("alocacao",alocaDAO.Consultar(codigo));
            return "/WEB-INF/views/administrador/edita-alocacao.jsp";
        }
        try {
            validation.addRule("required", "dataIni", req.getParameter("dataIni"));
            validation.addRule("required", "dataFin", req.getParameter("dataFin"));
            validation.addRule("required", "horaIni", req.getParameter("horaIni"));
            validation.addRule("required", "horaTerm", req.getParameter("horaTerm"));
            validation.addRule("required", "codTreina", req.getParameter("codTreina"));
            validation.addRule("required", "codCol", req.getParameter("codCol"));
            validation.addRule("required", "dataIni", req.getParameter("dataIni"));
           // validation.addRule("required", "seg", req.getParameter("diaSemana"));
            if (validation.executaRegras()) {
               Alocacao aloca = (Alocacao) alocaDAO.Consultar(Integer.parseInt(req.getParameter("codigo")));
               SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
               SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
               Date pegahoraini = tm.parse(req.getParameter("horaIni"));
               Date pegahorafin = tm.parse(req.getParameter("horaTerm"));
               aloca.setTreinamento(new TreinamentoDAO().Consultar(Integer.parseInt(req.getParameter("codTreina"))));
               aloca.setUsuarios(new UsuarioDAO().Consultar(Integer.parseInt(req.getParameter("codCol"))));
               aloca.setDatainicio(data.parse(req.getParameter("dataIni")));
               aloca.setDatafinal(data.parse(req.getParameter("dataFin")));
               Time timeini = new Time(pegahoraini.getTime());
               Time timefin = new Time(pegahorafin.getTime());
               aloca.setHorainicio(timeini);
               aloca.setHorafim(timefin);
               aloca.setSegunda(req.getParameter("seg")!= null ? true : false);
               aloca.setTerca(req.getParameter("ter")!= null ? true : false);
               aloca.setQuarta(req.getParameter("qua")!= null ? true : false);
               aloca.setQuinta(req.getParameter("qui")!= null ? true : false);
               aloca.setSexta(req.getParameter("sex")!= null ? true : false);
               aloca.setSabado(req.getParameter("sab")!= null ? true : false);
               if(alocaDAO.Alterar(aloca)==true){
                   req.setAttribute("sucesso", "A Alocação foi atualizada com sucesso!");
               }else{
                   req.setAttribute("erro", "Não foi possível atualizar a Alocação!");
               }
               listartudo(req,resp);
               return "/WEB-INF/views/alocacoes.jsp";
            }else {
                List<String> erros = validation.getTodosErros();
                req.setAttribute("erros", erros);
                for (String temp : erros) {
                    System.out.println(temp);
                }
            }        
        } catch (NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(TreinamentoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }         catch (ParseException ex) {
             Logger.getLogger(AlocacaoControlador.class.getName()).log(Level.SEVERE, null, ex);
         }

        return "/WEB-INF/views/administrador/edita-alocacao.jsp";
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        buscacolabtreino(req,resp);
        ArrayList alocacoes = (ArrayList<Alocacao>) alocaDAO.ConsultarTudo("");
        req.setAttribute("alocacoes", alocacoes);
        return "/WEB-INF/views/alocacoes.jsp";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        
        ArrayList<Alocacao> busca = new ArrayList<Alocacao>();
        ArrayList<Alocacao> alocacoes = new ArrayList<Alocacao>();
        alocacoes = (ArrayList<Alocacao>) alocaDAO.ConsultarTudo("");
        int colaborador = req.getParameter("codCol").equals("")?0:Integer.parseInt(req.getParameter("codCol"));
        int treinamento = req.getParameter("codTreina").equals("")?0:Integer.parseInt(req.getParameter("codTreina"));
       
        
        if(colaborador>0 && treinamento >0){
            for(Alocacao a : alocacoes){
                if(a.getUsuario().getCodigo()==colaborador
                        &&a.getTreinamento().getCodigo()==treinamento){
                    busca.add(a);
                }
            }
        }else if(colaborador > 0 && treinamento ==0){
            for(Alocacao a : alocacoes){
                if(a.getUsuario().getCodigo()==colaborador){
                    busca.add(a);
                }
            }
        }else if(colaborador == 0 && treinamento > 0){
            for(Alocacao a : alocacoes){
                if(a.getTreinamento().getCodigo()==treinamento){
                    busca.add(a);
                }
            }
        }else{
            req.setAttribute("erro", "Não foi selecionado nenhum colaborador ou treinamento para a busca!");
            listartudo(req,resp);
            buscacolabtreino(req,resp);
            return "/WEB-INF/views/alocacoes.jsp";
        }
        buscacolabtreino(req,resp);
        if(!busca.isEmpty()){
            req.setAttribute("alocacoes", busca);
        }else{
            req.setAttribute("erro", "Não foram encontrados resultados para a sua busca!");
        }
        return "/WEB-INF/views/alocacoes.jsp";
    }
    
    /**
     * Efetua o procedimento para consultar todos os treinamentos e usuarios
     * e os registram na requisição
     * @param req
     * @param resp 
     */
    public void buscacolabtreino(HttpServletRequest req, HttpServletResponse resp){
        UsuarioDAO userdao = new UsuarioDAO();
        TreinamentoDAO treinodao= new TreinamentoDAO();
        req.setAttribute("usuarios", userdao.ConsultarTudo(""));
        req.setAttribute("treinamentos", treinodao.ConsultarTudo(""));
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        if(alocaDAO.Deletar(Integer.parseInt(req.getParameter("codigo")))==true){
            req.setAttribute("sucesso", "A Alocação foi excluída com sucesso!");
        }else{
            req.setAttribute("erro", "Não foi possível excluir a Alocação!");
        }
        return listartudo(req,resp);
    }
    
}
