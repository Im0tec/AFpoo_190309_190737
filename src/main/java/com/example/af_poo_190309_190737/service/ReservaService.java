package com.example.af_poo_190309_190737.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.af_poo_190309_190737.DTO.ReservaDTO;
import com.example.af_poo_190309_190737.model.Clientes;
import com.example.af_poo_190309_190737.model.Reserva;
import com.example.af_poo_190309_190737.model.Veiculos;
import com.example.af_poo_190309_190737.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private VeiculosService veiculosService;

    @Autowired
    private ClientesService clientesService;

    public Reserva fromDTO(ReservaDTO dto){
        Reserva reserva = new Reserva();
        reserva.setData_inicio(dto.getData_inicio());
        reserva.setData_final(dto.getData_final());

        return reserva;
    }

    public List<Reserva> getAllReservas(){
        return reservaRepository.getlAllReservas();
    }

    public Reserva getReservaByNumero(int numero){
        Optional<Reserva> op = reservaRepository.getReservaByCodigo(numero);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva nao registrada!"));
    }

    public Reserva save(Reserva reserva, int codCliente, int codVeiculo){
        Clientes cliente = clientesService.getClienteByCodigo(codCliente);
        Veiculos veiculo = veiculosService.getVeiculoByCodigo(codVeiculo);
        checkData(reserva.getData_inicio(), reserva.getData_final(), reserva, veiculo.getReservas());

        reserva.setVeiculo(veiculo);
        reserva.setCliente(cliente);
        veiculo.reservar(reserva);
        cliente.reservar(reserva);

        return saveInList(reserva);
    }

    public Reserva saveInList(Reserva reserva){
        return reservaRepository.save(reserva);
    }

    public void removeByNumero(int numero){
        reservaRepository.remove(getReservaByNumero(numero));
    }

    public void remove(Reserva reserva){
        reservaRepository.remove(reserva);
    }

    public Reserva update(Reserva reserva){
        Reserva compare = getReservaByNumero(reserva.getNumero());
        checkData(compare.getData_inicio(), reserva.getData_final(), compare, compare.getVeiculo().getReservas());

        return reservaRepository.update(reserva);
    }

    public void checkData(LocalDate inicio, LocalDate fim, Reserva reserva, List<Reserva> reservas){
        for(Reserva aux: reservas){
            if(aux != reserva){
                if(!(inicio.compareTo(aux.getData_inicio()) < 0 && fim.compareTo(aux.getData_inicio()) < 0) && !(fim.compareTo(aux.getData_final()) > 0 && inicio.compareTo(aux.getData_final()) > 0)){
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operação inválida! Veículo já reservado...");
                }
            }
        }
        
        if(inicio.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operação inválida! Não alugamos carros aos domingos...");
        }

        if(fim.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operação inválida! Não recebemos carros aos domingos...");
        }

        if(inicio.compareTo(fim) > 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Operação inválida! O período de reserva informado não obecedece às retrições exigidas. Verifique se a data final é menor que a data inicial!");
        }
    }

    public ReservaDTO toDTO(Reserva reserva){
        ReservaDTO dto = new ReservaDTO();
        dto.setData_inicio(reserva.getData_inicio());
        dto.setData_final(reserva.getData_final());

        return dto;
    }

    public List<ReservaDTO> toListDTO(List<Reserva> reservas){
        ArrayList<ReservaDTO> listDTO = new ArrayList<ReservaDTO>();

        for(Reserva reserva: reservas){
            listDTO.add(toDTO(reserva));
        }

        return listDTO;
    }
}
