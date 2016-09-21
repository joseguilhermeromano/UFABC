/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author eddie
 */

@Entity
@Table(name = "treinamanto")

public class Treinamento {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer trei_cd;
    
    @Column(name = "trei_descr", nullable = false, length = 200) 
    private String descrição;
    
    @Column(name = "trei_dt", nullable = false) 
    @Temporal(TemporalType.DATE) 
    private Date data;
    
    @Column(name = "trei_hora", nullable = false)
    @Temporal(TemporalType.TIME) 
    private Date hora;
    
    @Column(name = "trei_lab", nullable = false, length = 50)
    private String laboratorio;
    
    @Column(name = "trei_resp", nullable = false, length = 50)
    private String responsavel;
    
    @Column(name = "trei_turm", nullable = false, length = 50)
    private String turma;
    
    @ManyToOne(targetEntity = Colaborador.class, cascade=CascadeType.MERGE)
    @JoinColumn(name="trei_cola_cd")
    private Colaborador colabora;

    /**
     * @return the trei_cd
     */
    public Integer getTrei_cd() {
        return trei_cd;
    }

    /**
     * @param trei_cd the trei_cd to set
     */
    public void setTrei_cd(Integer trei_cd) {
        this.trei_cd = trei_cd;
    }

    /**
     * @return the descrição
     */
    public String getDescrição() {
        return descrição;
    }

    /**
     * @param descrição the descrição to set
     */
    public void setDescrição(String descrição) {
        this.descrição = descrição;
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
     * @return the hora
     */
    public Date getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(Date hora) {
        this.hora = hora;
    }

    /**
     * @return the laboratorio
     */
    public String getLaboratorio() {
        return laboratorio;
    }

    /**
     * @param laboratorio the laboratorio to set
     */
    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    /**
     * @return the responsavel
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel the responsavel to set
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * @return the turma
     */
    public String getTurma() {
        return turma;
    }

    /**
     * @param turma the turma to set
     */
    public void setTurma(String turma) {
        this.turma = turma;
    }

    /**
     * @return the colabora
     */
    public Colaborador getColabora() {
        return colabora;
    }

    /**
     * @param colabora the colabora to set
     */
    public void setColabora(Colaborador colabora) {
        this.colabora = colabora;
    }
}
