package io.github.emfsilva.projeto_culsulta_cep.controller;

import io.github.emfsilva.projeto_culsulta_cep.dto.request.CepRequestDTO;
import io.github.emfsilva.projeto_culsulta_cep.dto.response.CepResponseDTO;
import io.github.emfsilva.projeto_culsulta_cep.model.ApiMessage;
import io.github.emfsilva.projeto_culsulta_cep.model.Cep;
import io.github.emfsilva.projeto_culsulta_cep.repository.CepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/cep")
public class CepController {

    @Autowired
    CepRepository cepRepository;

    @GetMapping("/{cep}")
    public ResponseEntity<Object> retornaCep(@PathVariable String cep) {
        Cep cepObject = cepRepository.getByCep(cep);
        if (cepObject != null) {
            return new ResponseEntity<>(new CepResponseDTO(cepRepository.getByCep(cep)), HttpStatus.OK);
        } else {
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            RestTemplate restTemplate = new RestTemplate();
            try {
                Cep cepResponse = restTemplate.getForObject(url, Cep.class);
                this.cepRepository.save(cepResponse);
                return new ResponseEntity<>(new CepResponseDTO(cepResponse), HttpStatus.OK);
            } catch (HttpClientErrorException httpClientErrorException){
                return new ResponseEntity<>(new ApiMessage(httpClientErrorException.getMessage()),
                        httpClientErrorException.getStatusCode());
            }
        }
    }

    @GetMapping
    public List<Cep> retornaTodosCeps() {
        return cepRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> cadastraCep(@RequestBody CepRequestDTO cepRequestDTO) {
        if (cepRequestDTO.getCep() == "" || cepRequestDTO.getCep() == null) {
            return new ResponseEntity<>(new ApiMessage("Favor informar o cep"), HttpStatus.BAD_REQUEST);
        } else {
            Cep cep = this.cepRepository.save(cepRequestDTO.build());
            return new ResponseEntity<>(cep, HttpStatus.CREATED);
        }
    }
}
