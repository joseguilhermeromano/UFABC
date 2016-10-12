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
import javax.websocket.Session;

/**
 *
 * @author rafin
 */
public class FormValidation {
   
    private List<String> erros = new ArrayList<String>();
    /**
     * @param str Como String que recebera um provável INT como conteudo
     * @param campo Será o nome do campo no formulário
     * @return verdadeiro ou falso para caso seja inteiro
     */
    protected boolean isInteger(String campo, String str) {
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
    protected boolean isValidEmail(String campo, String str) {
        if (campo.isEmpty()) {
            campo = "Campo sem nome";
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

    protected boolean isValidDate(String campo, String dataV) {
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
    protected List<String> getErros(){
       return erros;
    }
   
}
