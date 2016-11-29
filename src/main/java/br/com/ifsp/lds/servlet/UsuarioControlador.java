/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Alocacao;
import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.beans.Justificativa;
import br.com.ifsp.lds.beans.Reposicao;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.FaltaDAO;
import br.com.ifsp.lds.dao.JustificativaDAO;
import br.com.ifsp.lds.dao.ReposicaoDAO;
import br.com.ifsp.lds.dao.UsuarioDAO;
import br.com.ifsp.lds.util.JPAUtil;
import br.com.ifsp.lds.util.UseRules;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 *
 * @author Luiz Felipe
 */
public class UsuarioControlador implements Tarefa {
    /*
     * @permAdmin É um Map estático utilizado para especificar 
     * os nomes dos métodos de classes de models que são permitidos 
     * apenas para usuários que são administradores do sistema.
     */

    private static final String[] permAdmin = {"cadastrar", "listartudo", "excluir", "alterar", "buscar", "buscarPeloNome"};
    private UsuarioDAO userdao = new UsuarioDAO();
    private UseRules validation = new UseRules();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    /**
     * Exibe a pagina inicial do usuario
     *
     * @param req
     * @param resp
     * @return pagina para redirecinamento
     */
    public String inicio(HttpServletRequest req, HttpServletResponse resp) {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        req.setAttribute("usuario", usuario);
        if (usuario.getAdministrador() != 1) {
            return iniciocolaborador(req, resp, usuario);
        } else {
            return inicioCoordenador(req, resp);
        }
    }
    
    private String inicioCoordenador(HttpServletRequest req, HttpServletResponse resp) {
        List<Justificativa> justificativas = new JustificativaDAO().consultarUltimas();
        List<Reposicao> reposicoes = new ReposicaoDAO().consultarUltimas();
        req.setAttribute("ultimasReposicoes", reposicoes);
        req.setAttribute("ultimasJustificativas", justificativas);
        return "/WEB-INF/views/administrador/index.jsp";
    }

    private String iniciocolaborador(HttpServletRequest req, HttpServletResponse resp, Usuario usuario) {
        GregorianCalendar calendar = new GregorianCalendar();
        int totaltreinos = 0;
        int treinospendentes = 0;
        int faltas = 0;
        int mes = calendar.get(GregorianCalendar.MONTH);
        for (Alocacao a : usuario.getAlocacoes()) {
            if (a.getDatainicio().getMonth() == mes || a.getDatafinal().getMonth() == mes) {
                if (a.getStatus() == 0) {
                    treinospendentes = treinospendentes + 1;
                }
                faltas = faltas + a.getFaltas().size();
                totaltreinos = totaltreinos + 1;
            }
        }
        req.setAttribute("treinosmes", totaltreinos);
        req.setAttribute("totalfaltas", faltas);
        req.setAttribute("pendentes", treinospendentes);
        List<Falta> ultimasFaltas = new FaltaDAO().consultaFaltasColaborador(usuario.getCodigo());
        req.setAttribute("ultimasFaltas", ultimasFaltas);
        Justificativa justificativa = new JustificativaDAO().ultimaJustificativaColaborador(usuario.getCodigo());
        req.setAttribute("justificativa",justificativa);
        Reposicao reposicao = new ReposicaoDAO().ultimaReposicaoColaborador(usuario.getCodigo());
        req.setAttribute("reposicao",reposicao);
        

        return "/WEB-INF/views/colaborador/index.jsp";
    }

    /**
     * Exibe a pagina de perfil do usuario
     *
     * @param req
     * @param resp
     * @return pagina para redirecinamento
     */
    public String perfil(HttpServletRequest req, HttpServletResponse resp) {

        return "/WEB-INF/views/administrador/perfil.jsp";
    }

    /**
     * Efetua o processedimento para realização de login. Abre uma sessão caso o
     * login e a senha do usuário estejam corretos
     *
     * @param req
     * @param resp
     * @return pagina inicial caso o login seja efetuado com sucesso ou pagina
     * de login novamente caso ocorra um erro durante o login
     */
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        String senha = req.getParameter("senha");
        String login = req.getParameter("username");
        /* Quando forem fixar usuário e senha no login, 
         usar o gitignore pra não fixar para todos que puxarem o projeto do git
         */
        String pagina = "/index.jsp";
        Usuario usuario = userdao.buscaUsuario(login);
        if (usuario != null) {
            if (usuario.getSenha().equals(senha)) {
                System.out.println("Here");
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);
                pagina =  inicio(req, resp);
            } else {
                req.setAttribute("erro", "<b>Erro!</b> A Senha está incorreta!");
            }
        } else {
            req.setAttribute("erro", "<b>Erro!</b> Login digitado incorretamente "
                    + "ou o usuário não está cadastrado na base de dados!");
        }
        return pagina;
    }

    /**
     * Procedimento para realizar logoff, finalização de de sessão e
     * redirecionamento para a pagina inicial
     *
     * @param req
     * @param resp
     * @return pagina inicial apos realizar o logoff
     */
    public String logoff(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute("usuarioLogado");
        req.setAttribute("sucesso", "Você foi deslogado com sucesso!");
        return "/index.jsp";
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {
        String pagina = "/WEB-INF/views/administrador/novo-usuario.jsp";

        if (!req.getParameterMap().isEmpty()) {
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
                    if (userdao.Cadastrar(usuario) == true) {
                        req.setAttribute("sucesso", "Usuário cadastrado com sucesso!");
                    } else {
                        req.setAttribute("erro", "Não foi possível cadastrar o Usuário!");
                    }
                    req.setAttribute("nome", "");
                    return this.listartudo(req, resp);
                } else {
                    req.setAttribute("erros", validation.getTodosErros());
                    return "/WEB-INF/views/administrador/novo-usuario.jsp";
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pagina;
    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        Usuario usuario = userdao.Consultar(codigo);

        if (req.getParameter("alterar") != null) {
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
                if (validation.executaRegras()) {
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
                    userdao.Alterar(usuario);
                    usuario = userdao.Consultar(codigo);
                    req.setAttribute("sucesso", "Usuário alterado com sucesso!");
                } else {
                    req.setAttribute("erro", "Não foi possível alterar o usuário");
                    req.setAttribute("erros", validation.getTodosErros());
                }
            } catch (Exception e) {
                Logger.getLogger(UsuarioControlador.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        req.setAttribute("usuario", usuario);
        return "/WEB-INF/views/administrador/edita-usuario.jsp";
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        
        List<Usuario> usuarios = userdao.ConsultarTudo("");
        req.setAttribute("usuarios", usuarios);
        return "/WEB-INF/views/administrador/usuarios.jsp";
    }

    public void buscarPeloNome(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session =  req.getSession();
        ArrayList<Usuario> userList = new UsuarioDAO().buscaPeloNome((String) req.getParameter("name"));
        String users =  new UsuarioDAO().preparaUsuarioTable(userList, (String)session.getAttribute("baseURL"));
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(users);

    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        int codigo = Integer.parseInt(req.getParameter("codigo"));
        if (userdao.Deletar(codigo) == true) {
            req.setAttribute("sucesso", "Usuário excluído com sucesso!");
        } else {
            req.setAttribute("erro", "Não foi possível excluir o Usuário!");
        }
        return this.listartudo(req, resp);
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
