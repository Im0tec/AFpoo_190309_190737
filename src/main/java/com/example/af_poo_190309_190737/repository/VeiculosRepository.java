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

import com.example.af_poo_190309_190737.model.Veiculos;

import org.springframework.stereotype.Component;

@Component
public class VeiculosRepository {

    private List<Veiculos> veiculos;
    private int nextCode;
    private int delCode = -1;

    @PostConstruct
    public void criarVeiculos(){

        Veiculos v1 = new Veiculos();
        Veiculos v2 = new Veiculos();
        Veiculos v3 = new Veiculos();

        v1.setCodigo(1);
        v1.setDiaria(200);
        v1.setModelo("Ford Fiesta");

        v2.setCodigo(2);
        v2.setDiaria(38400);
        v2.setModelo("Ferrari 360 Modena");

        v3.setCodigo(3);
        v3.setDiaria(299.50);
        v3.setModelo("Corolla");

        nextCode = 4;

        veiculos = new ArrayList<Veiculos>();

        veiculos.add(v1);
        veiculos.add(v2);
        veiculos.add(v3);
    }

    public List<Veiculos> getAllVeiculos(){
        return veiculos;
    }

    public Optional<Veiculos> getVeiculoByCodigo(int codigo){

        for(Veiculos veiculo: veiculos){
            if(veiculo.getCodigo() == codigo){
                return Optional.of(veiculo);
            }
        }
        return Optional.empty();
    }

    public Veiculos save(Veiculos veiculo){
        if(delCode != -1){
            veiculo.setCodigo(delCode);
            delCode = -1;
        }
        else{
            veiculo.setCodigo(nextCode++);
        }
        veiculos.add(veiculo);
        return veiculo;
    }

    public void remove(Veiculos veiculo){
        delCode = veiculo.getCodigo();
        veiculos.remove(veiculo);
    }

    public Veiculos update(Veiculos veiculo){
        Veiculos aux = getVeiculoByCodigo(veiculo.getCodigo()).get();

        if(aux != null){
            aux.setDiaria(veiculo.getDiaria());
            aux.setModelo(veiculo.getModelo());
        }
        return aux;
    }

}