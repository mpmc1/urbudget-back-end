package com.urbudget.mailsender.mailsender;

import com.urbudget.mailsender.mailsender.services.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MailsenderApplication {
	@Autowired
	EmailSender sender;

	public static void main(String[] args) {
		SpringApplication.run(MailsenderApplication.class, args);
	}

	/*@EventListener(ApplicationReadyEvent.class)
	public void sendEmail(){
		sender.SendEmail("jjcardona1017@gmail.com", "Mensaje automático UrBudget",
				"Este mensaje es un mensaje de pruebas del microservicio de mensajería de UrBudget App." +
						" Por favor no responder.");

	}*/

}
