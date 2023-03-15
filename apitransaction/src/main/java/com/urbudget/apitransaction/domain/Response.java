package com.urbudget.apitransaction.domain;

import java.util.ArrayList;
import java.util.List;

public class Response <T> {
    T data;
    List<String> messages;

    public Response(){
        this.messages = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public void addMessage(String message){
        this.messages.add(message);
    }
}