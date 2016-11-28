/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author eddie
 */
@Entity
@Table(name = "falta")
public class Falta {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "just_cd") 
    private int codigo;
  
    @Column(name = "just_dt") 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
        
    @Column(name = "falta_status") 
    private  int status;
    
    @ManyToOne
    @JoinColumn(name = "falta_aloc_cd")
    private Alocacao alocacao;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy="falta", optional = true)
    private Justificativa justificativa;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy="falta", optional = true)
    private Reposicao reposicao;
    
    public Falta(){
        
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
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the justificativa
     */
    public Justificativa getJustificativa() {
        return justificativa;
    }

    /**
     * @param justificativa the justificativa to set
     */
    public void setJustificativa(Justificativa justificativa) {
        this.justificativa = justificativa;
    }

    /**
     * @return the alocacao
     */
    public Alocacao getAlocacao() {
        return alocacao;
    }

    /**
     * @param alocacao the alocacao to set
     */
    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
    }

    /**
     * @return the reposicao
     */
    public Reposicao getReposicao() {
        return reposicao;
    }

    /**
     * @param reposicao the reposicao to set
     */
    public void setReposicao(Reposicao reposicao) {
        this.reposicao = reposicao;
    }
    
}
