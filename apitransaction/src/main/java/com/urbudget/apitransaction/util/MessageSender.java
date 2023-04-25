package com.urbudget.apitransaction.util;

public interface MessageSender<T> {
    void execute(T message, String idMessage);}