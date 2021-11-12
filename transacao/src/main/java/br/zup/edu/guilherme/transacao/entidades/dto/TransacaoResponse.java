package br.zup.edu.guilherme.transacao.entidades.dto;

import br.zup.edu.guilherme.transacao.entidades.Transacao;
import br.zup.edu.guilherme.transacao.entidades.acomplamento.Estabelecimento;

import javax.persistence.Embedded;
import java.time.LocalDateTime;

public class TransacaoResponse {
    private Long id;
    private String idTransacao;
    @Embedded
    private Estabelecimento estabelecimento;
    private LocalDateTime efetivadaEm;

    public TransacaoResponse(Transacao transacao) {
        this.id = transacao.getIdDb();
        this.idTransacao = transacao.getIdTransacao();
        this.estabelecimento = transacao.getEstabelecimento();
        this.efetivadaEm = transacao.getEfetivadaEm();
    }

    public Long getId() {
        return id;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
