/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.controller;

import br.com.ifsp.lds.servlet.Tarefa;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Guilherme
 */
public class Administrador extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String pagina="index";
        try {
            String[] segments = new URI(req.getRequestURI()).getPath().split("/");
            
                String segmento = segments[segments.length-1];
                if (!segmento.equals("administrador")){
                    pagina=segmento;
                } 
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        pagina="/WEB-INF/views/administrador/"+pagina+".jsp";
        
        req.getRequestDispatcher(pagina).forward(req, resp);
        
    }
}
