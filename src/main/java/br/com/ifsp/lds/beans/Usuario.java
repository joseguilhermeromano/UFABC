/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import static com.sun.xml.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import javax.persistence.*;

/**
 *
 * @author eddie
 */

@Entity
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer cola_cd;
    
    @Column(name = "cola_nm")
    private String nome;
    
    @Column(name = "cola_id")
    private Integer ID;
    
    @Column(name = "cola_espe")
    private String especialidade;
    
    @Column(name = "cola_setor")
    private String setor;
    
    @Column(name = "cola_email")
    private String email;
    
    @Column(name = "cola_phone")
    private String telefone;
    
    @Column(name = "cola_isAdmin")
    private Integer administrador;
   
}
