/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author eddie
 */
@Entity
@Table(name = "justificativa")
public class Justificativa {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "just_cd") 
    private int codigo;
    
    @Column(name = "just_nm") 
    private String nome;
    
    @Column(name = "just_tm") 
    private String tamanho;
    
     @Column(name = "just_tp") 
    private String tipo;
     
    @Column(name = "just_dt") 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    
    @Column(name = "just_motivo_dec") 
    private String motivodesc;
    
    @Column(name = "just_comprovante") 
    private byte[] comprovante;
    
    @OneToOne
    private Falta falta;
    
    public Justificativa(){
        
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the motivodesc
     */
    public String getMotivodesc() {
        return motivodesc;
    }

    /**
     * @param motivodesc the motivodesc to set
     */
    public void setMotivodesc(String motivodesc) {
        this.motivodesc = motivodesc;
    }

    /**
     * @return the comprovante
     */
    public byte[] getComprovante() {
        return comprovante;
    }

    /**
     * @param comprovante the comprovante to set
     */
    public void setComprovante(byte[] comprovante) {
        //this.comprovante = comprovante;
    }

    /**
     * @return the tamanho
     */
    public String getTamanho() {
        return tamanho;
    }

    /**
     * @param tamanho the tamanho to set
     */
    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the falta
     */
    public Falta getFalta() {
        return falta;
    }

    /**
     * @param falta the falta to set
     */
    public void setFalta(Falta falta) {
        this.falta = falta;
    }
    
    
}
