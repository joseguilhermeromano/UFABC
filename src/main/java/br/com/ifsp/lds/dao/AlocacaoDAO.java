/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.dao;

import br.com.ifsp.lds.util.JPAUtil;
import br.com.ifsp.lds.beans.Alocacao;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author José Guilherme
 */
public class AlocacaoDAO implements DAO {
    private EntityManager entityManager = new JPAUtil().getEntityManager();

    @Override
    public void Cadastrar(Object obj, HttpServletRequest req, HttpServletResponse resp) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            entityManager.close();
            req.setAttribute("sucesso", "A Alocação foi realizada com sucesso!");
        }catch(Exception ex){
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            req.setAttribute("erro", "Não foi possível realizar a Alocação!");
        }
    }

    @Override
    public ArrayList ConsultarTudo(String string, HttpServletRequest req, HttpServletResponse resp) {
        try { 
            entityManager.getTransaction().begin();
            TypedQuery<Alocacao> query;
            if(string.equals("")){
                query = entityManager.createQuery("select a from Alocacao a",Alocacao.class);
            }else{
                query = (TypedQuery<Alocacao>) entityManager.createQuery("select a from Alocacao a where a.data like '%"+string+"%'");
            }
            ArrayList<Alocacao> cont = (ArrayList<Alocacao>) query.getResultList();
            entityManager.getTransaction().commit();
            return cont;
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        
        return null;
    }

    @Override
    public Object Consultar(int codigo, HttpServletRequest req, HttpServletResponse resp) {
        Alocacao alocacao = entityManager.find(Alocacao.class, codigo);
        return alocacao;
    }

    @Override
    public void Alterar(Object obj, HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Deletar(int codigo, HttpServletRequest req, HttpServletResponse resp) {
        try { 
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("select a from Alocacao a where a.codigo=" + codigo);
            Alocacao obj = (Alocacao) query.getResultList().get(0); 
            entityManager.remove(obj); 
            entityManager.getTransaction().commit();
            req.setAttribute("sucesso", "A Alocação foi excluída com sucesso!");
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
            req.setAttribute("erro", "Não foi possível excluir a Alocação!");
        }
    }
    
}
