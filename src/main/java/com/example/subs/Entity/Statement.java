package com.example.subs.Entity;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.List;

@Component
public class Statement {
    private String email;

    private List<String> statement;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getStatement() {
        return statement;
    }

    public void setStatement(List<String> statement) {
        this.statement = statement;
    }
}
