package com.rafael_men.parking_rest.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTO")
public class Moto extends Veículo {
    // modelo/placa/vagaAtual herdados
}

