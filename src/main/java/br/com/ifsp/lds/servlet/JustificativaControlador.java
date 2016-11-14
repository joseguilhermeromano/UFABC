/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.dao.JustificativaDAO;
import br.com.ifsp.lds.util.UseRules;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            JustificativaDAO justificadao = new JustificativaDAO();
            Part filePart = req.getPart("userfile"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
        
            
            //InputStream fileContent = filePart.getInputStream();
            validation.addRule("validaArquivo", filePart.getContentType(), Integer.toString((int) filePart.getSize()));
            validation.addRule("required", "Id da justificativa", req.getParameter("nome"));
            if (validation.executaRegras()) {
                //req.setAttribute("justificativa", justificadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
              
                System.out.println("Sanctify yourself");
            }else{
                  System.out.println("You should not pass !!");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(JustificativaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JustificativaControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(JustificativaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "fdsssssssssssssssssssssssssssssssss";
        //return "/WEB-INF/views/administrador/justificativa.jsp";
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

        return "/WEB-INF/views/administrador/justificativa.jsp";
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
