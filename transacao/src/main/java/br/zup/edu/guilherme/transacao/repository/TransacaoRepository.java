package br.zup.edu.guilherme.transacao.repository;

import br.zup.edu.guilherme.transacao.entidades.Transacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransacaoRepository extends CrudRepository<Transacao, Long> {
    List<Transacao> findTop10ByCartaoIdOrderByEfetivadaEmDesc(String idCartao);
}
