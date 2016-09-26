/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.controller;


import br.com.ifsp.lds.servlet.Tarefa;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

/**
 *
 * @author eddie
 */
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pagina = "/index.jsp";
        try {
            App app = new App(req);
            String metodo = "br.com.ifsp.lds.servlet." + app.getClasse() + "Model";
            
            Class<?> tipo = Class.forName(metodo);
            Tarefa instancia = (Tarefa) tipo.newInstance();
            
            Method method = tipo.getDeclaredMethod(app.getMetodo(),HttpServletRequest.class,HttpServletResponse.class);
            pagina = (String) method.invoke(instancia, req,resp);
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ClassNotFoundException | NoSuchMethodException ex) {
            
            //criar pagina para o erro 404
            
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.getRequestDispatcher(pagina).forward(req, resp);
    }
}
