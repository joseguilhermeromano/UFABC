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
     *
     * @param login
     * @return Usuario ou null
     */
    public Usuario buscaUsuario(String login) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM "
                    + "Usuario u WHERE u.login='" + login + "'");
            Usuario usuario = (Usuario) query.getSingleResult();
            return usuario;
        } catch (Exception ex) {
            return null;
        }
    }

    public String buscaPeloNome(String nome, String url_base) {
        try {

            entityManager.getTransaction().begin();
            TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nome LIKE  '%" + nome + "%'", Usuario.class);
            ArrayList<Usuario> consulta = (ArrayList<Usuario>) query.getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            String html=""
                    + "<table class=\"table ls-table\" id=\"tabela1\"><tr>"
                    + "<thead><tr><th >Nome</th><th class='text-center'>Permissão</th><th class='text-center'>E-mail</th><th class='text-center'>Telefone</th><th class='text-center'>Detalhar/Alterar</th><th class='text-center'>Excluir</th></tr></thead>";
            for(int i = 0; i < consulta.size();i++){
                html += this.preparaUsuario(consulta.get(i), url_base);
            }
             html += "</table>";
            return html;
        } catch (Exception ex) {
            return null;
        }
    }

    private String preparaUsuario(Usuario usuario, String url_base) {
        String permissao = "Colaborador";
        if (usuario.getAdministrador() == 1) {
            permissao = "Administrador";
        }
        String usuarioHtml = "";
        usuarioHtml += "<td>" + usuario.getNome() + "</td>";
        usuarioHtml += "<td class='text-center'>" + permissao + "</td>";
        usuarioHtml += "<td class='text-center'>" + usuario.getEmail() + "</td>";
        usuarioHtml += "<td class='text-center'>" + usuario.getTelefone() + "</td>";
        usuarioHtml += "<td class='text-center'>";
        usuarioHtml += "<a href='alterar?codigo="+usuario.getCodigo()+"'>";
        usuarioHtml += "<span class='glyphicon glyphicon-edit estilo-botao-edicao'></span></a></td>";
        System.out.println("------------------TESTE FOR BUGS PURPOSE" +url_base+"----------------------");
        usuarioHtml += "<td class='text-center'><a href='#' data-toggle='modal' data-target='#modalExcluir' "
                + "onclick=\"setCodigo('"+usuario.getCodigo()+"'); setLink('"+url_base+"area-restrita/usuario/excluir?codigo=');"+'"'+">";
        usuarioHtml += "<span class='glyphicon glyphicon-trash estilo-botao-exclusao'></span></a> </td></tr>";
        return usuarioHtml;

    }

    @Override
    public boolean Cadastrar(Usuario obj) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(obj);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public ArrayList<Usuario> ConsultarTudo(String string) {
        try {
            if (string.equals("")) {
                entityManager.getTransaction().begin();
                TypedQuery<Usuario> query = entityManager.createQuery("select t from Usuario t", Usuario.class);
                ArrayList<Usuario> consulta = (ArrayList<Usuario>) query.getResultList();
                entityManager.getTransaction().commit();
                return consulta;
            } else {
                Query query = entityManager.createQuery("select u from Usuario u where u.nome='" + string + "'");
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
