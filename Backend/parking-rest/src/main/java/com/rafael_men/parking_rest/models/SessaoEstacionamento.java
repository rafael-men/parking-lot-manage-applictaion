package com.rafael_men.parking_rest.models;

import com.rafael_men.parking_rest.models.enums.StatusSessao;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "sessoes",
        indexes = {
                @Index(name = "idx_sessao_status", columnList = "status"),
                @Index(name = "idx_sessao_veiculo_status", columnList = "veiculo_id,status")
        })
public class SessaoEstacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veículo veiculo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private StatusSessao status = StatusSessao.ABERTA;

    @Column(nullable = false)
    private Instant inicio;

    @Column
    private Instant fim;

    @Column
    private Integer minutosCobrados;

    @Column(precision = 10, scale = 2)
    private BigDecimal tarifaPorMinuto;

    @Column(precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @OneToOne(mappedBy = "sessao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Nota nota;

    @PrePersist
    void prePersist() {
        if (inicio == null) inicio = Instant.now();
    }

    public SessaoEstacionamento(UUID id, Veículo veiculo, Vaga vaga, StatusSessao status, Instant inicio, Instant fim, Integer minutosCobrados, BigDecimal tarifaPorMinuto, BigDecimal valorTotal, Nota nota) {
        this.id = id;
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.status = status;
        this.inicio = inicio;
        this.fim = fim;
        this.minutosCobrados = minutosCobrados;
        this.tarifaPorMinuto = tarifaPorMinuto;
        this.valorTotal = valorTotal;
        this.nota = nota;
    }

    public SessaoEstacionamento() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Veículo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veículo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public StatusSessao getStatus() {
        return status;
    }

    public void setStatus(StatusSessao status) {
        this.status = status;
    }

    public Instant getInicio() {
        return inicio;
    }

    public void setInicio(Instant inicio) {
        this.inicio = inicio;
    }

    public Instant getFim() {
        return fim;
    }

    public void setFim(Instant fim) {
        this.fim = fim;
    }

    public Integer getMinutosCobrados() {
        return minutosCobrados;
    }

    public void setMinutosCobrados(Integer minutosCobrados) {
        this.minutosCobrados = minutosCobrados;
    }

    public BigDecimal getTarifaPorMinuto() {
        return tarifaPorMinuto;
    }

    public void setTarifaPorMinuto(BigDecimal tarifaPorMinuto) {
        this.tarifaPorMinuto = tarifaPorMinuto;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SessaoEstacionamento that = (SessaoEstacionamento) o;
        return Objects.equals(id, that.id) && Objects.equals(veiculo, that.veiculo) && Objects.equals(vaga, that.vaga) && status == that.status && Objects.equals(inicio, that.inicio) && Objects.equals(fim, that.fim) && Objects.equals(minutosCobrados, that.minutosCobrados) && Objects.equals(tarifaPorMinuto, that.tarifaPorMinuto) && Objects.equals(valorTotal, that.valorTotal) && Objects.equals(nota, that.nota);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, veiculo, vaga, status, inicio, fim, minutosCobrados, tarifaPorMinuto, valorTotal, nota);
    }
}
