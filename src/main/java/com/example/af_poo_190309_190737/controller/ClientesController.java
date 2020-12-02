package com.example.af_poo_190309_190737.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.af_poo_190309_190737.DTO.ReservaDTO;
import com.example.af_poo_190309_190737.model.Clientes;
import com.example.af_poo_190309_190737.model.Reserva;
import com.example.af_poo_190309_190737.service.ClientesService;
import com.example.af_poo_190309_190737.service.ReservaService;

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
@RequestMapping("/clientes")
public class ClientesController {
    
    @Autowired
    private ClientesService service;

    @Autowired
    private ReservaService reservaService;

    @GetMapping()
    public List<Clientes> getCliente(){
        return service.getAllClientes();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Clientes> getClienteByCodigo(@PathVariable int codigo){
        Clientes cliente = service.getClienteByCodigo(codigo);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping()
    public ResponseEntity<Clientes> save(@RequestBody Clientes cliente, HttpServletRequest request, UriComponentsBuilder builder){
        cliente = service.save(cliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cliente.getCodigo()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remove(@PathVariable int codigo){
        service.removeByCodigo(codigo);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Clientes> update(@PathVariable int codigo, @RequestBody Clientes cliente){
        cliente.setCodigo(codigo);
        cliente = service.update(cliente);

        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/{codigoCliente}/veiculos/{codigoVeiculo}")
    public ResponseEntity<Reserva> reservar(@PathVariable int cliente, @PathVariable int veiculo, @Valid @RequestBody ReservaDTO reservaDTO, HttpServletRequest request, UriComponentsBuilder builder){
        Reserva reserva = reservaService.save(reservaService.fromDTO(reservaDTO), cliente, veiculo);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + reserva.getNumero()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
