/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.servlet;

import br.com.ifsp.lds.beans.Alocacao;
import br.com.ifsp.lds.beans.Falta;
import br.com.ifsp.lds.beans.Justificativa;
import br.com.ifsp.lds.beans.Usuario;
import br.com.ifsp.lds.dao.FaltaDAO;
import br.com.ifsp.lds.dao.JustificativaDAO;
import br.com.ifsp.lds.util.UseRules;
import com.mchange.io.FileUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;
import sun.misc.IOUtils;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;

/**
 *
 * @author Aluno
 */
public class JustificativaControlador implements Tarefa {

    private static final String[] permAdmin = {"aceitarecusa"};
    private UseRules validation = new UseRules();
    private JustificativaDAO justificaDao = new JustificativaDAO();

    @Override
    public String[] getPermAdmin(HttpServletRequest req, HttpServletResponse resp) {
        return this.permAdmin;
    }

    @Override
    public String cadastrar(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Part filePart = req.getPart("userfile"); // Retrieves <input type="file" name="file">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.            
            //InputStream fileContent = filePart.getInputStream();
            validation.addRule("required", "codigo", req.getParameter("codigo"));
            validation.addRule("isInteger", "codigo", req.getParameter("codigo"));
            validation.addRule("validaArquivo", fileName, Integer.toString((int) filePart.getSize()));
            validation.addRule("required", "nome", req.getParameter("nome"));
            validation.addRule("required", "data", req.getParameter("data"));
            validation.addRule("isValidDate", "data", req.getParameter("data"));
            validation.addRule("required", "motivo", req.getParameter("motivo"));
            if (validation.executaRegras()) {
                //req.setAttribute("justificativa", justificadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
                FaltaDAO faltadao = new FaltaDAO();
                HttpSession session = req.getSession();
                Justificativa justificador = new Justificativa();
                Usuario user = (Usuario) session.getAttribute("usuarioLogado");
                SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
                justificador.setComprovante(this.readFully(filePart.getInputStream()));
                justificador.setStatus(-1);
                justificador.setData(data.parse(req.getParameter("data")));
                justificador.setMotivodesc(req.getParameter("motivo"));
                justificador.setTamanho(Integer.toString((int) filePart.getSize()));
                justificador.setNome(fileName);
                justificador.setTipo(this.getExt(fileName));
                justificador.setFalta(faltadao.Consultar(Integer.parseInt(req.getParameter("codigo"))));

                if (justificaDao.Cadastrar(justificador)) {
                    Falta falta = faltadao.Consultar(Integer.parseInt(req.getParameter("codigo")));
                    falta.setJustificativa(justificador);
                    faltadao.Alterar(falta);

                    return new FaltaControlador().listartudo(req, resp);
                } else {
                    req.setAttribute("erro", "Não foi possível cadastrar a justificativa");
                    return "/WEB-INF/views/administrador/justificativa.jsp";
                }
            } else {
                req.setAttribute("erros", validation.getTodosErros());
                req.setAttribute("erro", "Não foi possível cadastrar a justificativa");
                return "/WEB-INF/views/administrador/justificativa.jsp";
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        } catch (IOException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        } catch (ServletException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        } catch (ParseException ex) {
            return "/WEB-INF/views/administrador/justificativa.jsp";
        }
    }

    private String getExt(String ext) {
        int i = ext.lastIndexOf('.');
        if (i > 0) {
            return ext.substring(i + 1);
        }
        return null;
    }

    private byte[] readFully(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();

    }

    @Override
    public String alterar(HttpServletRequest req, HttpServletResponse resp) {
        return "/WEB-INF/views/colaborador/edita-justificativa.jsp";
    }

    public String aceitarecusa(HttpServletRequest req, HttpServletResponse resp) {
        Justificativa justificativa = justificaDao.
                Consultar(Integer.parseInt(req.getParameter("codigo")));
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        req.setAttribute("usuario", usuario);
        if (req.getParameter("escolha") != null) {
            justificativa.setStatus(Integer.parseInt(req.getParameter("escolha")));
            if (Integer.parseInt(req.getParameter("escolha")) == 0) {
                try {
                    validation.addRule("required", "recusa", req.getParameter("recusa"));
                    if (validation.executaRegras()) {
                        justificativa.setMotivorecusa(req.getParameter("recusa"));
                        if (justificaDao.Alterar(justificativa) == true) {
                            req.setAttribute("sucesso", "A Justificativa foi recusada com sucesso!");
                        } else {
                            req.setAttribute("erro", "Não foi possível recusar a justificativa!");
                        }
                    } else {
                        req.setAttribute("erro", "Não foi possível recusar a justificativa! Por favor, escreva o motivo da recusa!");
                        req.setAttribute("erros", validation.getTodosErros());
                    }
                } catch (        ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(JustificativaControlador.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (Integer.parseInt(req.getParameter("escolha")) == 1) {
                justificativa.setMotivorecusa("");
                if (justificaDao.Alterar(justificativa) == true) {
                    req.setAttribute("sucesso", "A Justificativa foi aceita com sucesso!");
                } else {
                    req.setAttribute("erro", "Não foi possível aceitar a justificativa!");
                }
            }

        }

        req.setAttribute("justificativa", justificaDao.Consultar(Integer.parseInt(req.getParameter("codigo"))));
        return "/WEB-INF/views/visualizar-justificativa.jsp";
    }

    @Override
    public String listartudo(HttpServletRequest req, HttpServletResponse resp) {
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        req.setAttribute("usuario", usuario);

        List<Justificativa> justificativas = new ArrayList<Justificativa>();
        if (usuario.getAdministrador() != 1) {
            for (Alocacao u : usuario.getAlocacoes()) {
                for (Falta f : u.getFaltas()) {
                    if (f.getJustificativa() != null) {
                        justificativas.add(f.getJustificativa());
                    }
                }
            }
        }
        req.setAttribute("justificativas", justificativas);
        return "";
    }

    @Override
    public String buscar(HttpServletRequest req, HttpServletResponse resp) {
        JustificativaDAO justificadao = new JustificativaDAO();
        try {
            validation.addRule("required", "Id da justificativa", req.getParameter("codigo"));
            validation.addRule("isInteger", "Id da justificativa", req.getParameter("codigo"));
            if (validation.executaRegras()) {

                Justificativa justificativa = justificadao.Consultar(Integer.parseInt(req.getParameter("codigo")));
                req.setAttribute("justificativa", justificativa);

            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException ex) {
            Logger.getLogger(JustificativaControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        req.setAttribute("usuario", usuario);
        return "/WEB-INF/views/visualizar-justificativa.jsp";
    }

    @Override
    public String excluir(HttpServletRequest req, HttpServletResponse resp) {
        return "/erro404.jsp";
    }

    /**
     * Efetua o procedimento para exibir o pdf
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void showPdf(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] buffer = new byte[1024];
        int byteRead;
        Justificativa justificativa = justificaDao.Consultar(Integer.parseInt(req.getParameter("codigo")));
        byte[] justificativaPdf = justificativa.getComprovante();
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + justificativa.getNome() + "\"");
        resp.setContentLength(justificativaPdf.length);
        resp.getOutputStream().write(justificativaPdf);
    }

}
