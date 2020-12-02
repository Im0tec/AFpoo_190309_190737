/*
 * Dupla:
 * Eduardo Campos Gonçalves - 190309
 * Johanna Bernecker - 190737
 * 
 * Turma: CP107TIN1
 */
package com.example.af_poo_190309_190737.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaDTO {
    @Future(message = "A data inicial precisa ser maior que a do sistema!")
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_inicio;

    @Future(message = "A data final precisa ser maior que a do sistema!")
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_final;

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

}
