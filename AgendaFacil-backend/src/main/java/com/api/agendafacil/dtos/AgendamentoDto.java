package com.api.agendafacil.dtos;

import com.api.agendafacil.models.Usuario;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.api.agendafacil.enums.TipoDeConsulta;



/**
 * Classe de transferência de dados para operações de agendamento.
 * Contém informações sobre o tipo de consulta, data da consulta e o usuário associado ao agendamento.
 */
public class AgendamentoDto {
    
    /** Tipo da consulta (ex: medico, dentista etc). */
    @NotBlank
    private TipoDeConsulta tipoConsulta;
    
    /** Data da consulta. */
    @NotNull
    private LocalDate dataConsulta;
    
    /** Usuário associado ao agendamento. */
    @NotNull
    private Usuario usuario;
    
    // Getters e Setters
    
    /**
     * Obtém o tipo de consulta.
     * @return O tipo de consulta.
     */
    public TipoDeConsulta getTipoConsulta() {
        return tipoConsulta;
    }
    
    /**
     * Define o tipo de consulta.
     * @param tipoConsulta O tipo de consulta a ser definido.
     */
    public void setTipoConsulta(TipoDeConsulta tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
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
     * @param dataConsulta A data da consulta a ser definida.
     */
    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
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
     * @param usuario O usuário associado ao agendamento a ser definido.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}