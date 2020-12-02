package com.example.af_poo_190309_190737.controller;

import java.util.List;

import com.example.af_poo_190309_190737.DTO.ReservaDTO;
import com.example.af_poo_190309_190737.model.Reserva;
import com.example.af_poo_190309_190737.service.ReservaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getReservas(){
        return reservaService.getAllReservas();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Reserva> getReservaByNumero(@PathVariable int numero){
        Reserva reserva = reservaService.getReservaByNumero(numero);
        return ResponseEntity.ok(reserva);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Reserva> update(@PathVariable int codigo, @RequestBody ReservaDTO dto){
        Reserva reserva = reservaService.fromDTO(dto);
        reserva.setNumero(codigo);
        reserva = reservaService.update(reserva);
        
        return ResponseEntity.ok(reserva);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        reservaService.removeByNumero(codigo);

        return ResponseEntity.noContent().build();
    }
}
