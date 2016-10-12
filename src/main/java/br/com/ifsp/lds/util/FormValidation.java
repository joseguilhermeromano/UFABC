/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rafin
 */
public class FormValidation {

    private List<String> erros = new ArrayList<String>();
    private List<Metodo> regras = new ArrayList<Metodo>();

    /**
     * @param str Como String que recebera um provável INT como conteudo
     * @param campo Será o nome do campo no formulário
     * @return verdadeiro ou falso para caso seja inteiro
     */
    public boolean isInteger(String campo, String str) {
        if (campo.isEmpty()) {
            campo = "Campo sem nome :";
        }
        if (str == null || str.length() == 0) {
            this.erros.add(campo + "Impossivel análisar um campo vazio !!! \n");
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                this.erros.add(campo + "Apenas numeros inteiros é permitido nesse campo !!! ex:(0, 1 , 2, 3, 4, 5..) \n");
                return false;
            }
        }
        return true;

    }

    /**
     * @param str Como String que recebera um emáil
     * @param campo Será o nome do campo no formulário
     * @return verdadeiro ou falso para caso seja um email válido
     */
    public boolean isValidEmail(String campo, String str) {
        if (campo.isEmpty()) {
            campo = "Campo sem nome :";
        }
        if (str == null || str.length() == 0) {
            this.erros.add(campo + ": Campo vazio !!! \n");
            return false;
        }

        boolean verifica = Pattern.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}+\\.[A-Za-z]{2,}", str);
        if (verifica) {
            return true;
        } else {
            this.erros.add(campo + ": email inválido !!! \n");
            return false;
        }
    }

    public boolean isValidDate(String campo, String dataV) {
        if (campo.isEmpty()) {
            campo = "Campo sem nome :";
        }
        if (dataV == null || dataV.length() == 0) {
            return true;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yy");
        sdf.setLenient(false);

        try {
            Date data = sdf.parse(dataV);
            return true;
        } catch (ParseException e) {
            this.erros.add(campo + ": data em formáto inválido !!! \n");
            return false;
        }

    }

    public void addRule(String regra, String campo, String dado) throws NoSuchMethodException, ClassNotFoundException {
        
        /*
        Class<?> tipo = Class.forName(tarefa);
        FormValidation instancia = (FormValidation) tipo.newInstance();
        for (Metodo temp : regras) {
            Method metodo = tipo.getDeclaredMethod(temp.getMetodo(), String.class, String.class);
        */
        String classname = "br.com.ifsp.lds.util.FormValidation";
        Class<?> tipo = Class.forName(classname);
        Metodo metodo = new Metodo();
        Method methodToFind = tipo.getDeclaredMethod(regra, String.class,String.class);
        if (methodToFind != null) {
            metodo.setCampo(campo);
            metodo.setDado(dado);
            metodo.setMetodo(regra);
            regras.add(metodo);
        } else {
            erros.add("a regra '" + regra + "' não existe");
        }
    }

    public boolean executaRegras() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        this.erros = null;
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
        if (falsos == 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean carro(){
        return true;
    }
}
