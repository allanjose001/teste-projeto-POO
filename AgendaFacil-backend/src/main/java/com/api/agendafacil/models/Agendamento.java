package com.api.agendafacil.models;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Agendamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;

    @Column(nullable=false)
    private LocalDate dataConsulta;

    @Column(nullable=false, length=200)
    private String tipoConsulta;
    
    //metodo construtor vai me garantir o modelo de agendamento padrão para a minha aplicação.
    public Agendamento(UUID id, Usuario usuario, LocalDate dataConsulta, String tipoConsulta) {
        this.id = id;
        this.usuario = usuario;
        this.dataConsulta = dataConsulta;
        this.tipoConsulta = tipoConsulta;
    }

    // Getters e Setters

    /**
     * Obtém o ID do agendamento.
     * @return O ID do agendamento.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Define o ID do agendamento.
     * @param id O ID do agendamento.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Obtém o usuário associado ao agendamento.
     * @return O usuário associado ao agendamento.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Define o usuário associado ao agendamento.
     * @param usuario O usuário associado ao agendamento.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtém a data da consulta.
     * @return A data da consulta.
     */
    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    /**
     * Define a data da consulta.
     * @param dataConsulta A data da consulta.
     */
    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    /**
     * Obtém o tipo de consulta do agendamento.
     * @return O tipo de consulta do agendamento.
     */
    public String getTipoConsulta() {
        return tipoConsulta;
    }

    /**
     * Define o tipo de consulta do agendamento.
     * @param tipoConsulta O tipo de consulta do agendamento.
     */
    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    /**
     * Verifica se dois objetos Agendamento são iguais.
     * @param o O objeto a ser comparado.
     * @return true se os objetos forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agendamento other = (Agendamento) o;
        return Objects.equals(id, other.id) &&
               Objects.equals(usuario, other.usuario) &&
               Objects.equals(dataConsulta, other.dataConsulta) &&
               Objects.equals(tipoConsulta, other.tipoConsulta);
    }

    /**
     * Retorna o código hash do objeto Agendamento.
     * @return O código hash do objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, dataConsulta, tipoConsulta);
    }
}
	
