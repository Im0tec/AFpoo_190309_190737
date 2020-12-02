package com.example.af_poo_190309_190737.model;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Length;

public class Veiculos {

    private int codigo;

    @NotBlank(message = "Digite o modelo do carro")
    @Length(min = 3, max = 20, message = "Modelo exige umm minimo de 3 caracteres e maximo de 20")
    private String modelo;
    private double diaria;

    @JsonIgnore
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getDiaria() {
        return diaria;
    }

    public void setDiaria(double diaria) {
        this.diaria = diaria;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public void reservar(Reserva reserva){
        reservas.add(reserva);
    }

    public void encerrarReserva(Reserva reserva){
        reservas.remove(reserva);
    }

    @Override
    public String toString() {
        return "Veiculos [codigo=" + codigo + ", diaria=" + diaria + ", modelo=" + modelo + ", reservas=" + reservas
                + "]";
    }
    
}
