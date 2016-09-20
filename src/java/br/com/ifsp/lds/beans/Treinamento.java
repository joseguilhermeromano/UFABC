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
    private Integer codigo;
    
    @Column(name = "trei_descr") 
    private String descrição;
    
    @Column(name = "trei_dt") 
    private Date data;
    
    @Column(name = "trei_hora")
    private Date hora;
    
    @Column(name = "trei_lab")
    private String laboratorio;
    
    @Column(name = "trei_resp")
    private String responsavel;
    
    @Column(name = "trei_turm")
    private String turma;
}
