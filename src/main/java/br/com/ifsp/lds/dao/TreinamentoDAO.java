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

/**
 *
 * @author eddie
 */
public class TreinamentoDAO {
    
    EntityManager entityManager = new JPAUtil().getEntityManager();
    
    
    //Método cadastra treinamento
    public void Cadastrar(Treinamento treina) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(treina);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    
    
    //Método consulta todos os treinametos cadastrados 
    public ArrayList<Treinamento> Consultar(){
        try { 
            entityManager.getTransaction().begin(); 
            TypedQuery<Treinamento> query = entityManager.createQuery("select t from Treinamento t",Treinamento.class);
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
    public Treinamento Exibir(int codigo) {
        EntityManager entityManager = new JPAUtil().getEntityManager();
        Treinamento treina = entityManager.find(Treinamento.class, codigo);
        return treina;
    } 
     
    
    //Método deleta treinamento específico
    public void Deletar(int codigo) {      
        try { 
            entityManager.getTransaction().begin(); 
            Query query = entityManager.createQuery("select t from Treinamento t where t.codigo=" + codigo);
            Treinamento treina = (Treinamento) query.getResultList().get(0); 
            entityManager.remove(treina); 
            entityManager.getTransaction().commit();
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }
    
}
