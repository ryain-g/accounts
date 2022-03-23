package com.example.subs.Controller;

import com.example.subs.Entity.Statement;
import com.example.subs.Services.FutureService;
import com.example.subs.Services.LogServices;
import com.example.subs.Services.StatementService;
import com.example.subs.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//configure this class as controller
@RestController
// map web requests  path to this class
@RequestMapping(path="/subscriber")
public class StatementController {

    // link between the Services and controller to allow the implementation of the services base on the web request

    @Autowired
    FutureService futureService;

    @Autowired
    StatementService statementService;

    //map the HTTP GET requests with the certain path to the associate method
    @GetMapping(path="balance/{id}")

    //indicate that the result would be written straight in the response body
    @ResponseBody

    /*@PathVariable: extract the value from the URI and bind it to parameter.
     * getUserBalance: return user balance
     */
    public String getUserBalance(@PathVariable("id") Long id) {

        return statementService.getUserBalance(id);
    }

    @GetMapping(path="logs/{id}")
    @ResponseBody

    // return the last record log for the user from user_log table
    public String getUserLog(@PathVariable("id") Long id) {
        return statementService.getUserLog(id);
    }

    @GetMapping(path="statement/{id}")
    @ResponseBody

    // return  all the user logs from user_log table
    public Statement getUserStatement(@PathVariable("id") Long id) {
        return statementService.getUserStatement(id);
    }



}
