/*
 * Dupla:
 * Eduardo Campos Gonçalves - 190309
 * Johanna Bernecker - 190737
 * 
 * Turma: CP107TIN1
 */
package com.example.af_poo_190309_190737.model;

import java.util.ArrayList;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class Clientes {

    private int codigo;

    @Length(min = 3, max = 100, message = "Nome requere no minimo 4 caracteres e no maximo 100")
    @NotBlank(message = "Digite um nome")
    private String nome;

    @Length(min = 6, max = 100, message = "Endereco requere no minimo 6 caracteres e no maximo 100")
    @NotBlank(message = "Digite um endereço")
    private String endereco;

    @CPF
    @NotBlank(message = "Digite um CPF")
    private String cpf;

    @JsonIgnore
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        return "Clientes [codigo=" + codigo + ", cpf=" + cpf + ", endereco=" + endereco + ", nome=" + nome
                + ", reservas=" + reservas + "]";
    }
}
