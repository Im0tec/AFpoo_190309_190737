/*
 * Dupla:
 * Eduardo Campos Gonçalves - 190309
 * Johanna Bernecker - 190737
 * 
 * Turma: CP107TIN1
 */
package com.example.af_poo_190309_190737.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.af_poo_190309_190737.model.Clientes;

import org.springframework.stereotype.Component;

@Component
public class ClientesRepository {
    private List<Clientes> clientes;
    private int nextCode;

    @PostConstruct
    public void criarClientes(){

        Clientes c1 = new Clientes();
        Clientes c2 = new Clientes();

        c1.setCodigo(1);
        c1.setNome("Eduardo");
        c1.setCpf("365.127.321.00");
        c1.setEndereco("Rua dos bobos, 0");

        c2.setCodigo(2);
        c2.setNome("Fabiana");
        c2.setCpf("999.111.222.33");
        c2.setEndereco("Dom Pedro II, 135");

        nextCode = 3;
        clientes = new ArrayList<Clientes>();

        clientes.add(c1);
        clientes.add(c2);
    }

    public List<Clientes> getAllClientes(){
        return clientes;
    }

    public Optional<Clientes> getClienteByCodigo(int codigo){
        for(Clientes cliente: clientes){
            if(cliente.getCodigo() == codigo){
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    public Clientes save(Clientes cliente){
        cliente.setCodigo(nextCode++);
        clientes.add(cliente);
        return cliente;
    }

    public void remove(Clientes cliente){
        clientes.remove(cliente);
    }

    public Clientes update(Clientes cliente){
        Clientes aux = getClienteByCodigo(cliente.getCodigo()).get();
        
        if(aux != null){
            aux.setNome(cliente.getNome());
            aux.setCpf(cliente.getCpf());
            aux.setEndereco(cliente.getEndereco());
        }
        
        return aux;
    }

}
