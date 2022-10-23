package com.example.heroku.service;

@FunctionalInterface
public
interface DropboxActionResolver<T> {
    T perform() throws Exception;
}

