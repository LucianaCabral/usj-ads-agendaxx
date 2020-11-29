package br.edu.usj.ads.lgii.agendax;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContatoRepository extends CrudRepository<Contato, Long> {
    List<Contato> findAll();
    List<Contato>findByNome(String nome);
    List<Contato>findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);
    List<Contato>findByTipoAndNomeContainingIgnoreCase(String tipo, String nome);   
}
