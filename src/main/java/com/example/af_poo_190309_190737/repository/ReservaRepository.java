package com.example.af_poo_190309_190737.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo_190309_190737.model.Reserva;

import org.springframework.stereotype.Component;

@Component
public class ReservaRepository {
    
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private int nextCode = 1;

    public List<Reserva> getlAllReservas(){
        return reservas;
    }

    public Optional<Reserva> getReservaByCodigo(int codigo){
        for(Reserva reserva: reservas){
            if(reserva.getNumero() == codigo){
                return Optional.of(reserva);
            }
        }
        return Optional.empty();
    }

    public Reserva save(Reserva reserva){
        reserva.setNumero(nextCode++);
        reservas.add(reserva);

        return reserva;
    }

    public void remove(Reserva reserva){
        reservas.remove(reserva);
    }

    public Reserva update(Reserva reserva){
        Reserva aux = getReservaByCodigo(reserva.getNumero()).get();

        if(aux != null){
            aux.setData_inicio(reserva.getData_inicio());
            aux.setData_final(reserva.getData_final());
        }

        return aux;
    }
}
