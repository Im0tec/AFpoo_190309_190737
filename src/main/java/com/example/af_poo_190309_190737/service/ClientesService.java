package com.example.af_poo_190309_190737.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo_190309_190737.model.Clientes;
import com.example.af_poo_190309_190737.repository.ClientesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientesService {
    
    @Autowired
    private ClientesRepository repositorio;

    public List<Clientes> getAllClientes(){
        return repositorio.getAllClientes();
    }

    public Clientes getClienteByCodigo(int codigo){
        Optional<Clientes> op = repositorio.getClienteByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente nao cadastrado"));
    }

    public Clientes save(Clientes cliente){
        return repositorio.save(cliente);
    }

    public void removeByCodigo(int codigo){
        repositorio.remove(getClienteByCodigo(codigo));
    }

    public Clientes update(Clientes cliente){
        getClienteByCodigo(cliente.getCodigo());

        return repositorio.update(cliente);
    }
}
