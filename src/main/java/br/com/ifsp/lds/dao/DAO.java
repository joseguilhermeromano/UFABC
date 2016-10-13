package br.com.ifsp.lds.dao;


import java.util.ArrayList;

/*Interface necessária para padronizar métodos das classes DAO*/
public interface DAO <T> {
    public void Cadastrar(T obj);
    public ArrayList<T> ConsultarTudo(String string);
    public T Consultar(int codigo);
    public void Alterar(T obj);
    public void Deletar(int codigo);
}
