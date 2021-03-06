/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.dao;

import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.util.JPAUtil;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eddie
 */
public class FaltaDAO implements DAO<Falta>{

    EntityManager entityManager = new JPAUtil().getEntityManager();
    
    @Override
    public boolean Cadastrar(Falta obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public ArrayList<Falta> ConsultarTudo(String string) {
        try {  
            entityManager.getTransaction().begin();
            TypedQuery<Falta> query = null;            
            query = entityManager.createQuery("select f from Falta f",Falta.class);
            ArrayList<Falta> faltaResult = (ArrayList<Falta>) query.getResultList();
            entityManager.getTransaction().commit();
            return faltaResult;
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        
        return null;
    }

    @Override
    public Falta Consultar(int codigo) {
        Falta falta = entityManager.find(Falta.class, codigo);
        return falta;
    }
    
    public ArrayList<Falta> consultaFaltasColaborador(int codigo){
         try {  
            entityManager.getTransaction().begin();
            TypedQuery<Falta> query = null;            
            query = entityManager.createQuery("select f from Falta f where f.alocacao.usuario.codigo="+codigo+" order by f.codigo desc",Falta.class);
            ArrayList<Falta> faltaResult = (ArrayList<Falta>) query.setMaxResults(5).getResultList();
            entityManager.getTransaction().commit();
            return faltaResult;
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        
        return null;
    }

    @Override
    public boolean Alterar(Falta obj) {
     try {
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public boolean Deletar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
