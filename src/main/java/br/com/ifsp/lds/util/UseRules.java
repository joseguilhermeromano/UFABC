/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ifsp.lds.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafin
 */
public class UseRules extends FormValidation{
    private List<Metodo> regras = new ArrayList<Metodo>();
    private List<String> todosErros = new ArrayList<String>();
    public void addRule(String regra, String campo, String dado) throws ClassNotFoundException {
        try {
            String classname = "br.com.ifsp.lds.util.FormValidation";
            Class<?> tipo = Class.forName(classname);
            Metodo metodo = new Metodo();
            Method methodToFind = tipo.getDeclaredMethod(regra, String.class,String.class);
            if (methodToFind != null) {
                metodo.setCampo(campo);
                metodo.setDado(dado);
                metodo.setMetodo(regra);
                regras.add(metodo);
            }
        } catch (NoSuchMethodException ex) {
            todosErros.add("a regra '" + regra + "' não existe");
        } catch (SecurityException ex) {
            Logger.getLogger(UseRules.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean executaRegras() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        int falsos=0;
        String tarefa = "br.com.ifsp.lds.util.FormValidation";
        Class<?> tipo = Class.forName(tarefa);
        FormValidation instancia = (FormValidation) tipo.newInstance();
        for (Metodo temp : regras) {
            Method metodo = tipo.getDeclaredMethod(temp.getMetodo(), String.class, String.class);
            boolean pagina = (boolean) metodo.invoke(instancia, temp.getCampo(), temp.getDado());
            if(pagina == false){
                falsos++;
            }
        }
        if (falsos == 0 && todosErros.isEmpty()) {
            return true;
        } else {
            Method metodo3 = tipo.getDeclaredMethod("getErros");
            todosErros.addAll((List<String>) metodo3.invoke(instancia));
            return false;
        }
    }
    public List<String> getTodosErros(){
        return todosErros;
    }
}
