package br.zup.edu.guilherme.transacao.controller;

import br.zup.edu.guilherme.transacao.entidades.Transacao;
import br.zup.edu.guilherme.transacao.entidades.dto.TransacaoResponse;
import br.zup.edu.guilherme.transacao.repository.TransacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes")
public class TransacoesController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    private final Logger logger = LoggerFactory.getLogger(TransacoesController.class);

    @GetMapping("/cartoes/{idCartao}")
    public ResponseEntity<List<TransacaoResponse>> ultimasTransacoes(@PathVariable String idCartao){
        String cartaoOfuscado = idCartao.substring(idCartao.length() - 4);
            List<Transacao> transacoes = transacaoRepository.findTop10ByCartaoIdOrderByEfetivadaEmDesc(idCartao);
            logger.info("Realizando busca de transacoes para o cartao: {}", cartaoOfuscado);
            if(transacoes.isEmpty()){
                logger.error("Nao foi encontrada nenhuma para transacao para o cartao: {}",cartaoOfuscado);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma Transacao encontrada para esse cartao");
            }
            logger.info("Encontradas {} transacoes para o cartao = {}",transacoes.size(),cartaoOfuscado);
            return ResponseEntity.ok().body(transacoes.stream().map(TransacaoResponse::new).collect(Collectors.toList()));
    }
}
