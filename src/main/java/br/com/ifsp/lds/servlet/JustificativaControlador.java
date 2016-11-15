/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.beans.Justificativa;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.FaltaDAO;
import br.com.ifsp.lds.dao.JustificativaDAO;
import br.com.ifsp.lds.util.UseRules;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Aluno
 */
public class JustificativaControlador implements Tarefa {

    private static final String[] permAdmin = {""};
    private UseRules validation = new UseRules();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Part filePart = req.getPart("userfile"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.            
            //InputStream fileContent = filePart.getInputStream();
            validation.addRule("required", "codigo", req.getParameter("codigo"));
            validation.addRule("isInteger", "codigo", req.getParameter("codigo"));
            validation.addRule("validaArquivo", fileName, Integer.toString((int) filePart.getSize()));
            validation.addRule("required", "nome", req.getParameter("nome"));
            validation.addRule("required", "data", req.getParameter("data"));
            validation.addRule("isValidDate", "data", req.getParameter("data"));
            validation.addRule("required", "motivo", req.getParameter("motivo"));
            if (validation.executaRegras()) {
                //req.setAttribute("justificativa", justificadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
                FaltaDAO faltadao = null;
                HttpSession session = req.getSession();
                Justificativa justificador = null;
                Usuario user = (Usuario) session.getAttribute("usuarioLogado");
                JustificativaDAO justificaDao = new JustificativaDAO();
                SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
                justificador.setComprovante(this.readFully(filePart.getInputStream()));
                justificador.setData(data.parse(req.getParameter("dataIni")));
                justificador.setMotivodesc(req.getParameter("motivo"));
                justificador.setNome(fileName);
                justificador.setTipo(this.getExt(fileName));
                justificador.setFalta(faltadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
                justificaDao.Cadastrar(justificador);
                return "/WEB-INF/views/colaborador/faltas-colaborador.jsp";
            } else {
                req.setAttribute("erros", validation.getTodosErros());
                return "/WEB-INF/views/administrador/justificativa.jsp";
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        } catch (IOException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        } catch (ServletException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        } catch (ParseException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        }
        //return "/WEB-INF/views/colaborador/justificativa.jsp";
        //return "/WEB-INF/views/administrador/justificativa.jsp";
    }

    private String getExt(String ext) {
        int i = ext.lastIndexOf('.');
        if (i > 0) {
            return ext.substring(i + 1);
        }
        return null;
    }

    private byte[] readFully(InputStream is) throws IOException {
        int len;
        int size = 1024;
        byte[] buf;
        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1) {
                bos.write(buf, 0, len);
            }
            buf = bos.toByteArray();
        }
        return buf;
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
        JustificativaDAO justificadao = new JustificativaDAO();
        try {
            validation.addRule("required", "Id da justificativa", req.getParameter("codigo"));
            validation.addRule("isInteger", "Id da justificativa", req.getParameter("codigo"));
            if (validation.executaRegras()) {
                req.setAttribute("justificativa", justificadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(JustificativaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "/WEB-INF/views/administrador/justificativa-coordenador.jsp";
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
