/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.dao;

import br.com.ifsp.lds.beans.Justificativa;
import br.com.ifsp.lds.beans.Reposicao;
import br.com.ifsp.lds.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Luiz Felipe
 */
public class ReposicaoDAO implements DAO {
    
    private EntityManager entityManager = new JPAUtil().getEntityManager();
    
    @Override
    public boolean Cadastrar(Object obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch(Exception ex) {
            System.err.println(ex);
            return false;
        }
    }

    @Override
    public ArrayList ConsultarTudo(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object Consultar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Alterar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Deletar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Consulta as ultimas 5 reposições
     * @return uma lista de reposições
     */
    public List<Reposicao> consultarUltimas() {
        Query query = entityManager.createQuery("SELECT r FROM Reposicao r ORDER BY r.codigo DESC");
        return query.setMaxResults(5).getResultList();
    }
    
    
    /**
     * Consulta a última reposição atribuída ao colaborador
     * @return um objeto do tipo Reposição
     */
    public Reposicao ultimaReposicaoColaborador(int codigo){
        try{
            Query query = entityManager.createQuery("SELECT r FROM Reposicao r where r.falta.alocacao.usuario.codigo="+codigo
                    +" and MONTH(r.data) = MONTH(now()) and YEAR(r.data) = YEAR(now())");
            Reposicao reposicao = (Reposicao) query.getSingleResult();
            return reposicao;
        }catch(Exception ex){
            return null;
        }
    }
    
    
}
