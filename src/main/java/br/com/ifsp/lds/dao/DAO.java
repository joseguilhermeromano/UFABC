package br.com.ifsp.lds.dao;


import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*Interface necessária para padronizar métodos das classes DAO*/
public interface DAO <T> {
    /**
     * Registra o objeto na base de dados
     * @param obj objeto a ser registrado no banco de dados
     * @return true caso  a ação seja bem sucedida ou false em caso de algum erro
     */
    public boolean Cadastrar(T obj);
    
    /**
     * Retorna uma lista de determinado objeto
     * @param string regra como um nome ou simplemente empty. 
     * @return lista de objetos ou null
     */
    public ArrayList<T> ConsultarTudo(String string);
    /**
     * Retorna um unico objeto de acordo com o codigo fornecido
     * @param codigo registro do objeto ou relacionamento
     * @return objeto
     */
    public T Consultar(int codigo);
    
    /**
     * Alterar um valor existente na base de dados
     * @param obj objeto com os novos valores
     * @return true caso a ação seja bem sucedica ou false em caso de erros
     */
    public boolean Alterar(T obj);
    
    /**
     * Deleta determinado objeto na base de dados
     * @param codigo registro do objeto
     * @return true caso a ação seja bem sucedica ou false em caso de erros
     */
    public boolean Deletar(int codigo);
}
