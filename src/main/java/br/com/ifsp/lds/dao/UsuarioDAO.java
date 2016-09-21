/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.dao;

import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.util.JPAUtil;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author eddie
 */
public class UsuarioDAO {
    
    EntityManager entityManager = new JPAUtil().getEntityManager();
    
    public Usuario buscaUsuario(String login) {
        try {
            Query query = entityManager.createQuery("SELECT FROM Usuario u "
                    + "u.login='"+login+"'");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch(Exception ex) {
            return null;
        }
    }
}
