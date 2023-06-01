package com.urbudget.apitransaction.mensajeria.budget;

import com.urbudget.apitransaction.domain.transaction.Transaction;
import org.springframework.amqp.core.MessageProperties;
import com.urbudget.apitransaction.config.TransactionQueueConfig;
import com.urbudget.apitransaction.util.MessageSender;
import com.urbudget.apitransaction.util.gson.MapperJsonObjeto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageSenderBroker implements MessageSender<Transaction> {

    private final RabbitTemplate rabbitTemplate;
    private final MapperJsonObjeto mapperJsonObjeto;
    private final TransactionQueueConfig transactionQueueConfig;

    public MessageSenderBroker(RabbitTemplate rabbitTemplate, MapperJsonObjeto mapperJsonObjeto, TransactionQueueConfig clientQueueConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.transactionQueueConfig = clientQueueConfig;
    }

    @Override
    public void execute(Transaction message, String idMessage) {
        MessageProperties propiedadesMensaje = generarPropiedadesMensaje(idMessage);

        Optional<Message> cuerpoMensaje = obtenerCuerpoMensaje(message, propiedadesMensaje);
        if (!cuerpoMensaje.isPresent()) {
            return;
        }
        rabbitTemplate.convertAndSend(transactionQueueConfig.getExchangeName(), transactionQueueConfig.getRoutingKeyName(), cuerpoMensaje.get());
    }

    private MessageProperties generarPropiedadesMensaje(String idMessageSender) {
        return MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("idMensaje", idMessageSender)
                .build();
    }

    private Optional<Message> obtenerCuerpoMensaje(Object mensaje, MessageProperties propiedadesMensaje) {
        Optional<String> textoMensaje = mapperJsonObjeto.ejecutarGson(mensaje);

        return textoMensaje.map(msg -> MessageBuilder
                .withBody(msg.getBytes())
                .andProperties(propiedadesMensaje)
                .build());

    }
}

