package br.zup.edu.guilherme.transacao.repository;

import br.zup.edu.guilherme.transacao.entidades.Transacao;
import org.springframework.data.repository.CrudRepository;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {
}
