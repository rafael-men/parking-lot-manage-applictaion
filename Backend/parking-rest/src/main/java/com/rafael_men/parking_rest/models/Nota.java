package com.rafael_men.parking_rest.models;


import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sessao_id", nullable = false, unique = true)
    private SessaoEstacionamento sessao;

    @Column(nullable = false)
    private Instant dataEmissao;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column
    private String pdfPath;

    @PrePersist
    void prePersist() {
        if (dataEmissao == null) dataEmissao = Instant.now();
    }

    public Nota(UUID id, SessaoEstacionamento sessao, Instant dataEmissao, BigDecimal valorTotal, String pdfPath) {
        this.id = id;
        this.sessao = sessao;
        this.dataEmissao = dataEmissao;
        this.valorTotal = valorTotal;
        this.pdfPath = pdfPath;
    }

    public Nota() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SessaoEstacionamento getSessao() {
        return sessao;
    }

    public void setSessao(SessaoEstacionamento sessao) {
        this.sessao = sessao;
    }

    public Instant getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Instant dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Nota nota = (Nota) o;
        return Objects.equals(id, nota.id) && Objects.equals(sessao, nota.sessao) && Objects.equals(dataEmissao, nota.dataEmissao) && Objects.equals(valorTotal, nota.valorTotal) && Objects.equals(pdfPath, nota.pdfPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sessao, dataEmissao, valorTotal, pdfPath);
    }
}

