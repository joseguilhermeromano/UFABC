/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.util.ArrayList;
import java.util.List;
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
    @Column(name="usua_cd")
    private int codigo;
    
    @Column(name = "usua_nm")
    private String nome;
    
    @Column(name = "usua_cpf")
    private String cpf;
    
    @Column(name = "usua_rg")
    private String rg;
    
    @Column(name = "usua_email")
    private String email;
    
    @Column(name = "usua_telefone")
    private String telefone;
    
    @Column(name = "usua_endereco")
    private String endereco;
    
    @Column(name = "usua_numero")
    private String numero;
    
    @Column(name = "usua_bairro")
    private String bairro;
    
    @Column(name = "usua_cidade")
    private String cidade;
    
    @Column(name = "usua_complemento")
    private String complemento;
    
    @Column(name = "usua_espe")
    private String especialidade;
    
    @Column(name = "usua_isAdmin")
    private int administrador;
    
    
    @Column(name = "usua_login",unique = true)
    private String login;
    
    @Column(name = "usua_senha")
    private String senha;
    
    @OneToMany(mappedBy = "usuario")
    private List<Alocacao> alocacoes;
    
    /**
     * @return the cola_cd
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the cola_cd to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
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
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
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
     * @return the administrador
     */
    public int getAdministrador() {
        return administrador;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(int administrador) {
        this.administrador = administrador;
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
     * @return the alocacoes
     */
    public List<Alocacao> getAlocacoes() {
        return alocacoes;
    }

    /**
     * @param alocacoes the alocacoes to set
     */
    public void setAlocacoes(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }
}
