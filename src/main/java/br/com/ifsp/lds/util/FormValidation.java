/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.util;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import sun.font.EAttribute;

/**
 *
 * @author rafin
 */
public class FormValidation {
    private static final int fileLimit = 15000;
    private static final String types[] = {"pdf", "png", "jpg", "jpeg", "doc","docx"};
    private List<String> erros = new ArrayList<String>();
    
    protected boolean validaArquivo(String ext, String size){
        if(isInteger("arquivo", size)){
            if((Integer.parseInt(size) / 1024) > fileLimit){
                      System.out.println("O tamanho do arquivo excede o limit de "+fileLimit+" kbytes...  {"+(Integer.parseInt(size) / 1024)+"}");
                this.erros.add("O tamanho do arquivo excede o limit de "+fileLimit+" kbytes...");
                return false;
            }
        }
        for(int i = 0; i < types.length; i++){
            if(ext.equals(types[i])){
                return true;
            }
        }
        System.out.println("A extensão{"+ ext+"} não é permitida");
        this.erros.add("A extensão{"+ ext+"} não é permitida");
        return false;
    }
    
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
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                this.erros.add(campo + ": Apenas numeros inteiros é permitido nesse campo !!! ex:(0, 1 , 2, 3, 4, 5..) \n");
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
            return true;
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
    
    protected boolean required(String campo,String dado ){
        if (campo.isEmpty()) {
            campo = "Campo sem nome :";
        }
        if (dado == null || dado.length() == 0) {
            this.erros.add(campo + ": Campo é obrigatório !!! \n");
            return false;
        }
        return true;
    }
    protected boolean isValidHour(String campo, String dataV){
        if (campo.isEmpty()) {
            campo = "Campo sem nome :";
        }
        if (dataV == null || dataV.length() == 0) {
            return true;
        }
        
        boolean verifica = Pattern.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]", dataV);
        if (verifica) {
            return true;
        } else {
            this.erros.add(campo + ": Campo em formato inválido!!! \n");
            return false;
        }
        
    }
    protected List<String> getErros() {
       return erros;
    }
    
    /**
     * Verifica se o valor já existe no banco de dados
     * @param campo 
     * @param dado
     * @param tabela 
     * @return boolean
     */
    public boolean isUnique(String campo, String dado, String tabela) {
        boolean resultado = false;
        if(this.required(campo, dado)) {
            resultado =  new JPAUtil().getEntityManager()
                .createQuery("From " + tabela +" t where t."+campo+"=:dado")
                .setParameter("dado", dado).getSingleResult() == null;
            if(!resultado) this.erros.add(campo + " já esta registrado!");
        }
        return resultado;
    }
    
    
   
}
