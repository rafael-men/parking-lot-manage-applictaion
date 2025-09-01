package com.rafael_men.parking_rest.models;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veículo {
    // modelo/placa/vagaAtual herdados
}