package com.rafael_men.parking_rest.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "config")
public class Config {

    @Id
    private Long id = 1L;

    @Column(nullable = false)
    private boolean permitirMotoEmVagaCarro = false;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifaCarro = new BigDecimal("5.00");

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifaMoto = new BigDecimal("2.00");

    @Column(nullable = false, length = 10)
    private String regraArredondamento = "UP";

    public Config(Long id, boolean permitirMotoEmVagaCarro, BigDecimal tarifaCarro, BigDecimal tarifaMoto, String regraArredondamento) {
        this.id = id;
        this.permitirMotoEmVagaCarro = permitirMotoEmVagaCarro;
        this.tarifaCarro = tarifaCarro;
        this.tarifaMoto = tarifaMoto;
        this.regraArredondamento = regraArredondamento;
    }

    public Config() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPermitirMotoEmVagaCarro() {
        return permitirMotoEmVagaCarro;
    }

    public void setPermitirMotoEmVagaCarro(boolean permitirMotoEmVagaCarro) {
        this.permitirMotoEmVagaCarro = permitirMotoEmVagaCarro;
    }

    public BigDecimal getTarifaCarro() {
        return tarifaCarro;
    }

    public void setTarifaCarro(BigDecimal tarifaCarro) {
        this.tarifaCarro = tarifaCarro;
    }

    public String getRegraArredondamento() {
        return regraArredondamento;
    }

    public void setRegraArredondamento(String regraArredondamento) {
        this.regraArredondamento = regraArredondamento;
    }

    public BigDecimal getTarifaMoto() {
        return tarifaMoto;
    }

    public void setTarifaMoto(BigDecimal tarifaMoto) {
        this.tarifaMoto = tarifaMoto;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return permitirMotoEmVagaCarro == config.permitirMotoEmVagaCarro && Objects.equals(id, config.id) && Objects.equals(tarifaCarro, config.tarifaCarro) && Objects.equals(tarifaMoto, config.tarifaMoto) && Objects.equals(regraArredondamento, config.regraArredondamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, permitirMotoEmVagaCarro, tarifaCarro, tarifaMoto, regraArredondamento);
    }
}
