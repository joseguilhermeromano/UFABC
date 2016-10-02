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
    @GeneratedValue
    private Integer codigo;
    
//    @Column(name = "trei_descr") 
//    private String descrição;
//    
//    @Column(name = "trei_dt") 
//    private Date data;
//    
//    @Column(name = "trei_hora")
//    private Date hora;
//    
//    @Column(name = "trei_lab")
//    private String laboratorio;
//    
//    @Column(name = "trei_resp")
//    private String responsavel;
//    
//    @Column(name = "trei_turm")
//    private String turma;
//
    /**
     * @return the codigo
     */
    public Integer getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
//
//    /**
//     * @return the descrição
//     */
//    public String getDescrição() {
//        return descrição;
//    }
//
//    /**
//     * @param descrição the descrição to set
//     */
//    public void setDescrição(String descrição) {
//        this.descrição = descrição;
//    }
//
//    /**
//     * @return the data
//     */
//    public Date getData() {
//        return data;
//    }
//
//    /**
//     * @param data the data to set
//     */
//    public void setData(Date data) {
//        this.data = data;
//    }
//
//    /**
//     * @return the hora
//     */
//    public Date getHora() {
//        return hora;
//    }
//
//    /**
//     * @param hora the hora to set
//     */
//    public void setHora(Date hora) {
//        this.hora = hora;
//    }
//
//    /**
//     * @return the laboratorio
//     */
//    public String getLaboratorio() {
//        return laboratorio;
//    }
//
//    /**
//     * @param laboratorio the laboratorio to set
//     */
//    public void setLaboratorio(String laboratorio) {
//        this.laboratorio = laboratorio;
//    }
//
//    /**
//     * @return the responsavel
//     */
//    public String getResponsavel() {
//        return responsavel;
//    }
//
//    /**
//     * @param responsavel the responsavel to set
//     */
//    public void setResponsavel(String responsavel) {
//        this.responsavel = responsavel;
//    }
//
//    /**
//     * @return the turma
//     */
//    public String getTurma() {
//        return turma;
//    }
//
//    /**
//     * @param turma the turma to set
//     */
//    public void setTurma(String turma) {
//        this.turma = turma;
//    }
}
