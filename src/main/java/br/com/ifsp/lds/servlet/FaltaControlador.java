/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Alocacao;
import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.beans.Justificativa;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.AlocacaoDAO;
import br.com.ifsp.lds.dao.FaltaDAO;
import br.com.ifsp.lds.dao.UsuarioDAO;
import br.com.ifsp.lds.util.UseRules;
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
public class FaltaControlador implements Tarefa {

    private static final String[] permAdmin = {"cadastrar","listartudo","excluir","alterar","buscar"};

    private FaltaDAO faltaDAO = new FaltaDAO();
    private ArrayList<Falta> faltas;
    private UseRules validation = new UseRules();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        int codigoAlocacao = Integer.parseInt(req.getParameter("codigoAlocacao"));
        Alocacao alocacao = new AlocacaoDAO().Consultar(codigoAlocacao);
        req.setAttribute("alocacao", alocacao);

        if (req.getParameter("cadastrar") != null) {
            try {
                validation.addRule("required", "data", req.getParameter("data"));
                if (validation.executaRegras()) {
                    SimpleDateFormat dtFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Falta falta = new Falta();
                    falta.setStatus(0);
                    falta.setData(dtFormat.parse(req.getParameter("data")));
                    falta.setAlocacao(alocacao);
                    if (faltaDAO.Cadastrar(falta)) {
                        req.setAttribute("sucesso", "Falta cadastrada com sucesso!");
                        return listartudo(req, resp);
                    } else {
                        req.setAttribute("erro", "Não foi possível cadastrar a falta");
                        return "/WEB-INF/views/administrador/nova-falta.jsp";
                    }
                } else {
                    req.setAttribute("erro", "Não foi possível cadastrar a falta");
                    req.setAttribute("erros", validation.getTodosErros());
                    return "/WEB-INF/views/administrador/nova-falta.jsp";
                }
            } catch (Exception e) {
                Logger.getLogger(FaltaControlador.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return "/WEB-INF/views/administrador/nova-falta.jsp";
    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        usuario = new UsuarioDAO().Consultar(usuario.getCodigo());
        req.setAttribute("usuario", usuario);
        faltas = new ArrayList<>();
        
        if (usuario.getAdministrador() != 1) {
            for (Alocacao a : usuario.getAlocacoes()) {
                for (Falta f : a.getFaltas()) {
                    faltas.add(f);
                }
            }
            req.setAttribute("listaFaltas", faltas);
            return "/WEB-INF/views/colaborador/faltas-colaborador.jsp";
        }

        faltas = faltaDAO.ConsultarTudo("");
        req.setAttribute("listaFaltas", faltas);
        return "/WEB-INF/views/administrador/faltas.jsp";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        return "/WEB-INF/views/colaborador/faltas-colaborador.jsp";

    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        return "/erro404.jsp";
    }

}
