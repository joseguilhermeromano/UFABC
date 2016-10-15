/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.dao;

import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.util.JPAUtil;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author eddie
 */
public class UsuarioDAO implements DAO<Usuario> {
    
    EntityManager entityManager = new JPAUtil().getEntityManager();
    
    public Usuario buscaUsuario(String login) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM "
                    + "Usuario u WHERE u.login='"+login+"'");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public void Cadastrar(Usuario obj) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public ArrayList<Usuario> ConsultarTudo(String string) {
        try { 
            if(string.equals("")){
                entityManager.getTransaction().begin(); 
                TypedQuery<Usuario> query = entityManager.createQuery("select t from Usuario t",Usuario.class);
                ArrayList<Usuario> consulta = (ArrayList<Usuario>) query.getResultList();
                entityManager.getTransaction().commit();
                return consulta;
            }else{
                Query query = entityManager.createQuery("select u from Usuario u where u.nome='" +string+"'");
                ArrayList<Usuario> consulta = (ArrayList<Usuario>) query.getResultList();
                return consulta;
            }
            
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
        
        return null;
    }

    @Override
    public Usuario Consultar(int codigo) {
        Usuario consulta = entityManager.find(Usuario.class, codigo);
        return consulta;
    }

    @Override
    public void Alterar(Usuario obj) {
        try { 
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
        } catch (Exception ex) { 
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void Deletar(int codigo) {
        try { 
            entityManager.getTransaction().begin(); 
            Query query = entityManager.createQuery("select u from Usuario u where u.codigo" + codigo);
            Usuario consulta = (Usuario) query.getResultList().get(0); 
            entityManager.remove(consulta); 
            entityManager.getTransaction().commit();
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }
}
