package io.github.emfsilva.projeto_culsulta_cep.controller;

import io.github.emfsilva.projeto_culsulta_cep.dto.request.CepRequestDTO;
import io.github.emfsilva.projeto_culsulta_cep.dto.response.CepResponseDTO;
import io.github.emfsilva.projeto_culsulta_cep.model.Cep;
import io.github.emfsilva.projeto_culsulta_cep.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cep")
public class CepController {

    @Autowired
    CepRepository cepRepository;

    @GetMapping("/{cep}")
    public CepResponseDTO retornaCep(@PathVariable String cep){
        return new CepResponseDTO(cepRepository.getByCep(cep));
    }

    @GetMapping
    public List<Cep> retornaTodosCeps(){
        return cepRepository.findAll();
    }

    @PostMapping
    public CepRequestDTO cadastraCep(@RequestBody CepRequestDTO cepRequestDTO){
        this.cepRepository.save(cepRequestDTO.build());
        return cepRequestDTO;
    }
}
