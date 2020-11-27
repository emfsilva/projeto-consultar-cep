package io.github.emfsilva.projeto_culsulta_cep.dto.response;

import io.github.emfsilva.projeto_culsulta_cep.model.Cep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CepResponseDTO {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String unidade;

    public CepResponseDTO(Cep cep){
        this.cep = cep.getCep();
        this.logradouro = cep.getLogradouro();
        this.complemento = cep.getComplemento();
        this.bairro = cep.getBairro();
        this.localidade = cep.getLocalidade();
        this.uf = cep.getUf();
        this.unidade = cep.getUnidade();

    }
}
