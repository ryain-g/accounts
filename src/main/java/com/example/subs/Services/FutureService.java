package com.example.subs.Services;

import com.example.subs.Entity.FutureStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutureService {

    @Autowired
    FutureStatement futureStatement;

    public String addNewStatement(String statement){
        futureStatement.setFutureStatement(statement);
        System.out.println(futureStatement.getFutureStatement());
        return futureStatement.getFutureStatement();


    }

    public  String getNewStatement(){
       return futureStatement.getFutureStatement();
    }
}
