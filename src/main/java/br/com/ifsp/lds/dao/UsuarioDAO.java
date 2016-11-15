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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eddie
 */
public class UsuarioDAO implements DAO<Usuario> {
    
    private EntityManager entityManager = new JPAUtil().getEntityManager();
    
    /**
     * Consulta o usuario de acordo com o valor de login
     * @param login
     * @return Usuario ou null
     */
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
    public boolean Cadastrar(Usuario obj) {
        try { 
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();           
            return true;
        }catch(Exception ex){
             ex.printStackTrace(); 
            entityManager.getTransaction().rollback();          
            return false;
        }
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
    public boolean Alterar(Usuario obj) {
        try { 
            entityManager.getTransaction().begin();
            entityManager.merge(obj);
            entityManager.getTransaction().commit();
//            req.setAttribute("sucesso", "Usuário atualizado com sucesso!");
            return true;
        } catch (Exception ex) { 
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
//            req.setAttribute("erro", "Não foi possível atualizar o Usuário!");
            return false;
        }
    }

    @Override
    public boolean Deletar(int codigo) {
        try { 
            entityManager.getTransaction().begin(); 
            Query query = entityManager.createQuery("select u from Usuario u where u.codigo=" + codigo);
            Usuario consulta = (Usuario) query.getResultList().get(0); 
            entityManager.remove(consulta); 
            entityManager.getTransaction().commit();          
            return true;
        } catch (Exception ex) { 
            ex.printStackTrace(); 
            entityManager.getTransaction().rollback();
            return false;
        }
    }
}
