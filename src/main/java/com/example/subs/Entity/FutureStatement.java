package com.example.subs.Entity;

import org.springframework.stereotype.Component;

@Component
public class FutureStatement {

    private String futureStatement = "this is a thread";

    public String getFutureStatement() {
        return futureStatement;
    }

    public void setFutureStatement(String futureStatement) {
        this.futureStatement = futureStatement;
    }
}
