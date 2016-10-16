package br.com.ifsp.lds.dao;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Interface necessária para padronizar métodos das classes DAO*/
public interface DAO <T> {
    public void Cadastrar(T obj,HttpServletRequest req, HttpServletResponse resp);
    public ArrayList<T> ConsultarTudo(String string,HttpServletRequest req, HttpServletResponse resp);
    public T Consultar(int codigo,HttpServletRequest req, HttpServletResponse resp);
    public void Alterar(T obj,HttpServletRequest req, HttpServletResponse resp);
    public void Deletar(int codigo,HttpServletRequest req, HttpServletResponse resp);
}
