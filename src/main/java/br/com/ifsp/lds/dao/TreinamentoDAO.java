/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.dao;

import br.com.ifsp.lds.beans.Treinamento;
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
public class TreinamentoDAO implements DAO<Treinamento> {
    
    EntityManager entityManager = new JPAUtil().getEntityManager();
    
    
    //Método cadastra treinamento
    @Override
    public void Cadastrar(Treinamento treina,HttpServletRequest req, HttpServletResponse resp) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(treina);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    //Método que atualiza as informações de treinamento
    @Override
    public void Alterar(Treinamento obj,HttpServletRequest req, HttpServletResponse resp){
        try { 
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
        } catch (Exception ex) { 
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    
    //Método consulta todos os treinametos cadastrados
    @Override
    public ArrayList<Treinamento> ConsultarTudo(String string,HttpServletRequest req, HttpServletResponse resp){
        try { 
            entityManager.getTransaction().begin();
            TypedQuery<Treinamento> query;
            if(string.equals("")){
                query = entityManager.createQuery("select t from Treinamento t",Treinamento.class);
            }else{
                query = (TypedQuery<Treinamento>) entityManager.createQuery("select t from Treinamento t where t.nome like '%"+string+"%'");
            }
            ArrayList<Treinamento> cont = (ArrayList<Treinamento>) query.getResultList();
            entityManager.getTransaction().commit();
            return cont;
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        
        return null;
    }
     
     
    //Método consulta treinamento específico 
    @Override
    public Treinamento Consultar(int codigo,HttpServletRequest req, HttpServletResponse resp) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        Treinamento treina = entityManager.find(Treinamento.class, codigo);
        return treina;
    } 
     
    
    //Método deleta treinamento específico
    @Override
    public void Deletar(int codigo,HttpServletRequest req, HttpServletResponse resp) {      
        try { 
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select t from Treinamento t where t.codigo=" + codigo);
            Treinamento obj = (Treinamento) query.getResultList().get(0); 
            entityManager.remove(obj); 
            entityManager.getTransaction().commit();
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
    }
    
}
