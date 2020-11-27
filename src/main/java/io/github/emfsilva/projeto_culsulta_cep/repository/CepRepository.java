package io.github.emfsilva.projeto_culsulta_cep.repository;

import io.github.emfsilva.projeto_culsulta_cep.model.Cep;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepRepository extends MongoRepository<Cep, String> {
    public Cep getByCep(String cep);
}
