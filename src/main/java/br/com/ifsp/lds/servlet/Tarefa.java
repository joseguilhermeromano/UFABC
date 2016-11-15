/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luiz Felipe
 */
public interface Tarefa {  	
    /**
     * Retorna as todas as permissões de administrador 
     * associadas a determinada classe
     * @param req
     * @param resp
     * @return permissões 
     */
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp);
    
    /**
     * Efetua todo o procedimento para cadastrar determinado objeto no sistema, 
     * persistindo as informações em uma base de dados, além de retornar a pagina de 
     * acordo com o sucesso ou fracasso do procedimento.
     * @param req
     * @param resp
     * @return pagina para redirecionamento
     */
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp);
    
    
    /**
     * Recebe todos os valores que serão alterados em determinado objeto e os
     * registram em uma base de dados 
     * @param req
     * @param resp
     * @return pagina para redirecionamento
     */
    public String alterar(HttpServletRequest req, HttpServletResponse resp);
    
    /**
     * Efetua o procedimento para buscar uma lista de objetos na base de dados
     * e os exibe na pagina de retorno
     * @param req
     * @param resp
     * @return pagina para redirecionamento
     */
    public String listartudo(HttpServletRequest req, HttpServletResponse resp);
    
    /**
     * Efetua a busca de determinado objeto na base de dados.
     * @param req
     * @param resp
     * @return pagina para redirecionamento
     */
    public String buscar(HttpServletRequest req, HttpServletResponse resp);
    
    /**
     * Efetua o procedimento para excluir determinado objeto na base de dados.
     * @param req
     * @param resp
     * @return pagina para redirecionamento
     */
    public String excluir(HttpServletRequest req, HttpServletResponse resp);
}
