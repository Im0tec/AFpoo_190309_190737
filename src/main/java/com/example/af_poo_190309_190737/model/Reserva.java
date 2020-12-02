/*
 * Dupla:
 * Eduardo Campos Gonçalves - 190309
 * Johanna Bernecker - 190737
 * 
 * Turma: CP107TIN1
 */
package com.example.af_poo_190309_190737.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Reserva {
    private int numero;
    private Clientes cliente;
    private Veiculos veiculo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_inicio;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_final;
    
    private double total;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Veiculos getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculos veiculo) {
        this.veiculo = veiculo;
    }
    
    public LocalDate getData_inicio() {
        return data_inicio;
    }
    
    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }
    
    public LocalDate getData_final() {
        return data_final;
    }
    
    public void setData_final(LocalDate data_final) {
        this.data_final = data_final;
    }
    
    public double getTotal() {
        return total();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double total(){
        double periodo = data_inicio.until(data_final, ChronoUnit.DAYS);
        total = getVeiculo().getDiaria() * periodo;

        return total;
    }

    @Override
    public String toString() {
        return "Reserva [cliente=" + cliente + ", data_final=" + data_final + ", data_inicio=" + data_inicio
                + ", numero=" + numero + ", total=" + total + ", veiculo=" + veiculo + "]";
    }
    
}
