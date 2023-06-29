package br.com.ifsp.todolist.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Tarefa {

    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;
    private StatusEnum status;
    private Usuario usuario;

    public Tarefa() {
    }

    public Tarefa(String titulo, String descricao, LocalDateTime dataCriacao, LocalDateTime dataConclusao, StatusEnum status, Usuario usuario) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public StatusEnum getStatusEnum() {
        return status;
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.status = statusEnum;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Timestamp getDataCriacaoTimestamp() {
        return Timestamp.valueOf(getDataCriacao());
    }

    public Timestamp getDataConclusaoTimestamp() {
        if(dataConclusao == null)
            return null;
        return Timestamp.valueOf(dataConclusao);
    }

    public void setDataCriacaoTimestamp(Timestamp dataCriacaoTimestamp) {
        if (dataCriacaoTimestamp == null) {
            dataCriacao = null;
        } else {
            dataCriacao = dataCriacaoTimestamp.toLocalDateTime();
        }
    }

    public void setDataConclusaoTimestamp(Timestamp dataConclusaoTimestamp) {
        if (dataConclusaoTimestamp == null) {
            dataConclusao = null;
        } else {
            dataConclusao = dataConclusaoTimestamp.toLocalDateTime();
        }
    }
}
