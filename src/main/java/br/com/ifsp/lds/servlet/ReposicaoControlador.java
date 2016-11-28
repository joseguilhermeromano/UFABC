/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.beans.Reposicao;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.FaltaDAO;
import br.com.ifsp.lds.dao.ReposicaoDAO;
import br.com.ifsp.lds.dao.UsuarioDAO;
import br.com.ifsp.lds.util.UseRules;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rafin
 */
public class ReposicaoControlador implements Tarefa {
    
    private UseRules validation = new UseRules();
    private ReposicaoDAO reposicaoDao = new ReposicaoDAO();
    
    public String indicar(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session =  req.getSession();
        ArrayList<Usuario> userList = new UsuarioDAO().ConsultarTudo("");
        String users =  new UsuarioDAO().preparaUsuarioSelect(userList, (String)session.getAttribute("baseURL"));
        req.setAttribute("options", users);
        return "/WEB-INF/views/colaborador/indicar-colaborador.jsp";
    }
    
    private static final String[] permAdmin = {"aceitarecusa"};
    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
       return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        Falta falta = new FaltaDAO().Consultar(codigo);
        req.setAttribute("falta", falta);
        
        req.setAttribute("colaboradores", new UsuarioDAO().consultarColaboradores());
        
        
        if(req.getParameter("cadastrar") != null) {
            try {
                validation.addRule("required", "data", req.getParameter("data"));
                validation.addRule("required", "data", req.getParameter("horaInicio"));
                validation.addRule("required", "data", req.getParameter("horaFim"));
                if(validation.executaRegras()) {
                    Reposicao reposicao = new Reposicao();
                    SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat tm = new SimpleDateFormat("HH:mm");
                    Time horaInicio = new Time(tm.parse(req.getParameter("horaInicio")).getTime());
                    Time horaFim = new Time(tm.parse(req.getParameter("horaFim")).getTime());
                    reposicao.setData(dataFormat.parse(req.getParameter("data")));
                    reposicao.setStatus(0);
                    reposicao.setHoraInicio(horaInicio);
                    reposicao.setHoraFim(horaFim);
                    Usuario usuario;
                    if(req.getParameter("codigoColaborador") != null) {
                        usuario = new UsuarioDAO().Consultar(Integer.
                                parseInt(req.getParameter("codigoColaborador")));
                        reposicao.setResponsavelReposicao(usuario);
                        
                    }
                    FaltaDAO faltaDao = new FaltaDAO();
                    reposicao.setFalta(faltaDao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
                    falta.setReposicao(reposicao);
                    faltaDao.Alterar(falta);
                    if(reposicaoDao.Cadastrar(reposicao)) {
                        req.setAttribute("sucesso", "reposição cadastrada com sucesso");
                        return new FaltaControlador().listartudo(req, resp);
                    } else {
                        req.setAttribute("erro", "Não foi possivel cadastrar a reposição!");
                    }
                } else {
                    req.setAttribute("erro", "Não foi possivel cadastrar a reposição!");
                    req.setAttribute("erros", validation.getTodosErros());
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ReposicaoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return "/WEB-INF/views/colaborador/nova-reposicao.jsp";
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
