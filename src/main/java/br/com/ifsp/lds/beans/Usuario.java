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
    
    @Column(name = "usu_nm", nullable = false, length = 50)
    private String nome;
    
    @Column(name = "usu_id", nullable = false)
    private Integer ID;
    
    @Column(name = "usu_espe", nullable = false, length = 80)
    private String especialidade;
    
    @Column(name = "usu_setor", nullable = false, length = 80)
    private String setor;
    
    @Column(name = "usu_email", nullable = false, length = 100)
    private String email;
    
    @Column(name = "usu_phone", nullable = false, length = 20)
    private String telefone;
    
    @Column(name = "usu_isAdmin", nullable = false)
    private Integer administrador;
    
    @OneToMany(targetEntity = Treinamento.class, cascade=CascadeType.MERGE)
    @JoinColumn(name="usu_trei_cd")
    private Treinamento treino;
    
    @Column(name = "usu_login",unique = true)
    private String login;
    
    @Column(name = "usu_senh",nullable = false)
    private String senha;
    

    /**
     * @return the cola_cd
     */
    public Integer getCola_cd() {
        return cola_cd;
    }

    /**
     * @param cola_cd the cola_cd to set
     */
    public void setCola_cd(Integer cola_cd) {
        this.cola_cd = cola_cd;
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
     * @return the ID
     */
    public Integer getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * @return the especialidade
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /**
     * @return the setor
     */
    public String getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the administrador
     */
    public Integer getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(Integer administrador) {
        this.administrador = administrador;
    }

    /**
     * @return the treino
     */
    public Treinamento getTreino() {
        return treino;
    }

    /**
     * @param treino the treino to set
     */
    public void setTreino(Treinamento treino) {
        this.treino = treino;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
