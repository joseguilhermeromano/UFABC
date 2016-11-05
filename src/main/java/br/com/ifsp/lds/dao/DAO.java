package br.com.ifsp.lds.dao;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Interface necessária para padronizar métodos das classes DAO*/
public interface DAO <T> {
    public boolean Cadastrar(T obj);
    public ArrayList<T> ConsultarTudo(String string);
    public T Consultar(int codigo);
    public boolean Alterar(T obj);
    public boolean Deletar(int codigo);
}
