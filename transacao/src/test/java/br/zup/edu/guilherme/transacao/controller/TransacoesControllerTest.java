package br.zup.edu.guilherme.transacao.controller;

import br.zup.edu.guilherme.transacao.entidades.Transacao;
import br.zup.edu.guilherme.transacao.entidades.acomplamento.Cartao;
import br.zup.edu.guilherme.transacao.entidades.acomplamento.Estabelecimento;
import br.zup.edu.guilherme.transacao.entidades.dto.TransacaoMessage;
import br.zup.edu.guilherme.transacao.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class TransacoesControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    TransacaoRepository repository;
    String URI = "/transacoes/cartoes";

    @BeforeEach()
    void setUp(){
        Transacao transacao = new Transacao(new TransacaoMessage("12345",
                new Estabelecimento("Lojinha da Esquina","Sao Paulo","Uma Rua qualquer"),
                new Cartao("1234-5678-8910-1112","email@teste.com"),LocalDateTime.now()));
        repository.save(transacao);
        URI += "/1234-5678-8910-1112";
    }

    @Test
    void deveRetornar200UmaListaDeTransacoesCasoCartaoTenhaTransacoes() throws Exception {
        MockHttpServletRequestBuilder consultaRequest = MockMvcRequestBuilders.get(URI);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void deveRetornar404CasoCartaoNaoExistaOuNaoTenhaTransacoes() throws Exception {
        MockHttpServletRequestBuilder consultaRequest = MockMvcRequestBuilders.get(URI + "404");
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}