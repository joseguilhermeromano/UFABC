/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ifsp.lds.dao;


import br.com.ifsp.lds.beans.Justificativa;
import br.com.ifsp.lds.controller.Controller;
import br.com.ifsp.lds.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Aluno
 */
public class JustificativaDAO implements DAO{
    private EntityManager entityManager = new JPAUtil().getEntityManager();
    
    public boolean Cadastrar(Object obj) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        }catch(Exception ex){
            System.err.println(ex);
            //Logger.getLogger(JustificativaDAO.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    
    @Override
    public Justificativa Consultar(int codigo) {
        Justificativa justificativa = entityManager.find(Justificativa.class, codigo);
        return justificativa;
    }

    @Override
    public ArrayList ConsultarTudo(String string) {
        Query query = entityManager.createQuery("SELECT * FROM Justificativa", Justificativa.class);
        ArrayList<Justificativa> lista = (ArrayList<Justificativa>) query.getResultList();
        return lista;
    }

    @Override
    public boolean Alterar(Object obj) {
          try { 
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex){ 
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }
    
    public ArrayList consultarUltimas() {
        Query query = entityManager.createQuery("SELECT j FROM Justificativa j ORDER BY j.codigo DESC");
        ArrayList<Justificativa> justificativas = (ArrayList<Justificativa>) query.setMaxResults(5).getResultList();
        return justificativas;
    }
    
    
    /**
     * Consulta a última justificativa realizada pelo colaborador
     * @return um objeto do tipo Justificativa
     */
    public Justificativa ultimaJustificativaColaborador(int codigo){
        try{
            Query query = entityManager.createQuery("SELECT j FROM Justificativa j where j.falta.alocacao.usuario.codigo="+codigo
                    +" and MONTH(j.data) = MONTH(now()) and YEAR(j.data) = YEAR(now())");
            Justificativa justificativa = (Justificativa) query.getSingleResult();
            return justificativa;
        }catch(Exception ex){
            return null;
        }
    }
    
    
    @Override
    public boolean Deletar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
