package io.github.emfsilva.projeto_culsulta_cep.controller;

import io.github.emfsilva.projeto_culsulta_cep.model.Cep;
import io.github.emfsilva.projeto_culsulta_cep.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cep")
public class CepController {

    @Autowired
    CepRepository cepRepository;

    @GetMapping("/{cep}")
    public Cep retornaCep(@PathVariable String cep){
        return cepRepository.getByCep(cep);
    }

    @GetMapping
    public List<Cep> retornaTodosCep(){
        return cepRepository.findAll();
    }

    @PostMapping
    public Cep retornaCep(@RequestBody Cep cep){
        this.cepRepository.save(cep);
        return cep;
    }
}
