package com.urbudget.apitransaction.mensajeria.budget;

import com.urbudget.apitransaction.domain.budget.Budget;
import com.urbudget.apitransaction.service.budget.BudgetService;
import com.urbudget.apitransaction.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class RecieverMessagesBroker {
    @Autowired
    private BudgetService budgetService;

    private final MapperJsonObjeto mapperJsonObjeto;
    public RecieverMessagesBroker(MapperJsonObjeto mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;

    }

    @RabbitListener(queues = "${budget.queue-recibir.budget.queue-name}")
    public void receiveMessageProcessClient(String message) {
        try {
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private Optional<Budget> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, Budget.class);
    }


}
