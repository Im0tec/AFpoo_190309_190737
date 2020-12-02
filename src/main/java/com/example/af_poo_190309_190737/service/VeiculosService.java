package com.example.af_poo_190309_190737.service;

import java.util.List;
import java.util.Optional;

import com.example.af_poo_190309_190737.model.Veiculos;
import com.example.af_poo_190309_190737.repository.VeiculosRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculosService {
    
    @Autowired
    private VeiculosRepository repositorio;

    public List<Veiculos> getAllVeiculos(){
        return repositorio.getAllVeiculos();
    }

    public Veiculos getVeiculoByCodigo(int codigo){
        Optional<Veiculos> op = repositorio.getVeiculoByCodigo(codigo);
        return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veiculo nao cadastrado! "));
    }

    public Veiculos save(Veiculos veiculo){
        return repositorio.save(veiculo);
    }

    public void removeByCodigo(int codigo){
        repositorio.remove(getVeiculoByCodigo(codigo));
    }

    public Veiculos update(Veiculos veiculo){
        getVeiculoByCodigo(veiculo.getCodigo());
        return repositorio.update(veiculo);
    }
    
}
