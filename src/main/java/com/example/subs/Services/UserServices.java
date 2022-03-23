package com.example.subs.Services;

import com.example.subs.Controller.LogController;
import com.example.subs.Entity.User;
import com.example.subs.Entity.UserLog;
import com.example.subs.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//configure this class as services
@Service
public class UserServices {

    // link between the Services and Repositories to allow the implementation of the services in the database

    @Autowired
     UserRepository userRepository;

    @Autowired
    LogServices logServices;

    @Autowired
    LogController logController;

// define string to set the transaction type
    private String trans;

    private double transAmount=0;

// add new user to the user_log  table
     public User addNewUser(User user){
        user.setUserName(user.getUserName());
        user.setUserPhoneNumb(user.getUserPhoneNumb());
        user.setUserEmail(user.getUserEmail());
        user.setUserBalance(user.getUserBalance());
        User re=  userRepository.save(user);
        this.trans="create";
        logServices.addUserLog(re,trans,this.transAmount);
         return re;
     }

    // return all the users in the user table
     public Iterable<User> getAllUsers(){
         return userRepository.findAll();
     }

    // return user details from the user table base on it's id
     public User getUser(Long userId){
         return userRepository.getById(userId);
     }

    //Update the user information base on the send data
     public User updateUser(Long userId, User user) {
         user.setId(userId);
         user.setUserName(user.getUserName());
         user.setUserEmail(user.getUserEmail());
         user.setUserPhoneNumb(user.getUserPhoneNumb());
         user.setUserBalance(user.getUserBalance());
         User res=  userRepository.save(user);
         this.trans = "Update";
         logServices.addUserLog(user,trans,transAmount);
         logController.getUserLog(user.getId());
         return res;
     }

     //Update the user balance in case of adding to balance
    public User deposit(Long id, double numb){
         User user = userRepository.getById(id);
        user.setUserBalance(user.getUserBalance()+numb);
        userRepository.save(user);
        this.transAmount = numb;
        this.trans = "deposit";
        User res=  userRepository.save(user);
        logServices.addUserLog(user,trans,transAmount);
        logController.getUserLog(user.getId());
        return res;
    }

    //Update the user balance in case of subscribing from the balance
    public User withdraw(Long id, double numb) {
        User user = userRepository.getById(id);
        user.setUserBalance(user.getUserBalance()-numb);
        userRepository.save(user);
        this.transAmount=numb;
        this.trans = "WITHDRAW";
        User res =  userRepository.save(user);
        logServices.addUserLog(user,trans, transAmount);
        logController.getUserLog(user.getId());
        return res;
    }

    //delete all the users account from the user table
     public User deleteAllUsers() {
         userRepository.deleteAll();
         return null;
     }

    //delete user base on its id from the user table
    public User deleteUser(Long userId) {
        userRepository.deleteById(userId);
        return null;
    }
}
