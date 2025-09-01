package com.rafael_men.parking_rest.models;

import com.rafael_men.parking_rest.models.enums.TamanhoVaga;
import com.rafael_men.parking_rest.models.enums.TipoVaga;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "vagas",
        indexes = {
                @Index(name = "idx_vaga_disp_tipo_tam", columnList = "disponivel,tipo,tamanho"),
                @Index(name = "uk_vaga_numero", columnList = "numero", unique = true)
        })
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 10)
    private TamanhoVaga tamanho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 10)
    private TipoVaga tipo;

    @Column(nullable = false)
    private boolean disponivel = true;

    @Version
    private long version;

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

    public Vaga(UUID id, Integer numero, TipoVaga tipo, TamanhoVaga tamanho, long version, boolean disponivel, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.version = version;
        this.disponivel = disponivel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Vaga() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public TamanhoVaga getTamanho() {
        return tamanho;
    }

    public void setTamanho(TamanhoVaga tamanho) {
        this.tamanho = tamanho;
    }

    public TipoVaga getTipo() {
        return tipo;
    }

    public void setTipo(TipoVaga tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vaga vaga = (Vaga) o;
        return disponivel == vaga.disponivel && version == vaga.version && Objects.equals(id, vaga.id) && Objects.equals(numero, vaga.numero) && tamanho == vaga.tamanho && tipo == vaga.tipo && Objects.equals(createdAt, vaga.createdAt) && Objects.equals(updatedAt, vaga.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero, tamanho, tipo, disponivel, version, createdAt, updatedAt);
    }
}
