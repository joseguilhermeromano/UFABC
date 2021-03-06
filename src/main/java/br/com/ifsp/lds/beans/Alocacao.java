/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.sql.Time;
import java.sql.Timestamp;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Column(name = "aloc_status")
    private int status;

    @ManyToOne()
    @JoinColumn(name = "aloc_usua_cd")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "aloc_trei_cd")
    private Treinamento treinamento;

    @OneToMany(mappedBy = "alocacao")
    private List<Falta> faltas;

    private boolean[] diasSemana = new boolean[6];

    /**
     * insere os valores booleanos de cada dia da semana em um array
     */
    private void setDiasSemana() {
        diasSemana[0] = segunda;
        diasSemana[1] = terca;
        diasSemana[2] = quarta;
        diasSemana[3] = quinta;
        diasSemana[4] = sexta;
        diasSemana[5] = sabado;
    }

    /**
     * Retorna todos os dias
     *
     * @return array boolean
     */
    public boolean[] getDiasSemana() {
        setDiasSemana();
        return diasSemana;
    }

    /**
     * verifica se há a possibilidade de haver um conlito com a data da presente
     * alocação
     *
     * @param novaData
     * @return boolean true se tiver conflito, false se não tiver conflito
     */
    public boolean possuiConflitosData(Date novaData) {
        return (novaData.compareTo(datainicio) == 0
                || novaData.compareTo(datafinal) == 0
                || (novaData.after(datainicio) && novaData.before(datafinal)));
    }
    
    /**
     * verifica se há a possibilidade de haver um conlito com os dias que é 
     * realizada a atual alocação
     *
     * @param dias
     * @return 
     */
    public boolean possuiConflitosDias(boolean[] dias) {
        this.setDiasSemana();
        for (int i = 0; i < diasSemana.length; i++) {
            if (dias[i] && diasSemana[i]) {
                return true;
            }
        }
        return false;
    }
    
    
    /**
     * verifica se há a possibilidade de haver um conlito com a hora da presente
     * alocação
     *
     * 
     * @param novaHora
     * @return 
     */
    public boolean possuiConflitosHoras(Date novaHora) {
        return (novaHora.compareTo(horainicio) == 0
                || novaHora.compareTo(horafim) == 0
                || (novaHora.after(horainicio) && novaHora.before(horafim)));
    }

    public boolean existeConflitos(Date dataInicio, boolean[] diasSemana, Date horaInicio) {
        return possuiConflitosData(dataInicio)  && possuiConflitosDias(diasSemana)
                && possuiConflitosHoras(horaInicio);
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
    public void setHorainicio(Time horainicio) {
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
    public void setHorafim(Time horafim) {
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
    public Treinamento getTreinamento() {
        return treinamento;
    }

    /**
     * @param treinamento the treinamento to set
     */
    public void setTreinamento(Treinamento treinamento) {
        this.treinamento = treinamento;
    }

    /**
     * @return the faltas
     */
    public List<Falta> getFaltas() {
        return faltas;
    }

    /**
     * @param faltas the faltas to set
     */
    public void setFaltas(List<Falta> faltas) {
        this.faltas = faltas;
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
