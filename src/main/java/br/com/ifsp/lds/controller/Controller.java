/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.controller;


import br.com.ifsp.lds.servlet.Tarefa;
import br.com.ifsp.lds.servlet.UsuarioControlador;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author eddie
 */
public class Controller extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pagina = "/index.jsp";
        String[] permAdmin;
        boolean EstaNoArray=false;
        Filtro filtro = new Filtro();
        
        try {
            App app = new App(req);
            String classe = app.getClasse()+"Controlador";
            String metodo = app.getMetodo();
            String classname = "br.com.ifsp.lds.servlet." + classe;
            
            Class<?> tipo = Class.forName(classname);
            Tarefa instancia = (Tarefa) tipo.newInstance();
            
            Method method = tipo.getDeclaredMethod("getPermAdmin",HttpServletRequest.class,HttpServletResponse.class);
            permAdmin = (String[]) method.invoke(instancia, req,resp);
            
            method = tipo.getDeclaredMethod(metodo,HttpServletRequest.class,HttpServletResponse.class);
            
            for(String s : permAdmin){
                if (s.equals(metodo)){
                    EstaNoArray=true;
                    break;
                }
            }
            
            if (EstaNoArray==true && filtro.isAdmin(req)==false){
                //aqui deverá retornar uma página de erro.
                pagina = "/index.jsp";
            }else{
                pagina = (String) method.invoke(instancia, req,resp); 
            }
            
            
              
            
        } catch (URISyntaxException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch(ClassNotFoundException | NoSuchMethodException ex) {
            
            //criar pagina para o erro 404
            
            pagina="/erro404.jsp";
            
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pagina != null) {
            req.getRequestDispatcher(pagina).forward(req, resp);
        }
    }
}
