package com.urbudget.mailsender.mailsender.messaging;

import com.urbudget.mailsender.mailsender.domain.transaction.Transaction;
import com.urbudget.mailsender.mailsender.services.EmailSender;
import com.urbudget.mailsender.mailsender.utils.CustomException;
import com.urbudget.mailsender.mailsender.utils.gson.MapperJsonObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class Reciever {

    @Autowired
    EmailSender sender;
    private final MapperJsonObject mapperJsonObjeto;

    public Reciever(MapperJsonObject mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;
    }


    @RabbitListener(queues = "${budget.queue-recibir.budget.queue-name}")
    public void receiveMessageProcessClient(String message) {
        try {
            Transaction transaction = getObjectFromMessage(message).orElseThrow();
            String body = "Registered new transction for: "+transaction.getDescription()+ ".\nAnd a value of" +
                    ": "+ transaction.getAmmount() + ".\nIf it wasn't you, please contact to the call center";
            sender.SendEmail(transaction.getBudget().getPerson().getEmail(),"Nueva transacción registrada", body);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<Transaction> getObjectFromMessage(String message) {
        return mapperJsonObjeto.execute(message, Transaction.class);
    }
}
