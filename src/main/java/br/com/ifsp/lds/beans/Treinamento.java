/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsp.lds.beans;

import java.util.Date;
import java.util.List;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trei_cd")
    private int codigo;

    @Column(name = "trei_nm")
    private String nome;

    @Column(name = "trei_descr")
    private String descricao;

    @OneToMany(mappedBy = "treinamento")
    private List<Alocacao> alocacoes;

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
     * @return the alocacoes
     */
    public List<Alocacao> getAlocacao() {
        return alocacoes;
    }

    /**
     * @param alocacoes the alocacoes to set
     */
    public void setAlocacao(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }
}
