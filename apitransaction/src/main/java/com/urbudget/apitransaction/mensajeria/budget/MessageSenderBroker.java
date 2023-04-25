package com.urbudget.apitransaction.mensajeria.budget;

import org.springframework.amqp.core.MessageProperties;
import com.urbudget.apitransaction.config.BudgetQueueConfig;
import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.util.MessageSender;
import com.urbudget.apitransaction.util.gson.MapperJsonObjeto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageSenderBroker implements MessageSender<Budget> {

    private final RabbitTemplate rabbitTemplate;
    private final MapperJsonObjeto mapperJsonObjeto;
    private final BudgetQueueConfig budgetQueueConfig;

    public MessageSenderBroker(RabbitTemplate rabbitTemplate, MapperJsonObjeto mapperJsonObjeto, BudgetQueueConfig clientQueueConfig) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.budgetQueueConfig = clientQueueConfig;
    }

    @Override
    public void execute(Budget message, String idMessage) {
        MessageProperties propiedadesMensaje = generarPropiedadesMensaje(idMessage);

        Optional<Message> cuerpoMensaje = obtenerCuerpoMensaje(message, propiedadesMensaje);
        if (!cuerpoMensaje.isPresent()) {
            return;
        }
        rabbitTemplate.convertAndSend(budgetQueueConfig.getExchangeName(), budgetQueueConfig.getRoutingKeyName(), cuerpoMensaje.get());
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

