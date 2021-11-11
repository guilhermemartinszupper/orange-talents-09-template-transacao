package br.zup.edu.guilherme.transacao.listener;

import br.zup.edu.guilherme.transacao.entidades.Transacao;
import br.zup.edu.guilherme.transacao.entidades.dto.TransacaoMessage;
import br.zup.edu.guilherme.transacao.repository.TransacaoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerDeTransacao {

    @Autowired
    private TransacaoRepository transacaoRepository;

    private final Logger logger = LoggerFactory.getLogger(ListenerDeTransacao.class);

    @KafkaListener(topics = "${spring.kafka.topic.transactions}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {"spring.json.value.default.type=br.zup.edu.guilherme.transacao.entidades.dto.TransacaoMessage"})
    public void ouvir(ConsumerRecord<String, TransacaoMessage> record){
        try{
            Transacao transacao = new Transacao(record.value());
            transacaoRepository.save(transacao);
            logger.info("Transacao id={} salva",transacao.getIdTransacao());
        }catch (Exception e){
            logger.error("Erro ao consumir transacao");
            logger.error("Erro: " + e.getMessage());
        }
    }
}
