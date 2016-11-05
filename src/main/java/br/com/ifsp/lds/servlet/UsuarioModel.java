/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.UsuarioDAO;
import br.com.ifsp.lds.util.UseRules;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Luiz Felipe
 */
public class UsuarioModel implements Tarefa {

    /*
    * @permAdmin É um Map estático utilizado para especificar 
    * os nomes dos métodos de classes de models que são permitidos 
    * apenas para usuários que são administradores do sistema.
     */
    private static final String[] permAdmin = {""};
    private UsuarioDAO userdao = new UsuarioDAO();
    private UseRules validation = new UseRules();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    public String inicio(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/index.jsp";
    }

    public String perfil(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/perfil.jsp";
    }

    public String novousuario(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/administrador/novo-usuario.jsp";
    }

    public String login(HttpServletRequest req, HttpServletResponse resp) {
        //String senha = req.getParameter("senha");
        //String login = req.getParameter("username");
        /*Código temporário para desativar login*/
        String senha = "teste";
        String login = "teste";

        Usuario usuario = userdao.buscaUsuario(login);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);
            String segmento;
            if (usuario.getAdministrador() == 1) {
                segmento = "administrador";
            } else {
                segmento = "colaborador";
            }

            return "/WEB-INF/views/" + segmento + "/index.jsp";
        }
        return "/index.jsp";
    }

    public String logoff(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("usuarioLogado");
        return "/index.jsp";
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        String pagina = "/WEB-INF/views/administrador/novo-usuario.jsp";
        try {
            validation.addRule("required", "nome", req.getParameter("nome"));
            validation.addRule("required", "cpf", req.getParameter("cpf"));
            validation.addRule("required", "rg", req.getParameter("rg"));
            validation.addRule("required", "email", req.getParameter("email"));
            validation.addRule("isValidEmail", "email", req.getParameter("email"));
            validation.addRule("required", "telefone", req.getParameter("telefone"));
            validation.addRule("required", "endereco", req.getParameter("endereco"));
            validation.addRule("required", "numero", req.getParameter("numero"));
            validation.addRule("required", "bairro", req.getParameter("bairro"));
            validation.addRule("required", "cidade", req.getParameter("cidade"));
            validation.addRule("required", "permissao", req.getParameter("permissao"));
            validation.addRule("required", "login", req.getParameter("login"));
            validation.addRule("required", "senha", req.getParameter("senha"));
            if (validation.executaRegras()) {
                Usuario usuario = new Usuario();
                usuario.setNome(req.getParameter("nome"));
                usuario.setCpf(req.getParameter("cpf"));
                usuario.setRg(req.getParameter("rg"));
                usuario.setEmail(req.getParameter("email"));
                usuario.setTelefone(req.getParameter("telefone"));
                usuario.setEndereco(req.getParameter("endereco"));
                usuario.setNumero(req.getParameter("numero"));
                usuario.setBairro(req.getParameter("bairro"));
                usuario.setCidade(req.getParameter("cidade"));
                usuario.setComplemento(req.getParameter("complemento"));
                usuario.setAdministrador(Integer.parseInt(req.getParameter("permissao")));
                usuario.setLogin(req.getParameter("login"));
                usuario.setSenha(req.getParameter("senha"));
                if(userdao.Cadastrar(usuario)==true){
                    req.setAttribute("sucesso", "Usuário cadastrado com sucesso!");
                }else{
                    req.setAttribute("erro", "Não foi possível cadastrar o Usuário!");
                }
                return this.listartudo(req, resp);
            } else {
                
                for(String c : validation.getTodosErros()) {
                    System.out.println(c);
                }
                return "/WEB-INF/views/administrador/novo-usuario.jsp";
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return pagina;
    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        String nome = req.getParameter("nome") == null ? "" : req.getParameter("nome");
        List<Usuario> usuarios = userdao.ConsultarTudo(nome);
        req.setAttribute("usuarios", usuarios);
        for (Usuario u : usuarios) {
            System.out.println(u.getNome());
        }
        return "/WEB-INF/views/administrador/usuarios.jsp";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        if(userdao.Deletar(codigo)==true){
            req.setAttribute("sucesso", "Usuário excluído com sucesso!");
        }else{
            req.setAttribute("erro", "Não foi possível excluir o Usuário!");
        }
        return this.listartudo(req, resp);
    }

}
