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
    
    private EntityManager entityManager = new JPAUtil().getEntityManager();
    
    
    //Método cadastra treinamento
    @Override
    public boolean Cadastrar(Treinamento treina) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(treina);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    
    //Método que atualiza as informações de treinamento
    @Override
    public boolean Alterar(Treinamento obj){
        try { 
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();

            return true;
        } catch (Exception ex) { 
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    
    //Método consulta todos os treinametos cadastrados
    @Override
    public ArrayList<Treinamento> ConsultarTudo(String string){
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
    public Treinamento Consultar(int codigo) {
        Treinamento treina = entityManager.find(Treinamento.class, codigo);
        return treina;
    } 
     
    
    //Método deleta treinamento específico
    @Override
    public boolean Deletar(int codigo) {      
        try { 
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select t from Treinamento t where t.codigo=" + codigo);
            Treinamento obj = (Treinamento) query.getResultList().get(0); 
            entityManager.remove(obj); 
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    
}
