/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;


import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author eddie
 */

@Entity
@Table(name = "treinamanto")
public class Treinamento {
 
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "trei_cd") 
    private int codigo;
    
    @Column(name = "trei_descr") 
    private String descricao;
    
    @Column(name = "trei_ini_dt") 
    private Date dataincio;
    
    @Column(name = "trei_fim_dt") 
    private Date datafim;
    
    @Column(name = "trei_hora")
    private Date hora;
    
    @Column(name = "trei_turma")
    private String turma;
    
    @Column(name = "trei_lab")
    private String laboratorio;
    
    //@ManyToOne(targetEntity = Treinamento.class, cascade=CascadeType.MERGE)
    //@JoinColumn(name = "trei_usua_cd")
    
    //codico acima não funciona
    @ManyToOne
    @JoinColumn(name = "trei_usua_cd", referencedColumnName = "usua_cd")
    private Usuario usuario;


    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the dataincio
     */
    public Date getDatainicio() {
        return dataincio;
    }

    /**
     * @param dataincio the dataincio to set
     */
    public void setDatainicio(Date dataincio) {
        this.dataincio = dataincio;
    }

    /**
     * @return the datafim
     */
    public Date getDatafim() {
        return datafim;
    }

    /**
     * @param datafim the datafim to set
     */
    public void setDatafim(Date datafim) {
        this.datafim = datafim;
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
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    


    
}
