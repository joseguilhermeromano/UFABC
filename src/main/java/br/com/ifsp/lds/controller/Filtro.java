/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.controller;
import br.com.ifsp.lds.beans.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Luiz Felipe
 */
public class Filtro implements Filter  {
     
    @Override
    public void destroy() {
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        
        
        HttpSession session =  req.getSession();
        if (session.getAttribute("baseURL")==null){
            /*Essa é a base da url (ex: https://localhost:8080/UFABC/) utilizada para todos os links do sistema!*/
            String baseURL = req.getRequestURL().substring(0, req.getRequestURL().length()-req.getRequestURI().length())
                +req.getContextPath()+"/";
            session.setAttribute("baseURL", baseURL);
        }
        
        chain.doFilter(request, response);
    }
    
    public boolean usuarioLogado(HttpServletRequest req){
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if(usuario == null) return false;
        return true;
    }
    
    public int isAdmin(HttpServletRequest req){
       Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        if(usuario == null) return 0;
        return usuario.getAdministrador();
    }
    
}
