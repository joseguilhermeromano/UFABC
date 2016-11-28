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

/**
 *
 * @author Luiz Felipe
 */
@Entity
@Table(name = "reposicao")
public class Reposicao {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "repo_cd")
    private int codigo;
    
    @OneToOne
    private Falta falta;
    
    @ManyToOne()
    private Usuario responsavelReposicao;
    
    @Column(name = "repo_data")
    private Date data;
    
    @Column(name = "repo_inicio_hr")
    private Date horaInicio;
    
    @Column(name = "repo_fim_hr")
    private Date horaFim;
    
    @Column(name = "repo_status")
    private int status;
    
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

    /**
     * @return the responsavelReposicao
     */
    public Usuario getResponsavelReposicao() {
        return responsavelReposicao;
    }

    /**
     * @param responsavelReposicao the responsavelReposicao to set
     */
    public void setResponsavelReposicao(Usuario responsavelReposicao) {
        this.responsavelReposicao = responsavelReposicao;
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
     * @return the horaInicio
     */
    public Date getHoraInicio() {
        return horaInicio;
    }

    /**
     * @param horaInicio the horaInicio to set
     */
    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * @return the horaFim
     */
    public Date getHoraFim() {
        return horaFim;
    }

    /**
     * @param horaFim the horaFim to set
     */
    public void setHoraFim(Date horaFim) {
        this.horaFim = horaFim;
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
    
}
