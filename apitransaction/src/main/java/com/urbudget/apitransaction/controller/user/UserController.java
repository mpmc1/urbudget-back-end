package com.urbudget.apitransaction.controller.user;

import com.urbudget.apitransaction.domain.Patch;
import com.urbudget.apitransaction.domain.Person;
import com.urbudget.apitransaction.domain.Response;
import com.urbudget.apitransaction.service.BudgetService;
import com.urbudget.apitransaction.service.PersonService;
import com.urbudget.apitransaction.service.TransactionService;
import com.urbudget.apitransaction.util.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("api/v1/transaction")
public class UserController {

    @Autowired
    PersonService personService;

    private static final String NOT_VALID="Not valid operation";
    private static final String SUCCESS="Success";
    private static final String REMOVE="remove";
    private static final String REPLACE="replace";

    @GetMapping("/users")
    public Iterable<Person> getUsers() {
        return personService.getAll();
    }

    @PostMapping("users")
    public Response<Person> createUser(@RequestBody Person person) {
        Response<Person> response = new Response<>();
        try {
            Person responsePerson = personService.save(person);
            response.setData(responsePerson);
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @PatchMapping("users/{email}")
    public Response<Person> patchUser(@PathVariable String email, @RequestBody Patch patch) {
        Response<Person> response = new Response<>();
        try {
            Person personToUpdate = personService.getById(email);
            switch (patch.getOp()) {
                case REPLACE -> personToUpdate.setByKey(patch.getKey(), patch.getValue());
                case REMOVE -> personToUpdate.setByKey(patch.getKey(), null);
                case "test" -> {
                    boolean comparisonResult = personToUpdate.getByKey(patch.getKey()).equals(patch.getValue());
                    response.addMessage(String.valueOf(comparisonResult));
                }
                default -> throw new CustomException(NOT_VALID);
            }
            if (response.getMessages().isEmpty()) {
                Person responsePerson = personService.update(personToUpdate);
                response.setData(responsePerson);
                response.addMessage(SUCCESS);
            }
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("users/{email}")
    public Response<Person> getUser(@PathVariable String email) {
        Response<Person> response = new Response<>();
        try {
            Person responsePerson = personService.getById(email);
            response.setData(responsePerson);
            response.addMessage(SUCCESS);
        } catch (Exception e) {
            response.addMessage(e.getMessage());
        }
        return response;
    }
}
