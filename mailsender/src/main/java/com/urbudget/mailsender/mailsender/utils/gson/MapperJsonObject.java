package com.urbudget.mailsender.mailsender.utils.gson;

import org.springframework.stereotype.Component;

import java.util.Optional;

public interface MapperJsonObject {
    Optional<String> execute(Object object);

    <T> Optional<T> execute(String json, Class<T> destinationClass);

    Optional<String> executeGson(Object object);
}
