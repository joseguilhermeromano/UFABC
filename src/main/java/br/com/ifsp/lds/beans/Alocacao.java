/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author José Guilherme
 */
@Entity
@Table(name = "alocacao")
public class Alocacao {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "aloc_cd") 
    private int codigo;
    
    @Column(name = "aloc_inicio_dt") 
    private Date datainicio;
    
    @Column(name = "aloc_fim_dt")
    private Date datafinal;
    
    @Column(name = "aloc_inico_hr")
    private Date horainicio;
    
    @Column(name = "aloc_fim_hr")
    private Date horafim;
    
    @Column(name = "aloc_segunda")
    private boolean segunda;
    
    @Column(name = "aloc_terca")
    private boolean terca;
    
    @Column(name = "aloc_quarta")
    private boolean quarta;
    
    @Column(name = "aloc_quinta")
    private boolean quinta;
    
    @Column(name = "aloc_sexta")
    private boolean sexta;
    
    @Column(name = "aloc_sabado")
    private boolean sabado;
    
    @ManyToOne()
    @JoinColumn(name = "aloc_usua_cd")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "aloc_trei_cd")
    private Treinamento treinamento;

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
     * @return the datainicio
     */
    public Date getDatainicio() {
        return datainicio;
    }

    /**
     * @param datainicio the datainicio to set
     */
    public void setDatainicio(Date datainicio) {
        this.datainicio = datainicio;
    }

    /**
     * @return the datafinal
     */
    public Date getDatafinal() {
        return datafinal;
    }

    /**
     * @param datafinal the datafinal to set
     */
    public void setDatafinal(Date datafinal) {
        this.datafinal = datafinal;
    }

    /**
     * @return the horainicio
     */
    public Date getHorainicio() {
        return horainicio;
    }

    /**
     * @param horainicio the horainicio to set
     */
    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }

    /**
     * @return the horafim
     */
    public Date getHorafim() {
        return horafim;
    }

    /**
     * @param horafim the horafim to set
     */
    public void setHorafim(Date horafim) {
        this.horafim = horafim;
    }

    /**
     * @return the segunda
     */
    public boolean isSegunda() {
        return segunda;
    }

    /**
     * @param segunda the segunda to set
     */
    public void setSegunda(boolean segunda) {
        this.segunda = segunda;
    }

    /**
     * @return the terca
     */
    public boolean isTerca() {
        return terca;
    }

    /**
     * @param terca the terca to set
     */
    public void setTerca(boolean terca) {
        this.terca = terca;
    }

    /**
     * @return the quarta
     */
    public boolean isQuarta() {
        return quarta;
    }

    /**
     * @param quarta the quarta to set
     */
    public void setQuarta(boolean quarta) {
        this.quarta = quarta;
    }

    /**
     * @return the quinta
     */
    public boolean isQuinta() {
        return quinta;
    }

    /**
     * @param quinta the quinta to set
     */
    public void setQuinta(boolean quinta) {
        this.quinta = quinta;
    }

    /**
     * @return the sexta
     */
    public boolean isSexta() {
        return sexta;
    }

    /**
     * @param sexta the sexta to set
     */
    public void setSexta(boolean sexta) {
        this.sexta = sexta;
    }

    /**
     * @return the sabado
     */
    public boolean isSabado() {
        return sabado;
    }

    /**
     * @param sabado the sabado to set
     */
    public void setSabado(boolean sabado) {
        this.sabado = sabado;
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
    public void setUsuarios(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the treinamentos
     */
    public Treinamento getTreinamentos() {
        return treinamento;
    }

    /**
     * @param treinamento the treinamento to set
     */
    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }
}
