package com.example.subs.Controller;

import com.example.subs.Entity.FutureStatement;
import com.example.subs.Repository.LogRepository;
import com.example.subs.Services.FutureService;
import com.example.subs.Services.LogServices;
import com.example.subs.Entity.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.beans.Statement;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//configure this class as controller
@RestController

// map web requests path to this class
@RequestMapping(path="/subscriber")

public class LogController {

    // link between the Services and controller to allow the implementation of the services base on the web request
    @Autowired
    LogServices logServices;

    // link between the Repository and controller to allow the implementation of the Repository base on the web request
    @Autowired
    LogRepository logRepository;

    @Autowired
    FutureService futureService;

    private ExecutorService executor
            = Executors.newSingleThreadExecutor();

    //map the HTTP GET requests with the certain path to the associate method
    @GetMapping(path="/usersLogs/All")

    //indicate that the result would be written straight in the response body
    @ResponseBody

    //return all the users logs  in the user_log table
    public List<UserLog> getAllUserLogs() {
        return logRepository.findAll();
    }

    @GetMapping(path="/userLogs/{id}")
    @ResponseBody

    /*@PathVariable: extract the value from the URI and bind it to parameter.
     * getUserLog: return all the user logs in the user_log table
     */
    public List<UserLog> getUserLogs(@PathVariable ("id")Long id) {
        return logServices.getUserLog(id);
    }

    @GetMapping(path="/userLog/{id}")
    @ResponseBody
    public UserLog getUserLog(@PathVariable ("id")Long id) {
        System.out.println("success");
        List<UserLog> logList= logServices.getUserLog(id);
        UserLog log = new UserLog();
        return  log = logList.get(logList.size()-1);
    }

    @GetMapping(path="/thread")
    @ResponseBody
    public Future<String> getUserTransactionDetails () {
            return executor.submit(() -> {
                RestTemplate restTemplate = new RestTemplate();
                String futureStatement = restTemplate.getForObject("http://localhost:8085/future/thread", String.class);
                long FutureStartTime = System.nanoTime();
                System.out.println("###" + restTemplate.getForObject("http://localhost:8085/future/thread", String.class));
                Thread.sleep(7000);
                long FutureEndTime = System.nanoTime();
                long startTime = System.nanoTime();
                int number = 4+5;
                long endTime = System.nanoTime();
                String statement = futureService.getNewStatement();
                long FutureDuration = (FutureEndTime - FutureStartTime);
                long duration = (endTime - startTime);
                System.out.println("/////////////" + duration);
                System.out.println("/////////////" + FutureDuration);

                return statement;
            });


    }



}



