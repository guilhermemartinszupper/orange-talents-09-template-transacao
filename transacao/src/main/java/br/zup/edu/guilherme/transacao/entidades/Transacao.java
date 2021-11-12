package br.zup.edu.guilherme.transacao.entidades;

import br.zup.edu.guilherme.transacao.entidades.acomplamento.Cartao;
import br.zup.edu.guilherme.transacao.entidades.acomplamento.Estabelecimento;
import br.zup.edu.guilherme.transacao.entidades.dto.TransacaoMessage;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDb;
    private String idTransacao;
    @Embedded
    private Estabelecimento estabelecimento;
    @AttributeOverrides(
            @AttributeOverride(name = "id",
                    column = @Column(name = "id_cartao"))
    )
    @Embedded
    private Cartao cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao() {
    }

    public Transacao(TransacaoMessage transacaoMessage) {
        this.idTransacao = transacaoMessage.getId();
        this.estabelecimento = transacaoMessage.getEstabelecimento();
        this.cartao = transacaoMessage.getCartao();
        this.efetivadaEm = transacaoMessage.getEfetivadaEm();
    }

    public Long getIdDb() {
        return idDb;
    }

    public String getIdTransacao() {
        return idTransacao;
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
