/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.controller;

import br.com.ifsp.lds.servlet.Tarefa;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author eddie
 */
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String classe = req.getParameter("classe");
        String metodo = req.getParameter("metodo");
        String pagina;
        
        try {
        
            Class<?> tipo = Class.forName(classe);
            Tarefa instancia = (Tarefa) tipo.newInstance();
            Method method = tipo.getDeclaredMethod(metodo, HttpServletRequest.class, HttpServletResponse.class);
            pagina = (String) method.invoke(instancia, req, resp);
            req.getRequestDispatcher(pagina).forward(req, resp);
        
        } catch (NoClassDefFoundError | NoSuchMethodError ex) {
            //chamar a pagina 404.
            System.out.println(ex);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
