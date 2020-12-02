package com.example.af_poo_190309_190737.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.af_poo_190309_190737.model.Reserva;
import com.example.af_poo_190309_190737.model.Veiculos;
import com.example.af_poo_190309_190737.service.VeiculosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/veiculos")
public class VeiculosController {
    
    @Autowired
    private VeiculosService veiculoservice;

    @GetMapping()
    public List<Veiculos> getVeiculos(){
        return veiculoservice.getAllVeiculos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Veiculos> getVeiculoByCodigo(@PathVariable int codigo){
        Veiculos veiculo = veiculoservice.getVeiculoByCodigo(codigo);

        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("/{codigo}/reservas")
    public List<Reserva> reservasByCliente(@PathVariable int codigo){
        Veiculos veiculo = veiculoservice.getVeiculoByCodigo(codigo);
        
        return veiculo.getReservas();
    }
    
    @PostMapping()
    public ResponseEntity<Veiculos> save(@RequestBody Veiculos veiculo, HttpServletRequest request, UriComponentsBuilder builder){
        veiculo = veiculoservice.save(veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + veiculo.getCodigo()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        veiculoservice.removeByCodigo(codigo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Veiculos> update(@PathVariable int codigo, @RequestBody Veiculos veiculo){
        veiculo.setCodigo(codigo);
        veiculo = veiculoservice.update(veiculo);

        return ResponseEntity.ok(veiculo);
    }

}
