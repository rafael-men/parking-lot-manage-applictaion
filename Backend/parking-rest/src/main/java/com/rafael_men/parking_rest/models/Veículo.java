package com.rafael_men.parking_rest.models;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "veiculos",
        indexes = {
                @Index(name = "uk_veiculo_placa", columnList = "placa", unique = true)
        })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_veiculo", length = 10)
public abstract class Veículo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 10, unique = true)
    private String placa;

    @Column(nullable = false)
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_atual_id")
    private Vaga vagaAtual;

    @Column(nullable = false)
    private boolean ativo = true;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void prePersist() {
        var now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

    public Veículo(UUID id, String placa, String modelo, boolean ativo, Vaga vagaAtual, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.ativo = ativo;
        this.vagaAtual = vagaAtual;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Veículo() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Vaga getVagaAtual() {
        return vagaAtual;
    }

    public void setVagaAtual(Vaga vagaAtual) {
        this.vagaAtual = vagaAtual;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Veículo veículo = (Veículo) o;
        return ativo == veículo.ativo && Objects.equals(id, veículo.id) && Objects.equals(placa, veículo.placa) && Objects.equals(modelo, veículo.modelo) && Objects.equals(vagaAtual, veículo.vagaAtual) && Objects.equals(createdAt, veículo.createdAt) && Objects.equals(updatedAt, veículo.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, placa, modelo, vagaAtual, ativo, createdAt, updatedAt);
    }
}