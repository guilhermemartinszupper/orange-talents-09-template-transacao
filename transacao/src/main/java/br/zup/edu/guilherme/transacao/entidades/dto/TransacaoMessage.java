package br.zup.edu.guilherme.transacao.entidades.dto;

import br.zup.edu.guilherme.transacao.entidades.acomplamento.Cartao;
import br.zup.edu.guilherme.transacao.entidades.acomplamento.Estabelecimento;

import java.time.LocalDateTime;

public class TransacaoMessage {
    private String id;
    private Estabelecimento estabelecimento;
    private Cartao cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoMessage() {
    }

    public TransacaoMessage(String numeroTransacao, Estabelecimento estabelecimento, Cartao cartao, LocalDateTime efetivadaEm) {
        this.id = numeroTransacao;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }
}
