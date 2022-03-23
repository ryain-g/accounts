package com.example.subs.Services;

import com.example.subs.Entity.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    LogServices logServices;

    @Autowired
    UserServices userServices;


    public String getUserBalance(Long id) {
        double balance;
        balance = userServices.getUser(id).getUserBalance();
        String str = "Your Current Balance is: "+balance;

        return str;
    }


    // return the last record log for the user from user_log table
    public String getUserLog(Long id) {

        String transType;
        double balance;
        Date date;
        // get the user details from the user_log table
        int size = logServices.getUserLog(id).size()-1;
        balance = logServices.getUserLog(id).get(size).getUserBalance();
        transType = logServices.getUserLog(id).get(size).getTransactionType();
        date = logServices.getUserLog(id).get(size).getCurrentDate();

        String lastTrans = "The Last Process was "+transType+" at "+date+". Your Current Balance is: "+balance;
        return lastTrans;
    }


    // return  all the user logs from user_log table
    public Statement getUserStatement(Long id) {

        String transType;
        double balance;
        Date date;
        Statement statement = new Statement();
        int size = logServices.getUserLog(id).size()-1;
        List<String> list = new ArrayList<>();
        // get all users details from the user_log table
        for(int i=0; i<=size;i++ ){
            balance = logServices.getUserLog(id).get(i).getUserBalance();
            transType = logServices.getUserLog(id).get(i).getTransactionType();
            date = logServices.getUserLog(id).get(i).getCurrentDate();
            String lastTrans = "Your Process is "+transType+" at "+date+". Your Current Balance is: "+balance;
            list.add(lastTrans);
        }
        statement.setEmail(userServices.getUser(id).getUserEmail());
        statement.setStatement(list);
        return statement;
    }
}
