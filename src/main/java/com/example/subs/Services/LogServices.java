package com.example.subs.Services;

import com.example.subs.Controller.LogController;
import com.example.subs.Repository.LogRepository;
import com.example.subs.Entity.User;
import com.example.subs.Entity.UserLog;
import com.example.subs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//configure this class as Service
@Service
public class LogServices {

//  link between the Services and Repository to allow the implementation of the services in the database
    @Autowired
    LogRepository logRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    LogController logController;

    private Long logCounter = 1L;
    UserLog log;

    public Date date() {
        Date date = new Date();
        return date;
    }
// add new  log to the user_log  table
public UserLog addUserLog(User user, String trans, double transAmount) {
    System.out.println("******************************" + user.toString());
    //use object to add user details in the user_log table
    UserLog log = new UserLog();
    log.setUserId(user);
    log.setUserName(user.getUserName());
    log.setUserBalance(user.getUserBalance());
    log.setUserEmail(user.getUserEmail());
    log.setCurrentDate(date());
    log.setTransactionType(trans);
    log.setTransactionAmount(transAmount);
    return logRepository.save(log);

}


// return all the logs in the user_log table
    public List<UserLog> getAllUsersLogs() {
        List<UserLog> userList = logRepository.findAll();
        return userList;
    }

    /* @Param: extract value from the URI and bind it to parameter
     * getUserLog: return all user logs from the user_log table
     */
    public List<UserLog> getUserLog(@Param("id") Long id) {

        List<UserLog> logList ;
        List<UserLog> newLogList = new ArrayList<>();
        UserLog userLog = new UserLog();

        //check the exist of such user in the user table
        User user =userRepository.getById(id);
        logList=logRepository.findAllByUserId(user);

        return logList;
    }

    public double getTransactionAmount(@Param("id")Long id){

        List<UserLog> logList ;
        List<UserLog> newLogList = new ArrayList<>();
        UserLog userLog = new UserLog();
        User user =userRepository.getById(id);
        logList=logRepository.findAllByUserId(user);
        return logList.get(logList.size()-1).getTransactionAmount();
    }

    public String getTransactionType(@Param("id")Long id){

        List<UserLog> logList ;
        List<UserLog> newLogList = new ArrayList<>();
        UserLog userLog = new UserLog();
        User user =userRepository.getById(id);
        logList=logRepository.findAllByUserId(user);
        return logList.get(logList.size()-1).getTransactionType();
    }
}



